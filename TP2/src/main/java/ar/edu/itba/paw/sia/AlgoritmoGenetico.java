package ar.edu.itba.paw.sia;

import ar.edu.itba.paw.sia.corte.CantidadDeGeneraciones;
import ar.edu.itba.paw.sia.corte.CriterioDeCorte;
import ar.edu.itba.paw.sia.corte.Tiempo;
import ar.edu.itba.paw.sia.implementation.Fill;
import ar.edu.itba.paw.sia.implementation.FillAll;
import ar.edu.itba.paw.sia.implementation.FillParent;
import ar.edu.itba.paw.sia.seleccion.*;
import ar.edu.itba.paw.sia.seleccion.Seleccion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AlgoritmoGenetico {
    private int poblacion;
    private int K = 6;
    private int CICLO=0;
    private double mutacionProb;
    private PersonajeType personaje;
    private CriterioDeCorte criterioDeCorte;
    private double a,b;
    private Seleccion metodo1 = new Elite(K),metodo2 = new Ruleta(K),metodo3 = new Universal(K), metodo4 = new Ranking(K);
    private Fill fill = new FillAll(10,metodo3, metodo4,6,0.9);
    private String[] configurations;
    //private static int indexFitness [];
    private static int numeroReproducciones=0; //numero de veces que se realizara el cruce en cada generacion
    
    private Cruce cruce;
    
    private Mutacion mut;
    
    TSVReader tsvReader;
    
    //TODO: K Tiene que ser par
    //TODO: ver que probMutacion sea parametrizable

    public AlgoritmoGenetico(int poblacion, int K, PersonajeType personaje,Motor m,TSVReader tsvReader) throws Exception{
        this.poblacion          = poblacion;
        this.K                  = K;
        this.personaje          = personaje;
        this.mutacionProb       = m.probabilidad_mutacion;
        this.metodo1            = m.metodo1;
        this.metodo2            = m.metodo2;
        this.a                  = m.a;
        this.metodo3            = m.metodo3;
        this.metodo4            = m.metodo4;
        this.b                  = m.b;
        this.fill               = m.implementacion;
        this.criterioDeCorte    = m.criterioDeCorte;
        this.cruce              = m.cruce;
        this.mut                = m.mutacion;
        this.tsvReader			= tsvReader;
        mut.setPm(this.mutacionProb);
    }
    
	public void run() {
        HashMap<Integer, Integer> pecherasMasFrecuentes = new HashMap<>();
        HashMap<Integer, Integer> cascosMasFrecuentes = new HashMap<>();
        HashMap<Integer, Integer> armasMasFrecuentes = new HashMap<>();
        HashMap<Integer, Integer> botasMasFrecuentes = new HashMap<>();
        HashMap<Integer, Integer> guantesMasFrecuentes = new HashMap<>();
        HashMap<Double, Integer> alturaMasFrecuentes = new HashMap<>();

		StringBuilder str = new StringBuilder();//fitness(generaciones)
		StringBuilder str1 = new StringBuilder();
		StringBuilder str2 = new StringBuilder();//Animation Bar
		
        PersonajeImpl mejorPersonaje = null;
        double a2 = 0;
        
        int genMejorPersonaje        = 0;

        int firstKValues = (int) (K * a);
        int lastKValues = K - firstKValues;
        Random r = new Random();

        Generacion currentPoblacion = new Generacion(poblacion, personaje, tsvReader);

        //Puebla la generación con Personajes aleatorios -> Gen 0
        currentPoblacion.poblarIncial();
        int i = 0;

        double startTime = System.currentTimeMillis();

        /**
         * Tiempo, Cantidad de generacion o si despues de N generacion no hubo cambios termino
         */
        int critFitness = Integer.MAX_VALUE; //Esto deberia ser el valor con el que estoy feliz ???????????
        while (currentPoblacion.getPersonajeConMasFitness().fitness() < critFitness)        //Mientras que no exista ningun elemento en la poblacion con la forma maxima
        {
            System.out.println("-------------------------------------------------");
            System.out.println("GENERACION " + CICLO);

            elementosMasFrecuentes(currentPoblacion.getPoblacion(),
                    pecherasMasFrecuentes,
                    cascosMasFrecuentes,
                    armasMasFrecuentes,
                    botasMasFrecuentes,
                    guantesMasFrecuentes,
                    alturaMasFrecuentes
            );

            metodo1.setK(firstKValues);
            metodo2.setK(lastKValues);
            PersonajeImpl[] method1Parents = metodo1.select(currentPoblacion.getPoblacion());
            PersonajeImpl[] method2Parents = metodo2.select(currentPoblacion.getPoblacion());

            //Deep copy -> No hago referencia a los padres verdaderos
            PersonajeImpl[] selectedParents = currentPoblacion.deepCopy(method1Parents,method2Parents);

            PersonajeImpl[] copySelectedParents = currentPoblacion.deepCopy(method1Parents,method2Parents);

            //Shuffle selected parents!!
            shuffleArray(selectedParents);

            for (i = 0; i < selectedParents.length; i += 2) {

                PersonajeImpl padre1 = selectedParents[i];
                PersonajeImpl padre2 = selectedParents[i + 1];

                //Cruzar los padres para crear el hijo
                Genotipo genPadre1 = padre1.getGenotipo();
                Genotipo genPadre2 = padre2.getGenotipo();
                //System.out.println("Element "+random1+" has fitness " + padre1.fitness() + " and element "+random1+ " has fitness " + padre2.fitness() );

                cruce.cruzar(genPadre1, genPadre2);

                padre1.setGenotipo(genPadre1);
                padre2.setGenotipo(genPadre2);
            }
            for (i = 0; i < selectedParents.length; i++) {
                Genotipo gen = selectedParents[i].getGenotipo();
                mut.mutar(gen);
                selectedParents[i].setGenotipo(gen);
            }

            /** Nueva generacio vacia, con mismo tamaño*/
            Generacion siguienteGeneracion = new Generacion(poblacion, personaje, tsvReader);

            PersonajeImpl[] nextGenPoblacion;

            nextGenPoblacion = fill.fill(currentPoblacion.getPoblacion(), selectedParents);

            siguienteGeneracion.setGeneracion(nextGenPoblacion);

            PersonajeImpl personajeConMasFitnessCurrentGen = siguienteGeneracion.getPersonajeConMasFitness();

            double a1 = personajeConMasFitnessCurrentGen.fitness();
            if(mejorPersonaje == null){
                mejorPersonaje      = personajeConMasFitnessCurrentGen;
                genMejorPersonaje   = 0;
            }
            if(a1 > a2){
                mejorPersonaje = personajeConMasFitnessCurrentGen;
                a2 = personajeConMasFitnessCurrentGen.fitness();
                genMejorPersonaje = CICLO;
            }

            System.out.println("Elemento con mas fitness en esta generacion: " + personajeConMasFitnessCurrentGen.fitness());
            str.append(CICLO);
            str.append(" ");
            str.append(personajeConMasFitnessCurrentGen.fitness());
            str.append("\n");
            
            //str1.append(CICLO);
            //str1.append(" ");
            str1.append(personajeConMasFitnessCurrentGen.data());
            str1.append("\n");
            
            str2.append("F	");
            str2.append(CICLO + "\t");
            str2.append(personajeConMasFitnessCurrentGen.F());
            str2.append("\n");
            
            str2.append("A	");
            str2.append(CICLO + "\t");
            str2.append(personajeConMasFitnessCurrentGen.A());
            str2.append("\n");
            
            str2.append("P	");
            str2.append(CICLO + "\t");
            str2.append(personajeConMasFitnessCurrentGen.P());
            str2.append("\n");
            
            str2.append("R	");
            str2.append(CICLO + "\t");
            str2.append(personajeConMasFitnessCurrentGen.R());
            str2.append("\n");
            
            str2.append("V	");
            str2.append(CICLO + "\t");
            str2.append(personajeConMasFitnessCurrentGen.V());
            str2.append("\n");
            
            //str2.append(personajeConMasFitnessCurrentGen.animationBar());
            
            //Reemplazar poblacion con la siguienteGeneracion
            currentPoblacion = siguienteGeneracion;

            double criterioValue;

            if (criterioDeCorte instanceof Tiempo){
                double endTime = System.currentTimeMillis();
                criterioValue = endTime - startTime;
            }else {
                //(criterioDeCorte instanceof CantidadDeGeneraciones)
                criterioValue = CICLO;
            }

            if(criterioDeCorte.end(criterioValue)){
                System.out.println("-------------------------------------------------");
                System.out.println("-------------------------------------------------");
                System.out.println("-------------------------------------------------");
                System.out.println("-------------------------------------------------");
                System.out.println("Personaje con mejor fitness es: "+ a2);
                System.out.println("Generacion num: " + genMejorPersonaje);
                System.out.println("Bota ID: " + mejorPersonaje.getBota().getId());
                System.out.println("Casco ID: " + mejorPersonaje.getCasco().getId());
                System.out.println("Altura: " + mejorPersonaje.getAltura());
                System.out.println("Guante ID: " + mejorPersonaje.getGuante().getId());
                System.out.println("Pechera ID: " + mejorPersonaje.getPechera().getId());
                
                write("fitness(generaciones).txt",str.toString());
                write("personajes.txt",str1.toString());
                write("animationBar.txt",str2.toString());

                write("cascos_frecuencia.txt", printMap(cascosMasFrecuentes));
                write("armas_frecuencia.txt", printMap(armasMasFrecuentes));
                write("botas_frecuencia.txt", printMap(botasMasFrecuentes));
                write("guante_frecuencia.txt", printMap(guantesMasFrecuentes));
                write("pechera_frecuencia.txt", printMap(pecherasMasFrecuentes));


                StringBuilder stringBuilder = new StringBuilder();
                (find5BestAltura(alturaMasFrecuentes)).forEach((key,value) -> stringBuilder.append(key + " " + value + "\n"));
                write("altura_frecuencia.txt", stringBuilder.toString());

                return;
            }

            CICLO++;
        }
        
        
        
    }

    private String printMap(HashMap<Integer, Integer> hashMap){
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<Integer, Integer> aux = find5Best(hashMap);
        aux.forEach((key,value) -> stringBuilder.append(key + " " + value + "\n"));
        return stringBuilder.toString();
    }

    /***
     * Calcula el fitness para la primera generacion
     * @return
     */
    public static void primeraGeneracion(){
        //Iniciamos con una primera generacion aleatoria de poblacion = 150

        return;
    }

    private HashMap<Double, Integer> find5BestAltura(HashMap<Double, Integer> hashMap){
        HashMap<Double, Integer> aux = new HashMap<>();
        int best = Collections.max(hashMap.values());
        for (Map.Entry<Double, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }
        best = Collections.max(hashMap.values());
        for (Map.Entry<Double, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }
        best = Collections.max(hashMap.values());
        for (Map.Entry<Double, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }
        best = Collections.max(hashMap.values());
        for (Map.Entry<Double, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }
        best = Collections.max(hashMap.values());
        for (Map.Entry<Double, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }

        return aux;

    }

    private HashMap<Integer, Integer> find5Best(HashMap<Integer, Integer> hashMap){
        HashMap<Integer, Integer> aux = new HashMap<>();
        int best = Collections.max(hashMap.values());
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }
        best = Collections.max(hashMap.values());
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }
        best = Collections.max(hashMap.values());
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }
        best = Collections.max(hashMap.values());
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }
        best = Collections.max(hashMap.values());
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==best) {
                aux.put(entry.getKey(),best);
                hashMap.remove(entry.getKey());
                break;
            }
        }

        return aux;

    }

    private void mix(double[] aux) {
		for(int i = 0 ; i < aux.length - 2 ; i++) {
			if(aux[i] == aux[i+1]) {
				double temp = aux[i+2];
				aux[i+2] = aux[i+1];
				aux[i+1] = temp;
			}
		}
    }

    private static void shuffleArray(PersonajeImpl[] array)
    {
        int index;
        PersonajeImpl temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    
    private static void write (String filename , String value) {
    	try {
  	      FileWriter myWriter = new FileWriter(filename);
  	      myWriter.write(value);
  	      myWriter.close();
  	    } catch (IOException e) {
  	      System.out.println("An error occurred.");
  	      e.printStackTrace();
  	    }
    }

    private void elementosMasFrecuentes(PersonajeImpl[] poblacion,
                                        HashMap<Integer, Integer> pecherasMasFrecuentes,
                                        HashMap<Integer, Integer> cascosMasFrecuentes ,
                                        HashMap<Integer, Integer> armasMasFrecuentes,
                                        HashMap<Integer, Integer> botasMasFrecuentes,
                                        HashMap<Integer, Integer> guantesMasFrecuentes,
                                        HashMap<Double, Integer> alturaMasFrecuentes){
        for(PersonajeImpl personaje : poblacion){
            int pecheraId   = personaje.getPechera().getId();
            int cascoId     = personaje.getCasco().getId();
            int armaId     = personaje.getArma().getId();
            int botaId      = personaje.getBota().getId();
            int guanteId    = personaje.getGuante().getId();
            double altura   = personaje.getAltura();

            updateHashValue(pecherasMasFrecuentes,pecheraId);
            updateHashValue(cascosMasFrecuentes,cascoId);
            updateHashValue(armasMasFrecuentes,armaId);
            updateHashValue(botasMasFrecuentes,botaId);
            updateHashValue(guantesMasFrecuentes,guanteId);

            if(alturaMasFrecuentes.containsKey(altura))
                alturaMasFrecuentes.put(altura, alturaMasFrecuentes.get(altura) + 1);
            else
                alturaMasFrecuentes.put(altura, 1);
        }
    }

    private void updateHashValue(HashMap<Integer, Integer> hmap, int id){
        if(hmap.containsKey(id))
            hmap.put(id, hmap.get(id) + 1);
        else
            hmap.put(id, 1);
    }

    private void print(PersonajeImpl[] poblacion){
        for (int i = 0;i<poblacion.length;i++){
            System.out.println();
        }
    }

}
