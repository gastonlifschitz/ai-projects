package ar.edu.itba.paw.sia.seleccion;

import ar.edu.itba.paw.sia.PersonajeImpl;

import java.util.Arrays;
import java.util.Random;

public class Ranking implements Seleccion {
    private int K;

    public Ranking(int K){
        this.K = K;
    }

    @Override
    public PersonajeImpl[] select(PersonajeImpl[] poblacion) {
        //Ordeno poblacion segun fitness
        Arrays.sort(poblacion);

        int size = poblacion.length;
        int denominator = (size * (size+1)) / 2;
        double [] fitness = new double[size];
        double [] accumulatedFitness = new double[size];
        double accum = 0;
        for(int i = 0;i<size;i++){
            fitness[i] = (i+1)/(double)denominator;
            accum += fitness[i];
            accumulatedFitness[i] = accum;
        }

        //IDEM RULETA
        PersonajeImpl[] ret = new PersonajeImpl[K];
        /*
        [a,b,c,d,e,f] e=0,9 => [e,e,e]
         */
        for(int i = 0;i<K;i++){
            double random = random_p();
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
