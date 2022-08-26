package ar.edu.itba.paw.sia;

public class CruceUniforme extends Cruce {
	@Override
	public void cruzar(Genotipo a , Genotipo b) {
		int size = a.getSize();
		//double p = random_p(); // esto deberia ser configurable ?
		double p = 0.5;
		for(int i = 0 ; i <= size ; i++) {
			double pi = random_p();
			if(pi < p) {
				Object aux = a.getCromosoma()[i];
				a.getCromosoma()[i] = b.getCromosoma()[i];
				b.getCromosoma()[i] = aux;
			}
		}
	}
}
