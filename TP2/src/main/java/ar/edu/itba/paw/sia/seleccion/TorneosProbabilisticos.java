package ar.edu.itba.paw.sia.seleccion;

import ar.edu.itba.paw.sia.PersonajeImpl;

import java.util.Random;

public class TorneosProbabilisticos implements Seleccion{
    private int K;
    public TorneosProbabilisticos(int k){
        this.K=k;
    }

    @Override
    public PersonajeImpl[] select(PersonajeImpl[] poblacion) {
        double threshold = 0.5 + Math.random() * (1.0 - 0.5);
        threshold = 0.075;
        PersonajeImpl[] ret = new PersonajeImpl[K];
        int size = poblacion.length;
        Random random = new Random();
        for(int i = 0;i<K;i++){
            int random1 = random.nextInt(size);
            int random2 = random.nextInt(size);
            double randomValue = 0.0 + Math.random() * (1.0 - 0.0);
            if(randomValue < threshold){
                ret[i] = getMostFitted(poblacion[random1],poblacion[random2]);
            }else {
                ret[i] = getLeastFitted(poblacion[random1],poblacion[random2]);
            }
        }
        return ret;
    }
    private PersonajeImpl getMostFitted(PersonajeImpl p1, PersonajeImpl p2){
        if(p1.fitness() > p2.fitness())
            return p1;
        return p2;
    }
    private PersonajeImpl getLeastFitted(PersonajeImpl p1, PersonajeImpl p2){
        if(p1.fitness() > p2.fitness())
            return p1;
        return p2;
    }
    public void setK(int k) {
        K = k;
    }
}
