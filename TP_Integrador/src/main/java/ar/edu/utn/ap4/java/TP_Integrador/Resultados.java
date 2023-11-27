package ar.edu.utn.ap4.java.TP_Integrador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

public class Resultados {
	
	
/*******	METODO QUE ME DEVUELVE EL NOMBRE DEL TECNICO CON MAS INCIDENTES RESUELTOS 	********/	
	
	public static String tecnicoMasIncidentes(LocalDate fechaInicio, int ultimosNDias) {
	        String query = "SELECT tecnico, COUNT(*) AS cantidad FROM Incidente " +
	                       "WHERE estado = 'Resuelto' AND fecha_resolucion >= ? " +
	                       "GROUP BY tecnico " +
	                       "ORDER BY cantidad DESC " +
	                       "LIMIT 1";

	        try (ConexionBD conectar = new ConexionBD(); 
	        		Connection con = conectar.Conectar("Configuracion.txt");
	             PreparedStatement preparedStatement = con.prepareStatement(query)) {

	            LocalDate fechaFin = fechaInicio.minusDays(ultimosNDias);
	            preparedStatement.setDate(1, Date.valueOf(fechaFin));

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getString("tecnico");
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        return null;
	    }

	/*******	METODO QUE ME DEVUELVE EL TECNICO DE LA ESPECILIDAD ESPECIFICA CON MAS INCIDENTES RESUELTOS 	********/	
	
	public static String tecnicoEspecialidadMasIncidentes(LocalDate fechaInicio, int ultimosNDias) {
        String query = "SELECT tecnico, COUNT(*) AS cantidad FROM Incidente " +
                       "WHERE estado = 'Resuelto' AND servicios = 'SAP' AND fecha_resolucion >= ? " +
                       "GROUP BY tecnico " +
                       "ORDER BY cantidad DESC " +
                       "LIMIT 1";

        try (ConexionBD conectar = new ConexionBD(); 
        		Connection con = conectar.Conectar("Configuracion.txt");
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            LocalDate fechaFin = fechaInicio.minusDays(ultimosNDias);
            preparedStatement.setDate(1, Date.valueOf(fechaFin));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("tecnico");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return null;
    }

	/*******	METODO QUE ME DEVUELVE EL NOMBRE DEL TECNICO MAS RAPIDO 	********/	
	
	public static String tecnicoMasRapido() {
        String query = "SELECT tecnico, AVG(DATEDIFF(fecha_resolucion, fecha_alta)) AS promedio_resolucion " +
                       "FROM Incidente " +
                       "WHERE estado = 'Resuelto' " +
                       "GROUP BY tecnico " +
                       "ORDER BY promedio_resolucion ASC " +
                       "LIMIT 1";

        try (ConexionBD conectar = new ConexionBD(); 
        		Connection con = conectar.Conectar("Configuracion.txt");
                
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                return resultSet.getString("tecnico");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return null;
    }
	
	}

