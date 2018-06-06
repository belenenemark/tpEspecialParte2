package grafo;

import java.util.ArrayList;


public class vertice {
	private String nombre;
	private ArrayList<arista> adyacentes;
	
	public vertice(String nombre){
		this.nombre = nombre;
		adyacentes = new ArrayList<arista>();
		
	}
	public int cantAristas(){
		return adyacentes.size();
	}
	
	 public ArrayList<arista> getAdyacentes() {
		return adyacentes;
	}
	 public boolean hasAdyacente(){
		 return adyacentes!=null;
	 }
	 
	 public String getNombre() {
		return nombre;
	}
	 
	 public void addAdyacentes(vertice v1, int costo) {
			arista aux = new arista(v1,costo);
			adyacentes.add(aux);
	 }
	 
	 public arista getArista(vertice v2){
		 for(arista a :adyacentes){
			 if(a.getVa().equals(v2)){
				 return a;
			 }
		 }
		 return null;
	 }

}
