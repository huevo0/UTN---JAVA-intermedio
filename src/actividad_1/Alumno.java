package actividad_1;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
	private String nombre;
	private List<Materia>aprobadas;
	public Alumno(String nombre) {
		super();
		this.nombre = nombre;
		this.aprobadas = new ArrayList<>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void agregarAprobadas(Materia aprobadas) {
        this.aprobadas.add(aprobadas);
    }
	public boolean aprobada(Materia materia) {
        return aprobadas.contains(materia);
    }
	
	
	
	
	}	

