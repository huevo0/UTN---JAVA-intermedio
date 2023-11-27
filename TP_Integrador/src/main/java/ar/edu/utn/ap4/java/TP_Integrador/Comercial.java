package ar.edu.utn.ap4.java.TP_Integrador;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.AutoCloseable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercial {
    private Usuario usuario;
    private List<Cliente> clientes;

    public static List<Cliente> importarClientesCSV() {
        List<Cliente> clientes = new ArrayList<>();

        String filePath = "Clientes.txt";

        /*******	SE LEE EL ARCHIVO CLIENTE Y SE CARGA EN UN STRING[] 	********/
        
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(",");
                if (datos.length == 5) {
                    Cliente cliente = new Cliente();
                    cliente.setRazonsocial(datos[0].trim());
                    cliente.setCuit(datos[1].trim());
                    cliente.setEmail(datos[2].trim());
                    cliente.setTelefono(datos[3].trim());
                    cliente.setServicio(datos[4].trim());
                    clientes.add(cliente);
                } else {
                    System.out.println("Error en el formato de la línea: " + String.join(",", datos));
                }
            }
    //        System.out.println("========Se importaron correctamente los Clientes desde el archivo CSV========");
        } catch (FileNotFoundException e) {
            System.err.println("No se pudo encontrar el archivo: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientes;
    }

    /*******	SE CARGA LOS DATOS EN LA TABLA CLIENTES EN LA BD MySQL 	********/
    
    public static void altaCliente(List<Cliente> clientes) {
        try (ConexionBD conectar = new ConexionBD(); Connection con = conectar.Conectar("Configuracion.txt")) {
            String query = "INSERT INTO Cliente (Razon_Social, Cuit, E_mail, Telefono, Servicios) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                for (Cliente cli : clientes) {
                    preparedStatement.setString(1, cli.getRazonsocial());
                    preparedStatement.setString(2, cli.getCuit());
                    preparedStatement.setString(3, cli.getEmail());
                    preparedStatement.setString(4, cli.getTelefono());
                    preparedStatement.setString(5, cli.getServicio());
                    preparedStatement.executeUpdate();
                }
      //          System.out.println("========Se Cargaron correctamente los Clientes en la BD========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*******	SE MUESTRAN LOS CLIENTES DE LA BD MySQL 	********/

    
    public static List<Cliente> clientesBD () {
        List<Cliente> clientes = new ArrayList<>();
        System.out.println("\n====================================== Clientes DE LA BD ======================================\n");
        try (ConexionBD conectar = new ConexionBD(); Connection con = conectar.Conectar("Configuracion.txt")) {
            String query = "SELECT * FROM Cliente";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                System.out.println("---------------------------------------------------------------------------------------------------");
                System.out.println("Id\tRazon Social\tCuit\t\tE-Mail\t\t\tTelefono\tServicio");
                System.out.println("---------------------------------------------------------------------------------------------------");

                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setRazonsocial(rs.getString("Razon_Social"));
                    cliente.setCuit(rs.getString("Cuit"));
                    cliente.setEmail(rs.getString("E_mail"));
                    cliente.setTelefono(rs.getString("Telefono"));
                    cliente.setServicio(rs.getString("Servicios"));
                    clientes.add(cliente);

                    System.out.println(rs.getString("id") + "\t" + rs.getString("Razon_Social") + "\t" +
                            rs.getString("Cuit") + "\t" + rs.getString("E_mail") + "\t" +
                            rs.getString("Telefono") + "\t" + rs.getString("Servicios"));

                    System.out.println("---------------------------------------------------------------------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
