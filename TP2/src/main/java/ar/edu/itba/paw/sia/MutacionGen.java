package ar.edu.itba.paw.sia;

public class MutacionGen extends Mutacion {
	public MutacionGen(TSVReader tsvReader) {
		super(tsvReader);
		// TODO Auto-generated constructor stub
	}

	//Mutación de Gen: Se altera un solo gen con una probabilidad Pm.
	@Override
	public void mutar(Genotipo g) {
		int size = g.getSize();
		int l = random_locus(size);
		double pi = random_p();
		if(pi < Pm) {
			alter2(g,l);
		}
	}
}
