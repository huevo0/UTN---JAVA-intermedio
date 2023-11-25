package ar.edu.utn.ap4.java.TP_Integrador;


import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidente {
	
	 	
		private int id;
		private String cliente;
	    private String servicio;
	    private String descripcion;
	    private String tecnico;
	    private String estado;
	    private LocalDate fechaAlta;
        private LocalDate fechaResolucion;
/*	    private String consideraciones;
	    */
	    
	    
}
