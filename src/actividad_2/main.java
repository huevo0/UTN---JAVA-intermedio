package actividad_2;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
		interfazConString lambda = (elemento) -> elemento.toUpperCase(); 
		List<String> lista = new ArrayList<String>();
		 
		 lista.add("puerta");
		 lista.add("ventana");
		 lista.add("pared");
		 lista.add("techo");
		 lista.add("piso");
		 System.out.println("Lista de String");
		 for (String string : lista) {
			 System.out.println(string);
			
		}
		 
		 System.out.println("Lista transformada a Mayuscula");
		 for (String l : lista) {
			 System.out.println(lambda.convierteString(l));
			}
		 
		 

	}

}
