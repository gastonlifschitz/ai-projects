package ar.edu.itba.paw.sia;

import java.util.ArrayList;

public class MutacionMultigenUniforme extends Mutacion {
	public MutacionMultigenUniforme(TSVReader tsvReader) {
		super(tsvReader);
		// TODO Auto-generated constructor stub
	}

	//Mutación Multigen Uniforme: Cada gen tiene una probabilidad Pm de ser mutado.
	@Override
	public void mutar(Genotipo g) {
		int size = g.getSize();
		int m = random_locus(size);
		ArrayList<Double> pmi = new ArrayList<Double>();
		
		for(int i = 0 ; i < size ; i++) {
			pmi.add(random_p());
		}
		
		for(int i = 0 ; i < m ; i++) {
			if(pmi.get(i) < Pm) {
				alter2(g,i);
			}
		}
	}
}
