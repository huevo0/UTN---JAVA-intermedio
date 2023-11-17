package actividad_1;

public class main {

	public static void main(String[] args) {
		
		Materia sistema = new Materia("Diseño de Sistemas");
		Materia algoritmos = new Materia("Algoritmos y Estructuras de Datos");
		Materia paradigma = new Materia("Paradigmas de Programacion");
		
		sistema.agregarCorrelativa(paradigma);
		sistema.agregarCorrelativa(algoritmos);
		paradigma.agregarCorrelativa(algoritmos);
		
		Alumno alu1 = new Alumno("Jose Perez");
		Alumno alu2 = new Alumno("Juan Paez");
		Alumno alu3 = new Alumno("Mario Sanchez");
		
		alu1.agregarAprobadas(algoritmos);
		alu1.agregarAprobadas(paradigma);
		alu2.agregarAprobadas(algoritmos);
		alu2.agregarAprobadas(paradigma);
		alu3.agregarAprobadas(paradigma);

		
		Inscripcion I1 = new Inscripcion(alu1,sistema);
		Inscripcion I2 = new Inscripcion(alu2,sistema);
		Inscripcion I3 = new Inscripcion(alu3,sistema);
		
		System.out.println("La Inscripcion del Alumno: " + I1.getAlumno().getNombre()+" en la materia: " +I1.getMateria().getNombre() + " es "+ I1.Validar());
		System.out.println("La Inscripcion del Alumno: " + I2.getAlumno().getNombre()+" en la materia: " +I2.getMateria().getNombre() + " es "+ I2.Validar());
		System.out.println("La Inscripcion del Alumno: " + I3.getAlumno().getNombre()+" en la materia: " +I3.getMateria().getNombre() + " es "+ I3.Validar());
				
		
		

	}

}
