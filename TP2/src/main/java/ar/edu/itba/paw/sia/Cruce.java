package ar.edu.itba.paw.sia;

import java.util.Random;

public abstract class Cruce {
	
	public Cruce() {
		
	}
	
	public int random_locus(int max) {
		//generate a random integer between 0 and max (it will be the locus)
		Random rand = new Random();
		int rand_int = rand.nextInt(max);// Generate random integers in range 0 to max - 1
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
	
	public abstract void cruzar(Genotipo x , Genotipo y);
	
}
