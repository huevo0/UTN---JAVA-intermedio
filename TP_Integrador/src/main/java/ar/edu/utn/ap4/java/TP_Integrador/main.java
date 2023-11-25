package ar.edu.utn.ap4.java.TP_Integrador;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) throws FileNotFoundException, SQLException, ParseException {
		
		List<Tecnico>tecnicos = new ArrayList<Tecnico>();
		tecnicos = RRHH.importarTecnicosCSV();
		ConexionBD conectaBD = new ConexionBD();
		conectaBD.Conectar("Configuracion.txt");
		RRHH.altaTecnicos(tecnicos);
		RRHH.tecnicosBD();
		
		List<Cliente>clientes = new ArrayList<Cliente>();
		clientes=Comercial.importarClientesCSV();
		ConexionBD conectaBD1 = new ConexionBD();
		conectaBD1.Conectar("Configuracion.txt");
		Comercial.altaCliente(clientes);
		Comercial.clientesBD();
	
		
		List<Incidente>incidentes = new ArrayList<Incidente>();
		incidentes=MesaDeAyuda.importarIncidentesCSV();
		ConexionBD conectaBD2 = new ConexionBD();
		conectaBD2.Conectar("Configuracion.txt");
		MesaDeAyuda.altaIncidentes(incidentes);
		MesaDeAyuda.incidentesBD();
		
		LocalDate fechaInicio = LocalDate.now(); 
        int ultimosNDias = 30; 
        Resultados tecnico = new Resultados();
        String tecRes;
        tecRes = tecnico.tecnicoMasIncidentes(fechaInicio, ultimosNDias);
        System.out.println("\n=================================================================================================\n");
        if (tecRes != null) {
            System.out.println("El técnico con más incidentes resueltos en los últimos " + ultimosNDias + " días es: " + tecRes);
        } else {
            System.out.println("No se encontraron resultados.");
        }
        
        Resultados tecnico2 = new Resultados();
        String tecEsp;
        tecEsp = tecnico2.tecnicoEspecialidadMasIncidentes(fechaInicio, ultimosNDias);
        System.out.println("\n=================================================================================================\n");
        if (tecEsp != null) {
            System.out.println("En la Especialidad SAP el técnico con más incidentes resueltos en los últimos " + ultimosNDias +" días es: " + tecEsp);
        } else {
            System.out.println("No se encontraron resultados.");
        }
        
        Resultados tecnico3 = new Resultados();
        String tecnRapido = tecnico3.tecnicoMasRapido();
        System.out.println("\n=================================================================================================\n");
        if (tecnRapido != null) {
            System.out.println("El técnico que más rápido resolvió los incidentes es: " + tecnRapido);
        } else {
            System.out.println("No se encontraron resultados.");
        }
	}

	

}
