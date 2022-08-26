package ar.edu.itba.paw.sia;

import java.util.ArrayList;

public class Generacion {
    private PersonajeType personaje;
    private int cantidadPoblacion;
    private PersonajeImpl[] poblacion;
    
    TSVReader tsvReader;
    
    public Generacion(int cantidadPoblacion, PersonajeType personaje , TSVReader tsvReader){
        this.cantidadPoblacion=cantidadPoblacion;
        this.personaje=personaje;
        this.poblacion = new PersonajeImpl[cantidadPoblacion];
        this.tsvReader = tsvReader;
    }

    public PersonajeImpl[] getPoblacion() {
        return poblacion;
    }

    public void poblarIncial(){
        double altura = 2.0;
        for(int i = 0 ; i < poblacion.length ; i++) {
            switch (personaje){
                case ESPIA:
                    this.poblacion[i] = new Espia(tsvReader);
                    break;
                case ARQUERO:
                    this.poblacion[i] = new Arquero(tsvReader);
                    break;
                case DEFENSOR:
                    this.poblacion[i] = new Defensor(tsvReader);
                    break;
                case GUERRERO:
                    this.poblacion[i] = new Guerrero(tsvReader);
                    break;
                default:
                    return;
            }
        }
    }

    public PersonajeImpl[] deepCopy(PersonajeImpl[] parentsSelected1, PersonajeImpl[] parentsSelected2){
        PersonajeImpl[] deepCopy = new PersonajeImpl[parentsSelected1.length + parentsSelected2.length];

        for (int j = 0; j < parentsSelected1.length + parentsSelected2.length; j++) {
            if (j < parentsSelected1.length){
                switch (personaje){
                    case ESPIA:
                        deepCopy[j] = new Espia((Espia) parentsSelected1[j], tsvReader);
                        break;
                    case ARQUERO:
                        deepCopy[j] = new Arquero((Arquero) parentsSelected1[j], tsvReader);
                        break;
                    case DEFENSOR:
                        deepCopy[j] = new Defensor((Defensor) parentsSelected1[j], tsvReader);
                        break;
                    case GUERRERO:
                    default:
                        deepCopy[j] = new Guerrero((Guerrero) parentsSelected1[j], tsvReader);
                }
            }else{
                switch (personaje){
                    case ESPIA:
                        deepCopy[j] = new Espia((Espia) parentsSelected1[j - parentsSelected1.length], tsvReader);
                        break;
                    case ARQUERO:
                        deepCopy[j] = new Arquero((Arquero) parentsSelected1[j - parentsSelected1.length], tsvReader);
                        break;
                    case DEFENSOR:
                        deepCopy[j] = new Defensor((Defensor) parentsSelected1[j - parentsSelected1.length], tsvReader);
                        break;
                    case GUERRERO:
                    default:
                        deepCopy[j] = new Guerrero((Guerrero)parentsSelected1[j - parentsSelected1.length], tsvReader);
                }
            }
        }
        return deepCopy;
    }

    public PersonajeImpl getPersonajeConMasFitness(){
        PersonajeImpl res=poblacion[0];
        for(int i = 1;i<poblacion.length;i++){
            double au1 = poblacion[i].fitness(), au2 = res.fitness();
            if( au1 >  au2){
                res = poblacion[i];
            }
        }
        return res;
    }
    public PersonajeImpl getPersonaje(int i) {
        return this.poblacion[i];
    }
    public void setPersonaje(PersonajeImpl p, int index){
        this.poblacion[index] = p;
    }

    public void setGeneracion(PersonajeImpl[] poblacion){
        this.poblacion = poblacion;
    }
}
