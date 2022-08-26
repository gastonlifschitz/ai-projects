package ar.edu.itba.paw.sia.seleccion;

import ar.edu.itba.paw.sia.Personaje;
import ar.edu.itba.paw.sia.PersonajeImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class TorneosDeterministicos implements Seleccion {
    private int K;
    public TorneosDeterministicos(int K){ this.K=K;}
    
    @Override
    public PersonajeImpl[] select(PersonajeImpl[] poblacion) {
    	PersonajeImpl[] ret = new PersonajeImpl[K];
    	int N = poblacion.length;
    	List<PersonajeImpl> l = new ArrayList<>();
    	for(int i = 0 ; i < N ; i++) {
    		l.add(poblacion[i]);
    	}
        Random rand = new Random();
		int M = 9;
    	//int M = rand.nextInt(N-1) + 1;
		if(poblacion.length== 1)
			return poblacion;
        for(int i = 0 ; i < K ; i++) {
			//int M = rand.nextInt((N/2)-1) + 1;
        	//int M = N/2;
        	PersonajeImpl p = pick(l,M);
        	ret[i] = p;
        }
        return ret;
    }
    
    private PersonajeImpl pick(List<PersonajeImpl> poblacion , int M) {
    	PersonajeImpl ret;
		Collections.shuffle(poblacion);
		ret = poblacion.get(0);
		for(int i = 1;i<M;i++) {
			if(ret.fitness() < poblacion.get(i).fitness()) {
				ret = poblacion.get(i);
			}
		}
		return ret;
	}
    
    public void setK(int k) {
        K = k;
    }
}

