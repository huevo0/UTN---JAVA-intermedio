package ar.edu.utn.ap4.java.TP_Integrador;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants.Exclude;
@AllArgsConstructor
@NoArgsConstructor


@Data 
public class Cliente {
	private long id;
	private String razonsocial;
	private String cuit;
	private String email;
	private String telefono;
	private String servicio;
	 
}
