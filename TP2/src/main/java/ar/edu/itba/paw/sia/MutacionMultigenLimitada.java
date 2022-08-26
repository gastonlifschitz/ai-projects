package ar.edu.itba.paw.sia;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MutacionMultigenLimitada extends Mutacion {
	
	List<Integer> list = new ArrayList<>();
	
	public MutacionMultigenLimitada(TSVReader tsvReader) {
		super(tsvReader);
		// TODO Auto-generated constructor stub
	}

	//Mutación Multigen Limitada: Se selecciona una cantidad [1,M] (azarosa) de genes para mutar, con probabilidad Pm.
	@Override
	public void mutar(Genotipo g) {
		list.clear();
		for(int i = 0 ; i < 6 ; i++) {
			list.add(i);
		}
		int size = g.getSize();
		int m = random_locus(size);
		Collections.shuffle(list);
		for(int i = 0 ; i < m ; i++) {
			double pi = random_p();
			if(pi < Pm) {
				alter2(g,list.get(i));
			}
		}
	}
}
