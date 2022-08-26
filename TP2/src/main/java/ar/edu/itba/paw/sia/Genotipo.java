package ar.edu.itba.paw.sia;

public class Genotipo {
	private static int size = 5;
	private Object [] cromosoma = new Object[size];
	//Example: [Id Arma , Id Pechera , Id Casco , Id Bota , Id Guante , Gen Altura] -> ! No necesariamente es este orden pero CREO que el orden no importa
	public Genotipo() {
		//setCromosoma(new Object[size]);
		/*
		IMPORTANTE: el valor de size esta predeterminado y NO es configurable dado que no
		van a cambiar la cantidad de genes.
		*/
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("");
		for(int i = 0 ; i <= size ; i++){
			ret.append(cromosoma[i] + " | ");
		}
		return ret.toString();
	}

	public Object [] getCromosoma() {
		return cromosoma;
	}
	
	public void setItem(Item item , int l) {
		this.cromosoma[l] = item;
	}
	
	public void setHeight(double x) {
		this.cromosoma[5] = x;
	}
	
	public void setCromosoma(Object [] cromosoma) {
		this.cromosoma = cromosoma;
	}

	public int getSize() {
		return size;
	}
	
}
