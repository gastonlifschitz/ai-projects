package ar.edu.itba.paw.sia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TSVReader {
	
	double[][] armas = new double[1000000][6];
	double[][] cascos = new double[1000000][6];
	double[][] pecheras = new double[1000000][6];
	double[][] guantes = new double[1000000][6];
	double[][] botas = new double[1000000][6];
	
	double[][] file = new double[4][6];
	
	boolean loaded = false;
	
	public TSVReader() {
		load("armas.tsv");
	    load("cascos.tsv");
		load("guantes.tsv");
		load("pecheras.tsv");
		load("botas.tsv");
	}

	public TSVReader(String string) {
		// TODO Auto-generated constructor stub
		load("file.tsv");
	}

	public double [] splitLine(String line) {
		
		double ret [] = {0,0,0,0,0,0,0};
		
		String [] tempArray = line.split("\t");
        int id = Integer.parseInt(tempArray[0]);
        double fuerza = Double.parseDouble(tempArray[1]);
        double agilidad = Double.parseDouble(tempArray[2]);
        double experiencia = Double.parseDouble(tempArray[3]);
        double resistencia = Double.parseDouble(tempArray[4]);
        double vida = Double.parseDouble(tempArray[5]);
        //System.out.println(String.format("ID = %d \t FU = %.2f \t AG = %.2f \t EX = %.2f \t RE = %.2f \t VI = %.2f",id,fuerza,agilidad,experiencia,resistencia,vida));
        
        ret[0] = fuerza;
        ret[1] = agilidad;
        ret[2] = experiencia;
        ret[3] = resistencia;
        ret[4] = vida;
        ret[5] = id;
		
		return ret;
	}
	
	public void load(String propFileName) {
		System.out.println("LOAD " + propFileName);
		double [] aux = null;
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
	        String thisLine;
	        int i = -1; // con i podemos ubicar el id si es necesario
			while ((thisLine = reader.readLine()) != null) {
				if(i>=0) {
					aux = splitLine(thisLine);
					switch(propFileName) {
					case "armas.tsv":
						armas[i] = aux;
						break;
					case "cascos.tsv":
						cascos[i] = aux;
						break;
					case "botas.tsv":
						botas[i] = aux;
						break;
					case "pecheras.tsv":
						pecheras[i] = aux;
						break;
					case "guantes.tsv":
						guantes[i] = aux;
						break;
					case "file.tsv":
						file[i] = aux;
						break;
					default:
						return;
				}
				}
				i++;
			}
			System.out.println("Loaded " + i + " items");
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		 
	}
	
	public double[] goToId(int id, String propFileName) {
		switch(propFileName) {
			case "armas.tsv":
				return armas[id];
			case "cascos.tsv":
				return cascos[id];
			case "botas.tsv":
				return botas[id];
			case "pecheras.tsv":
				return pecheras[id];
			case "guantes.tsv":
				return guantes[id];
			case "file.tsv":
				return file[id];
			default:
				return null;
		}
	}
	
	public void readLineByLine() {
		String propFileName = "file.tsv";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
	        String thisLine;
	        int i = -1; // con i podemos ubicar el id si es necesario
			while ((thisLine = reader.readLine()) != null) {
				
				if(i>=0) {
		            String [] tempArray = thisLine.split("\t");
		            int id = Integer.parseInt(tempArray[0]);
		            double fuerza = Double.parseDouble(tempArray[1]);
		            double agilidad = Double.parseDouble(tempArray[2]);
		            double experiencia = Double.parseDouble(tempArray[3]);
		            double resistencia = Double.parseDouble(tempArray[4]);
		            double vida = Double.parseDouble(tempArray[5]);
		            System.out.println(String.format("ID = %d \t FU = %.2f \t AG = %.2f \t EX = %.2f \t RE = %.2f \t VI = %.2f",id,fuerza,agilidad,experiencia,resistencia,vida));
				}
	            i++;
			}
			reader.close();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}
}

