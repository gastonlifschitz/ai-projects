package ar.edu.itba.paw.sia;

public class CruceDeDosPuntos extends Cruce {
	public void de_dos_puntos(Genotipo a , Genotipo b , int p1 , int p2) {
		int size = a.getSize();
		for(int i = 0 ; i <= size ; i++) {
			if(i > p1 && i <= p2) {
				Object aux = a.getCromosoma()[i];
				a.getCromosoma()[i] = b.getCromosoma()[i];
				b.getCromosoma()[i] = aux;	
			}
		}
	}
	@Override
	public void cruzar(Genotipo a , Genotipo b) {
		int size = a.getSize();
		int p1 = random_locus(size);
		int p2 = random_locus(size);
		de_dos_puntos(a,b,p1,p2);
	}
}
