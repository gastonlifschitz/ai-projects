package ar.edu.itba.paw.sia;

import ar.edu.itba.paw.sia.seleccion.Seleccion;

public class MutacionCompleta extends Mutacion {
	public MutacionCompleta(TSVReader tsvReader) {
		super(tsvReader);
		// TODO Auto-generated constructor stub
	}

	//Mutación Completa: Con una probabilidad P_m, se mutan todos los genes del individuo, acorde a la función de mutación definida para cada gen.
	@Override
	public void mutar(Genotipo g) {
		int size = g.getSize();
		for(int i = 0 ; i < size ; i++) {
			double pi = random_p();
			if(pi < Pm) {
				alter2(g,i);
			}
		}
	}
}
