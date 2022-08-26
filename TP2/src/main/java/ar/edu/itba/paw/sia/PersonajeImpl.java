package ar.edu.itba.paw.sia;

import java.util.Objects;

public abstract class PersonajeImpl implements Personaje, Comparable<PersonajeImpl>  {
	
	private Item arma;
	private Item bota;
	private Item casco;
	private Item guante;
	private Item pechera;
	private double altura;
	protected static TSVReader tsvReader;
	
	public PersonajeImpl(Item arma, Item bota, Item casco, Item guante, Item pechera, double altura,TSVReader tsvReader){
		this.altura=altura;
		this.arma=arma;
		this.bota=bota;
		this.guante=guante;
		this.pechera=pechera;
		this.casco=casco;
		this.tsvReader=tsvReader;
	}
	
	public double fp() {
		return (arma.getFuerza() + bota.getFuerza() + casco.getFuerza() + guante.getFuerza() + pechera.getFuerza());
	}
	
	public double ap() {
		return (arma.getAgilidad() + bota.getAgilidad() + casco.getAgilidad() + guante.getAgilidad() + pechera.getAgilidad());
	}
	
	public double pp() {
		return (arma.getPericia() + bota.getPericia() + casco.getPericia() + guante.getPericia() + pechera.getPericia());
	}
	
	public double rp() {
		return (arma.getResistencia() + bota.getResistencia() + casco.getResistencia() + guante.getResistencia() + pechera.getResistencia());
	}
	
	public double vp() {
		return (arma.getVida() + bota.getVida() + casco.getVida() + guante.getVida() + pechera.getVida());
	}
	
	public double ataque() {
		return (agilidad() + pericia())*fuerza()*ATM();
	}

	public double defensa() {
		return (resistencia() + pericia())*vida()*DEM() ;
	}

	public double ATM() {
		return 0.7 - Math.pow(3*altura-5,4) + Math.pow(3*altura-5,2) + (altura/4);
	}

	public double DEM() {
		return 1.9 + Math.pow(2.5*altura-4.16,4) - Math.pow(2.5*altura-4.16,2) - ((3*altura)/10) ;
	}

	public double fuerza() {
		return 100*Math.tanh(0.01*fp());
	}

	public double agilidad() {
		return Math.tanh(0.01*ap());
	}

	public double pericia() {
		return 0.6*Math.tanh(0.01*pp());
	}

	public double resistencia() {
		return Math.tanh(0.01*rp());
	}

	public double vida() {
		return 100*Math.tanh(0.01*vp());
	}

	public Genotipo getGenotipo(){
		Genotipo ret = new Genotipo();
		Object [] array = {arma, bota, guante, pechera, casco, altura};
		ret.setCromosoma(array);
		return ret;
	}

	public void setGenotipo(Genotipo genotipo){
		Object [] obj = genotipo.getCromosoma();
		this.arma 		= (Item) 	obj[0];
		this.bota 		= (Item) 	obj[1];
		this.guante 	= (Item) 	obj[2];
		this.pechera 	= (Item) 	obj[3];
		this.casco 		= (Item) 	obj[4];
		this.altura 	= (double) 	obj[5];
	}

	public abstract double fitness();

	@Override
	public int compareTo(PersonajeImpl other) {
		if(this.fitness() > other.fitness())
			return 1;
		else if (this.fitness() == other.fitness())
			return 0 ;
		return -1 ;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PersonajeImpl))
			return false;
		PersonajeImpl p = (PersonajeImpl) obj;
		if(!p.guante.equals(this.guante))
			return false;
		if( p.altura != this.altura)
			return false;
		if(!p.casco.equals(this.casco))
			return false;
		if(!p.pechera.equals(this.pechera))
			return false;
		if(!p.arma.equals(this.arma))
			return false;
		return true;
	}

	public Item getBota() {
		return bota;
	}

	public Item getArma() {
		return arma;
	}

	public Item getCasco() {
		return casco;
	}

	public double getAltura() {
		return altura;
	}

	public Item getGuante() {
		return guante;
	}

	public Item getPechera() {
		return pechera;
	}
	
	public String F() {
		return this.fuerza() + "\t";
	}
	
	public String A() {
		return this.agilidad() + "\t";
	}
	
	public String P() {
		return this.pericia() + "\t";
	}
	
	public String R() {
		return this.resistencia() + "\t";
	}
	
	public String V() {
		return this.vida() + "\t";
	}
	
	public String animationBar() {
		String b = this.fuerza() + "\t";
		String c = this.agilidad() + "\t";
		String d = this.pericia() + "\t";
		String e = this.resistencia() + "\t";
		String f = this.vida() + "\t";
		return b + c + d + e + f + "\n";
	}
	
	public String data() {
		String a = "arma\t" + arma.toString() + "bota\t" + bota.toString() + "guante\t" + guante.toString() + "casco\t" + casco.toString() + "pechera\t" + pechera.toString() + "altura\t" + altura + "\n-";
		return a;
	}
	
	@Override
	public String toString() {
		String a = "\tid\tFu\tAg\tEx\tRe\tVi\n" + "-----------------------------------------------------\n" + "arma\t" + arma.toString() + "bota\t" + bota.toString() + "guante\t" + guante.toString() + "casco\t" + casco.toString() + "pechera\t" + pechera.toString() + "-----------------------------------------------------\n" + "altura\t" + altura;
		String b = "\nF = " + this.fuerza();
		String c = "\nA = " + this.agilidad();
		String d = "\nP = " + this.pericia();
		String e = "\nR = " + this.resistencia();
		String f = "\nV = " + this.vida();
		String g = "\nATM = " + this.ATM() + "\t DEM = " + this.DEM() + "\n"; 
		String h = "\nataque = " + this.ataque() + "\n";
		String i = "\ndefensa = " + this.defensa() + "\n";
		return a + b + c + d + e + f + g + h + i;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(guante,altura,casco,pechera,arma);
	}
}
