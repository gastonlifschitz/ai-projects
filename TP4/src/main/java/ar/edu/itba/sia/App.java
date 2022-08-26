package ar.edu.itba.sia;

import ar.edu.itba.sia.hopfield.Hopfield;

public class App {
    public static void main( String[] args ){
        System.out.println( "SIATP4");
        ReadConf conf = new ReadConf();
    	conf.getPropValues();
    	System.out.println("CONFIGURATION:	" + conf);
    	if(conf.ej1a) {
    		System.out.println( "EJ1A");
    		CSV csv = new CSV();
    		//System.out.println(csv.print());
    		csv.standarize2();
    		//System.out.println(csv.printStandarized());
    		/*
    		Implementar la Red de Kohonen y utilizarla para resolver los siguientes problemas:
    		Asociar países que posean las mismas características geoeconómicas y sociales.
    		Realizar al menos un gráfico que muestre los resultados.
    		Realizar un gráfico que muestre las distancias promedio entre neuronas vecinas.
    		Analizar la cantidad de elementos que fueron asociados a cada neurona.
    		*/
    		
    		Kohonen kohonen = new Kohonen();
    		kohonen.inputs = csv.europeStandarized;
    		kohonen.train(5);
//    		kohonen.vis();
//    		kohonen.results();
    		kohonen.navgd();
    		kohonen.nodecount();
    		
    	}
    	if(conf.ej1b) {
    		System.out.println( "EJ1B");
    		System.out.println("Oja's Rule");
    		CSV csv = new CSV();
    		//System.out.println(csv.print());
    		csv.standarize2();
    		System.out.println(csv.printStandarized());
    		Oja oja = new Oja();
    		oja.inputs = csv.europeStandarized;
    		oja.train();
    	}
    	if(conf.ej2a) {
    		System.out.println( "EJ2A");
    		Hopfield hopfield = new Hopfield();
            int[] j = new int[]{
                    1 , 1 ,1 , 1 ,1,
                    -1,-1 ,-1, 1, -1,
                    -1, -1, -1,1, -1,
                    1 , -1 ,-1,1 ,-1,
                    1 ,1 ,1 ,-1, -1
            };
            int[] i = new int[]{
                    1 ,1 ,1 ,1 ,1,
                    -1,-1 ,1, -1, -1,
                    -1, -1, 1 ,-1, -1,
                    -1 , -1 ,1 ,-1 ,-1,
                    1 ,1 ,1 ,1, 1
            };
            int[] u = new int[]{
                    1 ,-1 ,-1 ,-1 ,1,
                    1,-1 ,-1, -1, 1,
                    1, -1, -1 ,-1, 1,
                    1 , -1 ,-1 ,-1 ,1,
                    1 ,1 ,1 ,1, 1
            };
            hopfield.train(j);
            hopfield.train(i);
            hopfield.train(u);
            int[] jAltered = new int[]{
                    1 ,1 ,1 ,1 ,1,
                    -1,-1 ,-1, 1, -1,
                    -1, -1, -1 ,1, -1,
                    -1 , -1 ,-1 ,1 ,1,
                    1 ,1 ,1 ,-1, -1
            };
            int[] iAltered = new int[]{
                    1 ,1 ,1 ,1 ,1,
                    -1,1 ,1, -1, -1,
                    -1, -1, 1 ,1, -1,
                    -1 , -1 ,1 ,1 ,-1,
                    1 ,1 ,1 ,1, 1
            };
            int[] uAltered = new int[]{
                    1 ,-1 ,-1 ,-1 ,1,
                    1,-1 ,-1, -1, 1,
                    1, 1, -1 ,1, 1,
                    1 , 1 ,1 ,1 ,1,
                    1 ,1 ,1 ,1, 1
            };
            hopfield.run(jAltered);
            hopfield.run(iAltered);
            hopfield.run(uAltered);
            hopfield.getWeightsMatrix();
            hopfield.vis();
    	}
    	if(conf.ej2b) {System.out.println( "EJ2B");}
    }
}
