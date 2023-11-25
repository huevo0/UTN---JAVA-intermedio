package ar.edu.utn.ap4.java.TP_Integrador;

import java.sql.ResultSet;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico {
	private int id;
	private String nombre;
    private String especialidad;
/*	private int tiempoEstimadoPorDefecto;
    private String medioNotificacionPreferido;
*/
}
