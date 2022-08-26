package ar.edu.itba.paw.sia;

public class CruceDeUnPunto extends Cruce {
	public CruceDeUnPunto(){}
	public void de_un_punto(Genotipo a , Genotipo b , int p) {
		int size = a.getSize();		
		for(int i = 0 ; i <= size ; i++) {
			if(i > p) {
				Object aux = a.getCromosoma()[i];
				a.getCromosoma()[i] = b.getCromosoma()[i];
				b.getCromosoma()[i] = aux;
			}
		}
	}
	@Override
	public void cruzar(Genotipo a , Genotipo b) {
		int size = a.getSize();
		int p = random_locus(size);
		de_un_punto(a,b,p);
	}
}
