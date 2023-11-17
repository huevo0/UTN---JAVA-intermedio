package actividad_3_Ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class main {

	public static void main(String[] args) {
		
		List<String> lista = new ArrayList<String>();
		 
		lista.add("puerta");
		lista.add("ventana");
		lista.add("pared");
		lista.add("techo");
		lista.add("piso");
		System.out.println("Lista de Strings: ");
		lista.stream().forEach((l)-> System.out.println(l));
		
		System.out.println("RESULTADO para Lista de Strings Concatenada: ");
		String listaConcatena = lista.stream().collect(Collectors.joining(", "));
		System.out.println(listaConcatena);
		int n = 5;
		System.out.println("RESULTADO para Lista Concatenada y Filtrada: ");
		System.out.println(Filtrar(lista , n));
		
	}
	public static String Filtrar(List<String> l, int n) {
		return l.stream().filter((palabra) -> palabra.length()>n).collect(Collectors.joining(", "));
	}

}
