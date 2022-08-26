package ar.edu.itba.paw.sia;

import java.util.Objects;

public class Item {
	private double fuerza;
	private double agilidad;
	private double pericia;
	private double resistencia;
	private double vida;
	private int id;

	public Item(Item item){
		this.fuerza = item.getFuerza();
		this.agilidad=item.getAgilidad();
		this.resistencia=item.getResistencia();
		this.pericia=item.getPericia();
		this.vida=item.getVida();
		this.id = item.getId();
	}

	public Item(double fuerza,double agilidad, double pericia,double resistencia, double vida, int id){
		this.fuerza=fuerza;
		this.agilidad=agilidad;
		this.resistencia=resistencia;
		this.pericia=pericia;
		this.vida=vida;
		this.id = id;
	}

	public double getAgilidad() {
		return agilidad;
	}

	public double getFuerza() {
		return fuerza;
	}

	public double getPericia() {
		return pericia;
	}

	public double getResistencia() {
		return resistencia;
	}

	public double getVida() {
		return vida;
	}
	public static Item randomArma(TSVReader tsvReader){
		return randomItem("armas.tsv",tsvReader);
	}
	public static Item randomBota(TSVReader tsvReader){
		return randomItem("botas.tsv",tsvReader);
	}
	public static Item randomCasco(TSVReader tsvReader){
		return randomItem("cascos.tsv",tsvReader);
	}
	public static Item randomGuante(TSVReader tsvReader){
		return randomItem("guantes.tsv",tsvReader);
	}
	public static Item randomPechera(TSVReader tsvReader){
		return randomItem("pecheras.tsv",tsvReader);
	}

	private static Item randomItem(String file,TSVReader tsvReader){
		int id = (int) (Math.random() * (999999 - 1)) + 1;
		double [] values = tsvReader.goToId(id,file);
		return new Item(values[0], values[1],values[2],values[3],values[4],(int)values[5]);
	}
	
	public static Item fixedItem(String file,TSVReader tsvReader,int id) {
		double [] values = tsvReader.goToId(id,file);
		return new Item(values[0], values[1],values[2],values[3],values[4],(int)values[5]);
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n",id,fuerza,agilidad,pericia,resistencia,vida);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Item))
			return false;
		Item p = (Item) obj;
		if(p.pericia - this.pericia < 0.0001)
			return false;
		if(p.resistencia - this.resistencia < 0.0001)
			return false;
		if(p.agilidad - this.agilidad < 0.0001)
			return false;
		if(p.fuerza - this.fuerza < 0.0001)
			return false;
		if(p.vida - this.vida < 0.0001)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pericia,resistencia,agilidad,fuerza,vida);
	}
	public void setVida(double value) {
		this.vida = value;
		
	}
}

