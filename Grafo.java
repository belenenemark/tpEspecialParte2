package grafo;

import java.util.ArrayList;
import java.util.Hashtable;


public class Grafo {
	private ArrayList<vertice> vertices;
	
	
	
	public Grafo(){
		vertices = new ArrayList<vertice>();
		
	}
	
	public int getTamaniografo(){
		return vertices.size();
	}
	 public int getCantAristas(){
		 int aux=0;
		 for (vertice ver : vertices) {
			aux+=ver.cantAristas();
		}
		 return aux;
	 }
	public void addVertice (String v1){
		vertice aux = new vertice(v1);
		addVertice(aux);
	}
	
	public void addPar(String v1, String v2){
		vertice aux1 = buscarVertice(v1);
		vertice aux2 = buscarVertice(v2); //dado 2 String genero los vertices de ser necesario
		if (aux1==null){
			aux1=new vertice(v1);
			this.addVertice(aux1);
		}
			
		if(aux2==null){
			aux2=new vertice(v2);
			this.addVertice(aux2);
		}
			
		
		arista camino = aux1.getArista(aux2); //agrego la arista de los vertices, si ya existe sumo uno al costo del camino
		if(camino!=null){
			camino.incremento();
		}else{
			addAdyacente(aux1,aux2);
		}
		
	}
	
	public vertice buscarVertice(String v){
		for(vertice ver:vertices){
			if(ver.getNombre().equals(v)){
				return ver;
			}
		}
		return null;
	}
	
	public void addVertice (vertice v1){
		vertices.add(v1);
	}
	
	public ArrayList<arista> getAdyacentes(vertice v1){
		
		return vertices.get(vertices.indexOf(v1)).getAdyacentes();
		
	}
	
	public void addAdyacente (vertice origen, vertice destino){
		addAdyacente(origen,destino,1);
	}
	
	public void addAdyacente (vertice origen, vertice destino,int costo){
		vertices.get(vertices.indexOf(origen)).addAdyacentes(destino, costo);
	}
	
	public ArrayList<vertice> getVertices() {
		return vertices;
	}
	
	public void DFS (){
		Hashtable<vertice,String> estadoVisita = new Hashtable<vertice,String>();
		for (vertice vert : vertices) {
			estadoVisita.put(vert, "blanco");
		}		
		String[] estado = new String[vertices.size()+1];
		vertice[] padres = new vertice[vertices.size()+1];
		
		for(int i =0;i< estado.length;i++){
			estado[i]="blanco";
			padres[i]=null;
		}
		
		for(vertice v : vertices){
		
			if(estadoVisita.get(v)=="blanco"){
				this.DFS_visitar(v,estadoVisita);
				
			}			
		}		
	}
	private void DFS_visitar(vertice v, Hashtable<vertice,String> estadosVisita){
		estadosVisita.put(v, "amarillo");
		
		for(arista u : v.getAdyacentes()){
			String aux= estadosVisita.get(u);
			
			if(estadosVisita.get(u.getVa()).equals("blanco")){
				this.DFS_visitar(u.getVa(),estadosVisita);
			}
		}
		estadosVisita.put(v,"negro");
		

	}
	
	
	public ArrayList<vertice> generosBuscadosPostA(String generoA){
		Hashtable<vertice,String> estadoVisita = new Hashtable<vertice,String>();
		for (vertice vert : vertices) {
			estadoVisita.put(vert, "blanco");
		}
		
		DFS_visitar(buscarVertice(generoA), estadoVisita);
		

		ArrayList<vertice> aBuscados= new ArrayList<vertice>();
		
		
		for(vertice v : vertices){
			if(estadoVisita.get(v).equals("negro")){
				aBuscados.add(v);
			}
		}
		
			return aBuscados;
	
	}
	public ArrayList<String> getNgeneros(String genero, int N){
		vertice v1 = this.buscarVertice(genero);
		System.out.println(v1.cantAristas());
		ArrayList<arista> aux =this.buscarVertice(genero).getAdyacentes();
		ArrayList<String> gen = new ArrayList<String>();
		if (aux.size()<=N){
			System.out.println("el tamaño de aux es menor a N");
			for (int i = 0; i < aux.size(); i++) {
			
				 gen.add(aux.get(i).getVa().getNombre());
				
			}
		}else{
			System.out.println("el tamaño de aux es mayor a N");
			int ValorMax=0;
			String nom= "";
			int i=0;
			while (i<N){
				for (int j = 0; j < aux.size(); j++) {
					
					if((ValorMax<aux.get(j).getCosto())&& !gen.contains(aux.get(j).getVa().getNombre())){
						ValorMax=aux.get(j).getCosto();
						nom= aux.get(j).getVa().getNombre();
						
					}
					
				}
				ValorMax=0;
				gen.add(nom);
				i++;
			}
			
			
		}
		return gen;
	}
	private void  buscarAfines(vertice v,Hashtable<vertice, Boolean> afines,Hashtable<vertice, String> estadoNodos, vertice fin){
    	if (v.hasAdyacente()) {
        	for (arista arist : v.getAdyacentes()) {
        		if (estadoNodos.get(arist.getVa()).equals("blanco") ) {
        			if (!arist.getVa().equals(fin)) {
        				if (arist.getVa().hasAdyacente()) {
        		        	estadoNodos.put(arist.getVa(), "negro");
        					buscarAfines(arist.getVa(),afines,estadoNodos,fin);
						}
					}
        			else{
    					afines.put(arist.getVa(), true);
    				}
				}
        		if (afines.get(arist.getVa())) {
        			afines.put(v, true);
				}
    		}
		}
    }
    
    public ArrayList<vertice> generosAfines(String nodo){
    	vertice buscar = buscarVertice(nodo);
    	
    	ArrayList<vertice> arrRet = new ArrayList<vertice>();
    	if (buscar != null) {
    		Hashtable<vertice, String> estadoNodos = new Hashtable<vertice, String>();
        	Hashtable<vertice,Boolean> afines = new Hashtable<vertice,Boolean>();
        	
        	for (vertice actual : vertices) {
        		afines.put(actual, false);
        		estadoNodos.put(actual, "blanco");
    		}	
        	
        	buscarAfines(buscar,afines,estadoNodos,buscar);
        	
        	for (vertice clave : afines.keySet()) {
    			if (afines.get(clave)) {
    				arrRet.add(clave);
    			}
    		}
		}
    	return arrRet;
    }
}
