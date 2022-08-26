package ar.edu.itba.paw.sia.seleccion;

import ar.edu.itba.paw.sia.PersonajeImpl;

public interface Seleccion {
    PersonajeImpl[] select(PersonajeImpl[] poblacion);
    void setK(int k);
}
