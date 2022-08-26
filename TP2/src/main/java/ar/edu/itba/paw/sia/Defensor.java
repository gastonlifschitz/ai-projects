package ar.edu.itba.paw.sia;

import java.util.Random;

public class Defensor extends PersonajeImpl {
	public Defensor(TSVReader tsvReader){
		super(Item.randomArma(tsvReader), Item.randomBota(tsvReader), Item.randomCasco(tsvReader), Item.randomGuante(tsvReader), Item.randomPechera(tsvReader), randomHeight(),tsvReader);
	}
	public Defensor(Defensor defensor, TSVReader tsvReader){
		super(new Item(defensor.getArma()), new Item(defensor.getBota()),new Item(defensor.getCasco()), new Item(defensor.getGuante()), new Item(defensor.getPechera()), defensor.getAltura(), tsvReader);
	}
	public static double randomHeight(){
		Random r = new Random();
		double randomValue = 1.3 + (2.0 - 1.3) * r.nextDouble();
		double roundedValue = Math.round(randomValue * 10) / 10.0;
		return roundedValue;
	}
	public double fitness() {
		return 0.3*ataque() + 0.8*defensa();
	}
}
