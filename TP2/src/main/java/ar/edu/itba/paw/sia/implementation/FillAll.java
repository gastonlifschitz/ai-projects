package ar.edu.itba.paw.sia.implementation;

import ar.edu.itba.paw.sia.PersonajeImpl;
import ar.edu.itba.paw.sia.seleccion.Seleccion;

public class FillAll implements Fill {
    private int populationSize, K, firstKValues, lastKValues;
    private double b;
    private Seleccion metodo3, metodo4;
    public FillAll(int populationSize, Seleccion metodo3, Seleccion metodo4, int K, double b){
        this.populationSize=populationSize;
        this.metodo3 = metodo3;
        this.metodo4 = metodo4;
        this.K = K;
        this.b = b;
        firstKValues = (int)(populationSize*b);
        lastKValues = populationSize-firstKValues;
    }
    @Override
    public PersonajeImpl[] fill(PersonajeImpl[] parent, PersonajeImpl[] children) {
        PersonajeImpl[] ret     = new PersonajeImpl[populationSize];
        PersonajeImpl[] temp    = new PersonajeImpl[populationSize + K];

        for(int j = 0;j<K + populationSize;j++){
            if(j<parent.length)
                temp[j] = parent[j];
            else
                temp[j] = children[j-parent.length];
        }
        metodo3.setK(firstKValues);
        metodo4.setK(lastKValues);

        PersonajeImpl[] firstPart = metodo3.select(temp);
        PersonajeImpl[] lastPart = metodo4.select(temp);

        for(int j = 0;j<populationSize;j++){
            if(j<firstPart.length)
                ret[j] = firstPart[j];
            else
                ret[j] = lastPart[j-firstPart.length];
        }

        return ret;
    }
}
