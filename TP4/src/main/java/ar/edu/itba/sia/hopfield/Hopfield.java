package ar.edu.itba.sia.hopfield;

import java.io.FileWriter;
import java.io.IOException;

public class Hopfield {
    private double[][] weightsMatrix = new double[25][25];

    // Input size of 25 elements
    public void train(int[] input){
        double[][] inputWeights = new double[25][25];
        for(int i = 0; i < 25 ; i++){
            for(int j = 0; j < 25 ; j++){
                if(i != j){
                    inputWeights[i][j] = (input[i] * input[j])/25.0;
                }
            }
        }

        for(int i = 0; i < 25 ; i++){
            for(int j = 0; j < 25 ; j++){
                if(i != j){
                    weightsMatrix[i][j] += inputWeights[i][j];
                }
            }
        }

    }

    // Input size of 25 elements
    public void run(int[] input){
        System.out.println("Input: ");
        printMatrix(input);
        double sum = 0;
        int[] output = new int[25];
        for(int i = 0; i < 25 ; i++){
            sum = 0;
            for(int j = 0; j < 25 ; j++){
                if(i != j){
                    sum += weightsMatrix[i][j] * input[j];
                }
            }
            output[i] = (int) Math.signum(sum);
        }
        System.out.println("Output: ");
        printMatrix(output);
    }

    private void printMatrix(int[] output) {
        for (int i = 0;i<25;i++){
            if(i%5 == 0){
                System.out.println();
            }
            if(output[i] == -1)
                System.out.print("  ");
            else
                System.out.print("* ");
        }
        System.out.println();
        System.out.println("-----------------");
    }

    public void getWeightsMatrix(){
        for (int i = 0;i<25;i++){
            for (int j = 0;j<25;j++){

                System.out.print(weightsMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("-----------------");
    }
    
    public void vis() {
    	
    	StringBuilder ovito = new StringBuilder();
    	ovito.append(weightsMatrix.length*weightsMatrix[0].length);
    	ovito.append("\n\n");
    	String color;
    	int id = 0;
    	for(int i = 0 ; i < weightsMatrix.length ; i++) {
    		for(int j = 0 ; j < weightsMatrix[i].length ; j++) {
    			if(weightsMatrix[i][j] > 0) {
    				color = " 1 0 0";
    			}
    			else if(weightsMatrix[i][j] == 0) {
    				color = " 0 1 0";
    			}
    			else {
    				color = " 0 0 1";
    			}
    			ovito.append(id + " " + i + " " + j +  " 0.2" + color +  "\n");
    		}
    	}
    	write("vishopfield.txt",ovito.toString());
    }
    
    static void write (String filename , String value) {
     	try {
   	      FileWriter myWriter = new FileWriter(filename);
   	      myWriter.write(value);
   	      myWriter.close();
   	    } catch (IOException e) {
   	      System.out.println("An error occurred.");
   	      e.printStackTrace();
   	    }
     }
    
}
