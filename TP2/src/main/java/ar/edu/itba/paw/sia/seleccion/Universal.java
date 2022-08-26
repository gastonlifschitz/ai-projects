package ar.edu.itba.paw.sia.seleccion;

import ar.edu.itba.paw.sia.PersonajeImpl;

import java.util.Random;

public class Universal implements Seleccion {
    private int K;
    public Universal(int K){
        this.K=K;
    }
    @Override
    public PersonajeImpl[] select(PersonajeImpl[] poblacion) {
        double[] fitnessArray = new double[poblacion.length];
        double sum_array = 0;
        for(int i=0;i<poblacion.length;i++){
            double value = poblacion[i].fitness();
            fitnessArray[i] = value;
            sum_array += value;
        }
        double[] relativeFitness = new double[poblacion.length];
        double[] accumulatedFitness = new double[poblacion.length];
        double accum = 0;
        for(int j=0;j<poblacion.length;j++){
            relativeFitness[j]      = fitnessArray[j]/sum_array;
            accum += relativeFitness[j];
            accumulatedFitness[j] = accum;
        }

        PersonajeImpl[] ret = new PersonajeImpl[K];
        for(int i = 0;i<K;i++){
            double random = random_p();
            double randomJ = (random + i)/K;
            boolean found = false;
            for(int j = 0;j<poblacion.length && !found;j++){
                if(accumulatedFitness[j] >= random){
                    ret[i] = poblacion[j];
                    found = true;
                }
            }
        }
        return ret;
    }
    private static double random_p() {
        Random rand = new Random();
        return rand.nextDouble();
    }
    public void setK(int k) {
        K = k;
    }
}
