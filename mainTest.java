public static int IngresarValor(){
		int n=0;
		try{
			BufferedReader entrada= new BufferedReader(new InputStreamReader(System.in));
			
			n= new Integer(entrada.readLine());
			
		}catch(Exception exc){
			System.out.println(exc);
		}
		
		
		return n;
	}
	public static char IngresarOpcion(){
		char value=0;
		try{
			BufferedReader entrada= new BufferedReader(new InputStreamReader(System.in));
			
			value=(char) (entrada.readLine().charAt(0));
			
		}catch(Exception exc){
			System.out.println(exc);
		}
		
		
		return value;
	}
	
	
	public static void menu(char opc, Grafo predictor){
		System.out.println("Ingrese opcion");
		String genero=" ";
		switch(opc){
		case 'a':System.out.println("Ingrese  genero que quiere buscar");
				 genero =generarString();
				System.out.println("Ingrese la cantidad de generos de mayor incidencia de busqueda");
				int N=IngresarValor();
			resolucionA(predictor,N,genero);
		break;
		case 'b':System.out.println("Ingrese  genero que quiere buscar");
			 genero =generarString();
			resolucionB(predictor, genero);
		break;
		case 'c': System.out.println("Ingrese  genero que quiere buscar");
		 genero =generarString();
			resolucionC( predictor,genero);
		break;
		}
	}

	private static String generarString() {
		String value=" ";
		try{
			BufferedReader entrada= new BufferedReader(new InputStreamReader(System.in));
			
			value= entrada.readLine();
			
		}catch(Exception exc){
			System.out.println(exc);
		}
		
		
		return value;
	}

	private static void resolucionC(Grafo predictor, String gene) {
		ArrayList<vertice> vol= predictor.generosAfines(gene);
		System.out.println("cant generos afines "+vol.size());

		for (vertice vertice : vol) {
			System.out.print(vertice.getNombre()+" ");
		}
		
	}

	private static void resolucionB(Grafo predictor,String gene) {
		 ArrayList<vertice> ab=predictor.generosBuscadosPostA(gene);
			System.out.println("tamanio punto b "+ab.size());
		 for (vertice vertice : ab) {
			System.out.print(vertice.getNombre()+" ");
		}
		
	}

	private static void resolucionA(Grafo predictor, int cantNum, String genero) {
		ArrayList<String> aux= predictor.getNgeneros(genero, cantNum);
		 for (String a : aux) {
				System.out.println(a);
			}
		
	}package grafo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileNotFoundException;

public class mainTest {

	public static void main(String[] args) throws FileNotFoundException {
		Grafo predictor = new Grafo();
		generarGrafo(predictor);
		System.out.println("cant vertices "+predictor.getTamaniografo());
		System.out.println("cant aristas "+predictor.getCantAristas());

		System.out.println("Ingresar opcion");
		System.out.println("a-N generos mas buscados a partir de un genero");
		System.out.println("b-generos buscados luego de buscar por A");
		System.out.println("c-Obtener generos afines");
		char opc =IngresarOpcion();
		menu(opc,predictor);
		
	}
	
	// ingresar N numeros 
	public static int IngresarValor(){
		int n=0;
		try{
			BufferedReader entrada= new BufferedReader(new InputStreamReader(System.in));
			
			n= new Integer(entrada.readLine());
			
		}catch(Exception exc){
			System.out.println(exc);
		}
		
		
		return n;
	}
	public static char IngresarOpcion(){
		char value=0;
		try{
			BufferedReader entrada= new BufferedReader(new InputStreamReader(System.in));
			
			value=(char) (entrada.readLine().charAt(0));
			
		}catch(Exception exc){
			System.out.println(exc);
		}
		
		
		return value;
	}
	
	
	public static void menu(char opc, Grafo predictor){
		System.out.println("Ingrese opcion");
		String genero=" ";
		switch(opc){
		case 'a':System.out.println("Ingrese  genero que quiere buscar");
				 genero =generarString();
				System.out.println("Ingrese la cantidad de generos de mayor incidencia de busqueda");
				int N=IngresarValor();
			resolucionA(predictor,N,genero);
		break;
		case 'b':System.out.println("Ingrese  genero que quiere buscar");
			 genero =generarString();
			resolucionB(predictor, genero);
		break;
		case 'c': System.out.println("Ingrese  genero que quiere buscar");
		 genero =generarString();
			resolucionC( predictor,genero);
		break;
		}
	}

	private static String generarString() {
		String value=" ";
		try{
			BufferedReader entrada= new BufferedReader(new InputStreamReader(System.in));
			
			value= entrada.readLine();
			
		}catch(Exception exc){
			System.out.println(exc);
		}
		
		
		return value;
	}

	private static void resolucionC(Grafo predictor, String gene) {
		ArrayList<vertice> vol= predictor.generosAfines(gene);
		System.out.println("cant generos afines "+vol.size());

		for (vertice vertice : vol) {
			System.out.print(vertice.getNombre()+" ");
		}
		
	}

	private static void resolucionB(Grafo predictor,String gene) {
		 ArrayList<vertice> ab=predictor.generosBuscadosPostA(gene);
			System.out.println("tamanio punto b "+ab.size());
		 for (vertice vertice : ab) {
			System.out.print(vertice.getNombre()+" ");
		}
		
	}

	private static void resolucionA(Grafo predictor, int cantNum, String genero) {
		ArrayList<String> aux= predictor.getNgeneros(genero, cantNum);
		 for (String a : aux) {
				System.out.println(a);
			}
		
	}
	public static void generarGrafo(Grafo predictor) throws FileNotFoundException{
		 String csvFile = "dataset2.csv";
	        String line = "";
	        String cvsSplitBy = ",";
	        FileReader fr = new FileReader(csvFile);

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        	
	        	boolean primera = true;
	            while ((line = br.readLine()) != null) {
	            	if (primera) {
	            		primera = false;
	            	} else {
	            		  String[] items = line.split(cvsSplitBy);
	            		  for (int i = 1; i < items.length; i++) {
	            			  predictor.addPar(items[i-1],items[i]);            			  
						}              
	            	}
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	      
	 }
}
