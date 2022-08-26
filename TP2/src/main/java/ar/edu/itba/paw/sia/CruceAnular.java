package ar.edu.itba.paw.sia;

public class CruceAnular extends Cruce {
	@Override
	public void cruzar(Genotipo a , Genotipo b) {
		int size = a.getSize();
		int p = random_locus(size - 1);
		int l = random_length(size);
		anular(a,b,p/2,l);
	}
	public void anular(Genotipo a , Genotipo b , int p , int l) {
		int size = a.getSize();
		int q = 0;
		for(int i = 0 ; i <= size ; i++) {
			if(i > p) {
				Object aux = a.getCromosoma()[i];
				a.getCromosoma()[i] = b.getCromosoma()[i];
				b.getCromosoma()[i] = aux;	
				q++;
			}
		}
		int i = 0;
		while(q < l) {
			Object aux = a.getCromosoma()[i];
			a.getCromosoma()[i] = b.getCromosoma()[i];
			b.getCromosoma()[i] = aux;
			q++;
			i++;
		}
	}
}
