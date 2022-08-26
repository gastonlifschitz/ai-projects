package ar.edu.itba.paw.sia.implementation;

import ar.edu.itba.paw.sia.PersonajeImpl;
import ar.edu.itba.paw.sia.seleccion.Seleccion;

public class FillParent implements Fill {
    private int populationSize, K, firstKValues, lastKValues;
    private double b;
    private Seleccion metodo3, metodo4;
    public FillParent(int populationSize, Seleccion metodo3, Seleccion metodo4, int K, double b){
        this.populationSize=populationSize;
        this.metodo3 = metodo3;
        this.metodo4 = metodo4;
        this.K = K;
        this.b = b;
        firstKValues = (int)(K*b);
        lastKValues = K-firstKValues;
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


        if(K >= populationSize){
            for(int i = 0;i<populationSize;i++){
                ret[i] = children[i];
            }
            return ret;
        }else{
            int i;
            for(i = 0;i<children.length;i++){
                ret[i] = children[i];
            }
            int newK = populationSize-i;
            firstKValues = (int)(newK*b);
            lastKValues = newK-firstKValues;
            metodo3.setK(firstKValues);
            metodo4.setK(lastKValues);

            PersonajeImpl[] firstPart = metodo3.select(temp);
            PersonajeImpl[] lastPart = metodo4.select(temp);

            int counter = 0;
            for(int j = i;j<populationSize;j++){
                if(counter<firstPart.length){
                    ret[j] = firstPart[counter];
                }else{
                    ret[j] = lastPart[counter-firstPart.length];
                }
                counter++;
            }

        }
        return ret;
    }

}
