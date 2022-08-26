package ar.edu.itba.paw.sia;


import static org.junit.Assert.assertArrayEquals;


import ar.edu.itba.paw.sia.seleccion.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SeleccionTest {
    PersonajeImpl[] poblacion = new PersonajeImpl[20];
    TSVReader tsvReader = new TSVReader();
    int K = 5;
    @Before
    public void setUp(){
        for (int i = 0;i<poblacion.length;i++){
            poblacion[i] = new Arquero(tsvReader);
        }
        System.out.println("Antes de seleccionar:");
        for(int i = 0;i<poblacion.length;i++){
            System.out.println("Element " + i + " has fitness " + poblacion[i].fitness());
        }

    }
    //@Test
    public void testOrderedPoblacion(){
        PersonajeImpl[] poblacion = new PersonajeImpl[3];
        //poblacion[0] = new Arquero();
        //poblacion[1] = new Arquero();
        //poblacion[2] = new Arquero();

        for(int i = 0;i<3;i++){
            System.out.println("Personaje " + i + " con fitness = " +poblacion[i].fitness());
        }
        System.out.println("-----------------------------------");
        Arrays.sort(poblacion);

        for(int i = 0;i<3;i++){
            System.out.println("Personaje " + i + " con fitness = " +poblacion[i].fitness());
        }
    }

    //Every methdo will be tested with arqueros
    //@Test
    public void testBoltzmann(){
        Seleccion seleccion = new Boltzmann(K);
        System.out.println("Selecciono "+ K+" de " + poblacion.length + " ....");

        //TODO: No funciona boltzmann
        PersonajeImpl[] selected = seleccion.select(poblacion);

        for(int i = 0;i<selected.length;i++){
            System.out.println("Element " + i + " has fitness " + selected[i].fitness());
        }

    }
    @Test
    public void testElite(){
        Seleccion seleccion = new Elite(K);
        System.out.println("Selecciono "+ K+" de " + poblacion.length + " ....");


        PersonajeImpl[] selected = seleccion.select(poblacion);

        for(int i = 0;i<selected.length;i++){
            System.out.println("Element " + i + " has fitness " + selected[i].fitness());
        }

    }
    //@Test
    public void testRanking(){
        Seleccion seleccion = new Ranking(K);
        System.out.println("Selecciono "+ K+" de " + poblacion.length + " ....");


        PersonajeImpl[] selected = seleccion.select(poblacion);

        for(int i = 0;i<selected.length;i++){
            System.out.println("Element " + i + " has fitness " + selected[i].fitness());
        }
    }
    //@Test
    public void testRuleta(){
        Seleccion seleccion = new Ruleta(K);
        System.out.println("Selecciono "+ K+" de " + poblacion.length + " ....");


        PersonajeImpl[] selected = seleccion.select(poblacion);

        for(int i = 0;i<selected.length;i++){
            System.out.println("Element " + i + " has fitness " + selected[i].fitness());
        }
    }
    //@Test
    public void testTorneosDeterministicos(){
        Seleccion seleccion = new TorneosDeterministicos(K);
        System.out.println("Selecciono "+ K+" de " + poblacion.length + " ....");

        PersonajeImpl[] selected = seleccion.select(poblacion);

        for(int i = 0;i<selected.length;i++){
            System.out.println("Element " + i + " has fitness " + selected[i].fitness());
        }
    }
    //@Test
    public void testTorneosProbabilisticos(){
        Seleccion seleccion = new TorneosProbabilisticos(K);
        System.out.println("Selecciono "+ K+" de " + poblacion.length + " ....");


        PersonajeImpl[] selected = seleccion.select(poblacion);

        for(int i = 0;i<selected.length;i++){
            System.out.println("Element " + i + " has fitness " + selected[i].fitness());
        }

    }
    //@Test
    public void testUniversal(){
        Seleccion seleccion = new Universal(K);
        System.out.println("Selecciono "+ K+" de " + poblacion.length + " ....");


        PersonajeImpl[] selected = seleccion.select(poblacion);

        for(int i = 0;i<selected.length;i++){
            System.out.println("Element " + i + " has fitness " + selected[i].fitness());
        }
    }


}
