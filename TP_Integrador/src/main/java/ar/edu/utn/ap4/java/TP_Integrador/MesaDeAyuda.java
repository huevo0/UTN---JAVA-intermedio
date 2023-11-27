package ar.edu.utn.ap4.java.TP_Integrador;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.AutoCloseable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaDeAyuda {
    private Usuario usuario;
    private List<Incidente> incidentes;

    
    /*******	SE LEE EL CONTENIDO DEL ARCHIVO INCIDENTES Y SE CARGA EN UN STRING[] 	********/   

    public static List<Incidente> importarIncidentesCSV() {
        List<Incidente> incidentes = new ArrayList<>();
        String archivo = "Incidentes.txt";

        try (Scanner scanner = new Scanner(new File(archivo))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(",");
                if (datos.length == 7) {
                    Incidente incidente = new Incidente();
                    incidente.setCliente(datos[0].trim());
                    incidente.setServicio(datos[1].trim());
                    incidente.setDescripcion(datos[2].trim());
                    incidente.setTecnico(datos[3].trim());
                    incidente.setEstado(datos[4].trim());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    incidente.setFechaAlta(LocalDate.parse(datos[5].trim(), formatter));
                    incidente.setFechaResolucion(LocalDate.parse(datos[6].trim(), formatter));
                    incidentes.add(incidente);
                } else {
                    System.out.println("Error en el formato de la línea: " + String.join(",", datos));
                }
            }
     //       System.out.println("========Se importaron correctamente los Incidentes desde el archivo CSV========");
        } catch (FileNotFoundException e) {
            System.err.println("No se pudo encontrar el archivo: " + archivo);
        }
        return incidentes;
    }
    /*******	SE CARGA LA BASE DE DATOS EN MySQL 	********/
    
    public static void altaIncidentes(List<Incidente> incidentes) {
        try ( ConexionBD conectar = new ConexionBD(); 
        		Connection con = conectar.Conectar("Configuracion.txt")) {
            String query = "INSERT INTO Incidente (Cliente, Servicios, Descripcion, Tecnico, Estado, Fecha_Alta, Fecha_Resolucion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                for (Incidente incid : incidentes) {
                    preparedStatement.setString(1, incid.getCliente());
                    preparedStatement.setString(2, incid.getServicio());
                    preparedStatement.setString(3, incid.getDescripcion());
                    preparedStatement.setString(4, incid.getTecnico());
                    preparedStatement.setString(5, incid.getEstado());
                    preparedStatement.setDate(6, Date.valueOf(incid.getFechaAlta()));
                    preparedStatement.setDate(7, Date.valueOf(incid.getFechaResolucion()));

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
       //         System.out.println("========Se Cargaron correctamente los Incidentes a la BD========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*******	SE MUESTRA LA TABLA INCIDENTES DESDE MySQL 	********/
    
    public static List<Incidente> incidentesBD() {
        List<Incidente> incidentes = new ArrayList<>();
        System.out.println("\n==================================================== Incidentes DE LA BD ====================================================\n");
        try (ConexionBD conectar = new ConexionBD(); Connection con = conectar.Conectar("Configuracion.txt")) {
            String query = "SELECT * FROM Incidente";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("id\tCliente\t\tServicios\tTecnico\t\tEstado\t\tFecha de Alta\tFehca de Resolucion\tDescripcion");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

                while (rs.next()) {
                    Incidente incidente = new Incidente();
                    incidente.setId(rs.getInt("id"));
                    incidente.setCliente(rs.getString("Cliente"));
                    incidente.setServicio(rs.getString("Servicios"));
                    incidente.setDescripcion(rs.getString("Descripcion"));
                    incidente.setTecnico(rs.getString("Tecnico"));
                    incidente.setEstado(rs.getString("Estado"));
                    incidente.setFechaAlta(rs.getDate("Fecha_Alta").toLocalDate());
                    incidente.setFechaResolucion(rs.getDate("Fecha_Resolucion").toLocalDate());
                    incidentes.add(incidente);
                    System.out.println(incidente.getId() + "\t" + incidente.getCliente() + "\t" +
                            incidente.getServicio() + "\t\t" + incidente.getTecnico() + "\t\t" + incidente.getEstado() + "\t" +
                            incidente.getFechaAlta() + "\t" + incidente.getFechaResolucion()+ "\t\t" + incidente.getDescripcion() );
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incidentes;
    }
}
