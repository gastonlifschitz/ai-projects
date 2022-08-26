package ar.edu.itba.paw.sia.seleccion;

import ar.edu.itba.paw.sia.FitnessSort;
import ar.edu.itba.paw.sia.PersonajeImpl;

import java.util.ArrayList;

public class Elite implements Seleccion {
    private int nElitismo;
    public Elite(int n_elitismo){
        this.nElitismo = n_elitismo;
    }
    @Override
    public PersonajeImpl[] select(PersonajeImpl[] poblacion) {
        //Primero: ordeno segun fitness
        ArrayList<PersonajeImpl> posiblesPadres = new ArrayList<>();

        for(int i = 0;i<poblacion.length;i++){
            posiblesPadres.add(poblacion[i]);
        }
        //Sort by fitness
        posiblesPadres.sort(new FitnessSort());

        PersonajeImpl[] ret = new PersonajeImpl[nElitismo];
        for(int j = 0;j<nElitismo;j++){
            ret[j] = posiblesPadres.get(j);
        }
        return ret;
    }
    public void setK(int k) {
        nElitismo = k;
    }
}
