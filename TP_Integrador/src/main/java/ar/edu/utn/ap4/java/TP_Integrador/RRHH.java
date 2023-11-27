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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RRHH {
    private Usuario usuario;
    private List<Tecnico> tecnicos;

    public static List<Tecnico> importarTecnicosCSV() {
        List<Tecnico> tecnicos = new ArrayList<>();
        String filePath = "Tecnicos.txt";

/*******	SE LEE EL CONTENIDO DEL ARCHIVO TECNICOS Y SE CARGA EN UN STRING[] 	********/
        
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(",");
                if (datos.length == 2) {
                    Tecnico tecnico = new Tecnico();
                    tecnico.setNombre(datos[0].trim());
                    tecnico.setEspecialidad(datos[1].trim());
                    tecnicos.add(tecnico);
                } else {
                    System.out.println("Error en el formato de la línea: " + String.join(",", datos));
                }
            }
      //      System.out.println("========Se importaron correctamente los Tecnicos desde el archivo CSV========");
        } catch (FileNotFoundException e) {
            System.err.println("No se pudo encontrar el archivo: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tecnicos;
    }
 /*******	SE CARGAN LOS DATO EN LA BD DE MySQL 	********/

    public static void altaTecnicos(List<Tecnico> tecnicos) {
        try (ConexionBD conectar = new ConexionBD(); Connection con = conectar.Conectar("Configuracion.txt")) {
            String query = "INSERT INTO Tecnico (nombre, especialidad) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                for (Tecnico tecnico : tecnicos) {
                    preparedStatement.setString(1, tecnico.getNombre());
                    preparedStatement.setString(2, tecnico.getEspecialidad());
                    preparedStatement.executeUpdate();
                }
        //        System.out.println("========Se Cargaron correctamente los Tecnicos a la BD========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*******	SE CONSULTA LA TABLA TECNCOS BD DE MySQL YU SE MUESTRA POR PANTALLA 	********/
    
    public static List<Tecnico> tecnicosBD() {
        List<Tecnico> tecnicos = new ArrayList<>();
        System.out.println("\n========================== Tecnicos DE LA BD ==========================\n");
        try (ConexionBD conectar = new ConexionBD(); Connection con = conectar.Conectar("Configuracion.txt")) {
            String query = "SELECT * FROM Tecnico";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                System.out.println("-----------------------------------------------------------------------");
            	System.out.println("\tid\t" + "\tNombre\t" + "\tEspecialidad");
                System.out.println("-----------------------------------------------------------------------");
                while (rs.next()) {
                    Tecnico tecnico = new Tecnico();
                    tecnico.setId(rs.getInt("id"));
                    tecnico.setNombre(rs.getString("Nombre"));
                    tecnico.setEspecialidad(rs.getString("Especialidad"));
                    tecnicos.add(tecnico);

                    System.out.println("\t" + tecnico.getId() + "\t\t" + tecnico.getNombre() + "\t\t" + tecnico.getEspecialidad());
                    System.out.println("-----------------------------------------------------------------------");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tecnicos;
    }
}
