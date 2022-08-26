package ar.edu.itba.paw.sia;

import ar.edu.itba.paw.sia.Personaje;
import ar.edu.itba.paw.sia.PersonajeImpl;

import java.util.Comparator;

public class FitnessSort implements Comparator<PersonajeImpl>
{

    private Personaje o1;

    @Override
    public int compare(PersonajeImpl o1, PersonajeImpl o2) {
        return ((Double) o2.fitness()).compareTo(o1.fitness());
    }
}