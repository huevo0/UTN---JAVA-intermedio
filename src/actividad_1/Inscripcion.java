package actividad_1;

public class Inscripcion {

	private Alumno alumno;
	private Materia materia;
	
	public Inscripcion(Alumno alumno, Materia materia) {
		super();
		this.alumno = alumno;
		this.materia = materia;
	}
	
	public boolean Validar() {
		
		for (Materia correlativa : materia.getCorrelativas()) {
            if (!alumno.aprobada(correlativa)) {
				return false;
			}
			
		}
		return true;
		
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public Materia getMateria() {
		return materia;
	}
	
	
	}


