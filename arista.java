package grafo;

public class arista {
	private vertice va;
	private int costo;
	
	public arista(vertice v, int costo){
		this.va = v;
		this.costo = costo;
		}
	
	public int getCosto() {
		return costo;
	}
	public vertice getVa() {
		return va;
	}
	public void incremento(){
		costo++;
	}

}
