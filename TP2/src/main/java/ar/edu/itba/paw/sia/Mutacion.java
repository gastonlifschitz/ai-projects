package ar.edu.itba.paw.sia;

import java.util.ArrayList;
import java.util.Random;

public abstract class Mutacion {
	private TSVReader tsvReader;
	public double Pm;
	public double getPm() {
		return Pm;
	}

	public void setPm(double pm) {
		Pm = pm;
	}

	public Mutacion(TSVReader tsvReader) {
		this.tsvReader = tsvReader;
	}
	
	boolean sentido = true;//true:sumar , false:restar
	char distribucion = 'G';//Gaussiana
	
	public int random_locus(int max) {
		//generate a random integer between 0 and max (it will be the locus)
		Random rand = new Random();
		int rand_int = rand.nextInt(max+1);// Generate random integers in range 0 to max - 1
		return rand_int;
	}
	
	public int random_length(int max) {
		//generate a random integer between 0 and max / 2 (it will be the length)
		Random rand = new Random();
		int rand_int = rand.nextInt(max/2);// Generate random integers in range 0 to max/2 - 1
		return rand_int;
	}
	
	public double random_p() {
		Random rand = new Random();
		return rand.nextDouble();
	}
	
	public double nuevo_gen(double value) {
		return delta(sentido,distribucion,value);
	}
	
	public double delta(boolean sentido , char distribucion , double value) {
		Random r = new Random();
		double d = 0;
		double ret = value;
		if(distribucion=='G') {
			d = r.nextGaussian();
		}
		if(sentido) {
			ret = value*(1+d);
		}
		else {
			ret = value*(1-d);
		}
		return ret;
	}
	
	public void alter2(Genotipo g, int l) {
		Item item;
		double x = 1.3;
		switch(l) {
		case 0:
			item = Item.randomArma(tsvReader);
			break;
		case 1:
			item = Item.randomBota(tsvReader);
			break;
		case 2:
			item = Item.randomGuante(tsvReader);
			break;
		case 3:
			item = Item.randomPechera(tsvReader);
			break;
		case 4:
			item = Item.randomCasco(tsvReader);
			break;
		case 5:
			x = randomHeight();
			g.setHeight(x);
			return;
		default:
			item = null;
			//THROW EXCEPTION
			break;
	}
		
		g.setItem(item, l);
	}
	
	private static double randomHeight(){
		Random r = new Random();
		double randomValue = 1.3 + (2.0 - 1.3) * r.nextDouble();
		double roundedValue = Math.round(randomValue * 10) / 10.0;
		return roundedValue;
	}
	
	public void alter(Genotipo g, int l) {
		// TODO Auto-generated method stub
		double value = 0;
		Object aux = g.getCromosoma()[l];
		if(aux.getClass().equals(Item.class)) {
			Item auxItem = (Item)aux;
			// TODO: hacerlo en general para no mutar solo la vida
			Random rand = new Random();
			int rand_int = rand.nextInt(6);
			
			switch(rand_int) {
				case 1:
					value = auxItem.getVida();
					double nuevavida = nuevo_gen(value);
					auxItem.setVida(nuevavida);
					g.getCromosoma()[l] = auxItem ;
					break;
				case 2:
					value = auxItem.getResistencia();
					double nuevaresistencia = nuevo_gen(value);
					auxItem.setVida(nuevaresistencia);
					g.getCromosoma()[l] = auxItem ;
					break;
				case 3:
					value = auxItem.getAgilidad();
					double nuevaagilidad = nuevo_gen(value);
					auxItem.setVida(nuevaagilidad);
					g.getCromosoma()[l] = auxItem ;
					break;
				case 4:
					value = auxItem.getPericia();
					double nuevapericia = nuevo_gen(value);
					auxItem.setVida(nuevapericia);
					g.getCromosoma()[l] = auxItem ;
					break;
				case 5:
					value = auxItem.getFuerza();
					double nuevafuerza = nuevo_gen(value);
					auxItem.setVida(nuevafuerza);
					g.getCromosoma()[l] = auxItem ;
					break;
				default:
					break;
			}
			
			
		}
		if(aux.getClass().equals(double.class)) {
			value = (double) aux;
			value = nuevo_gen(value);
			if(!(value > 1.3 && value < 2.0)) {
				value  = (double) aux;
				g.getCromosoma()[l] = nuevo_gen(value);
			}
		}
		
	}
	
	
	
	public abstract void mutar(Genotipo g);
	
	
	
	
	
}
