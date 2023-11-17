package actividad_3_Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
		List<String> lista = new ArrayList<String>();
		 
		lista.add("puerta");
		lista.add("ventana");
		lista.add("pared");
		lista.add("techo");
		lista.add("piso");
		
		lista.stream().forEach((l)-> System.out.println(l.toUpperCase()));
		
	}

}
