package ar.edu.itba.paw.sia;

import java.util.Random;

public class Guerrero extends PersonajeImpl {
	public Guerrero(TSVReader tsvReader){
		super(Item.randomArma(tsvReader), Item.randomBota(tsvReader), Item.randomCasco(tsvReader), Item.randomGuante(tsvReader), Item.randomPechera(tsvReader), randomHeight(),tsvReader);
	}
	public Guerrero(Guerrero guerrero, TSVReader tsvReader){
		super(new Item(guerrero.getArma()), new Item(guerrero.getBota()),new Item(guerrero.getCasco()), new Item(guerrero.getGuante()), new Item(guerrero.getPechera()), guerrero.getAltura(), tsvReader);
	}
	public double fitness() {
		double aux = 0.6*ataque() + 0.6 * defensa();
		return aux;
	}

	public static double randomHeight(){
		Random r = new Random();
		double randomValue = 1.3 + (2.0 - 1.3) * r.nextDouble();
		double roundedValue = Math.round(randomValue * 10) / 10.0;
		return roundedValue;
	}
}


