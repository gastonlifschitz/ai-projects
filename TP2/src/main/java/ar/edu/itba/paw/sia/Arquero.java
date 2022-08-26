package ar.edu.itba.paw.sia;

import java.util.Random;

public class Arquero extends PersonajeImpl {
	public Arquero(TSVReader tsvReader){
		super(Item.randomArma(tsvReader), Item.randomBota(tsvReader), Item.randomCasco(tsvReader), Item.randomGuante(tsvReader), Item.randomPechera(tsvReader), randomHeight(),tsvReader);
	}
	public Arquero(TSVReader tsvReader , int id, String filename){
		super(Item.fixedItem(filename,tsvReader,id), Item.fixedItem(filename,tsvReader,id), Item.fixedItem(filename,tsvReader,id), Item.fixedItem(filename,tsvReader,id), Item.fixedItem(filename,tsvReader,id), 1.5 /*randomHeight()*/,tsvReader);
	}
	public Arquero(Arquero arquero, TSVReader tsvReader){
		super(new Item(arquero.getArma()), new Item(arquero.getBota()),new Item(arquero.getCasco()), new Item(arquero.getGuante()), new Item(arquero.getPechera()), arquero.getAltura(), tsvReader);
	}
	public static double randomHeight(){
		Random r = new Random();
		double randomValue = 1.3 + (2.0 - 1.3) * r.nextDouble();
		double roundedValue = Math.round(randomValue * 10) / 10.0;
		return roundedValue;
	}
	public double fitness() {
		return 0.9*ataque() + 0.1*defensa();
	}
}
