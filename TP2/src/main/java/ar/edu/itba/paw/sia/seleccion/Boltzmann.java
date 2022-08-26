package ar.edu.itba.paw.sia.seleccion;

import ar.edu.itba.paw.sia.PersonajeImpl;

import java.util.ArrayList;
import java.util.Random;

public class Boltzmann implements Seleccion {
    private double temperature;
    private int K, generacion = 0;
    private double Tc = 0.2;
    public Boltzmann(int K){
        temperature = 0.95;
        this.K=K;
    //Default value to start
    }

    @Override
    public PersonajeImpl[] select(PersonajeImpl[] poblacion) {
        int size = poblacion.length;
        double[] fitnesses = new double[size]; 	//Guarda el fitness de la poblacion
        double denominator = 0; 			//suma de todas las probabilidades
        double runningTotal = 0; 			//Acumulada
        double randDouble; 					//random double
        Random random = new Random();		//for randomness
        double sum_array = 0;

        //gets array of fitnesses to be sorted
        for (int i = 0; i < size; i++) {
            fitnesses[i] = poblacion[i].fitness();
            sum_array += fitnesses[i];

        }

        double[] relativeFitness = new double[poblacion.length];
        for(int j=0;j<poblacion.length;j++){
            relativeFitness[j]      = fitnesses[j]/sum_array;
            denominator += Math.exp(relativeFitness[j]/temperature);
        }

        //Elijo un numero random
        randDouble = random.nextDouble();

        PersonajeImpl[] breedingPool = new PersonajeImpl[K];
        for (int index = 0 ;index<K ;index++){
            //Elijo un numero random
            randDouble = random.nextDouble();
            for (int i = 0; i < size; i++) {
                double percentEntry = Math.exp(relativeFitness[i]/temperature)/denominator; //generate probability for this individual
                runningTotal += percentEntry;	//add to running total
                if(randDouble < runningTotal) { 	//if our random double is less than the running total
                    breedingPool[index] = poblacion[i]; 		//add to array
                    runningTotal = 0; 								//clear running total
                    randDouble = random.nextDouble(); 				//get a new random double
                    i = Math.abs(random.nextInt(size)); 						//send i to random start location
                }
            }
        }

        generacion++;
        //Refresh temperature
        randDouble = random.nextDouble();
        if(randDouble > 0.9){
            //0.5 -> Decay factor
            temperature = Tc + (1-Tc) * Math.exp(-0.5*generacion);
        }
        return breedingPool;
    }

    public void setK(int k) {
        K = k;
    }
}
