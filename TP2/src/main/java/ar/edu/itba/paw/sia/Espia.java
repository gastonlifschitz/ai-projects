package ar.edu.itba.paw.sia;

import java.util.Random;

public class Espia extends PersonajeImpl {
	public Espia(TSVReader tsvReader){
		super(Item.randomArma(tsvReader), Item.randomBota(tsvReader), Item.randomCasco(tsvReader), Item.randomGuante(tsvReader), Item.randomPechera(tsvReader), randomHeight(),tsvReader);
	}

	public Espia(Espia espia, TSVReader tsvReader){
		super(new Item(espia.getArma()), new Item(espia.getBota()),new Item(espia.getCasco()), new Item(espia.getGuante()), new Item(espia.getPechera()), espia.getAltura(), tsvReader);
	}

	public static double randomHeight(){
		Random r = new Random();
		double randomValue = 1.3 + (2.0 - 1.3) * r.nextDouble();
		double roundedValue = Math.round(randomValue * 10) / 10.0;
		return roundedValue;
	}
	public double fitness() {
		return 0.8*ataque() + 0.3*defensa();
	}

}
