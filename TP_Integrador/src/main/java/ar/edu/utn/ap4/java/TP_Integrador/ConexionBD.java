package ar.edu.utn.ap4.java.TP_Integrador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.lang.AutoCloseable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConexionBD implements AutoCloseable{
    private String bd;
    private String url;
    private String user;
    private String password;
    private String driver;
    private Connection con;

        
    public Connection Conectar(String archivo) {
    	Properties configuracion = new Properties();
    	 try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
             String line;
             while ((line = reader.readLine()) != null) {
                 String[] parts = line.split("=");
                 if (parts.length == 2) {
                     String key = parts[0].trim();
                     String value = parts[1].trim();
                     configuracion.setProperty(key, value);
                 } else {
                     System.out.println("Error en el formato de la línea: " + line);
                 }
             }
         } catch (IOException e) {
             System.err.println("Error al cargar la configuración: " + e.getMessage());
         }
        try {
            Class.forName(configuracion.getProperty("driver"));
            con = DriverManager.getConnection(
                    configuracion.getProperty("url"),
                    configuracion.getProperty("user"),
                    configuracion.getProperty("password"));
     //       System.out.println("Conectado a BD: " + configuracion.getProperty("bd"));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("No se pudo Conectar a BD: " + configuracion.getProperty("bd"));
            e.printStackTrace();
        }
        return con;
    }
    
    public void Desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Desconectado de BD");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        Desconectar();
    }

}