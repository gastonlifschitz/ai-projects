package ar.edu.itba.sia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimplePerceptron {
	
	int epochs;
	double learning_rate;
	boolean plot_each_epoch;
	boolean stop_early;
	double[][] matrix;
	double [] weights = {0.01,0.02,0.4};
	boolean verbose = true;
	String file;
	StringBuilder plot = new StringBuilder("");
	
	
	// Constructor, important variables are parametrized
	public SimplePerceptron(int epochs,double learning_rate,boolean plot_each_epoch,boolean stop_early,double[][] matrix,String file) {
		this.epochs = epochs;
		this.learning_rate = learning_rate;
		this.plot_each_epoch = plot_each_epoch;
		this.stop_early = stop_early;
		this.matrix = matrix;
		this.file = file;
	}
	
	public SimplePerceptron() {
		
	}

	
	// Train until error is cero or until epochs have been all made
	public double [] train() {
		for(int epoch = 0 ; epoch < epochs ; epoch++) {
			double current_accuracy = this.accuracy(this.matrix,this.weights);
			if(verbose) {System.out.println(String.format("%d	%s",epoch,printArr(this.weights)));}
			if(current_accuracy==1.0 && stop_early) {
				plot.append(printArr(this.weights));
				break;
			}
			if(plot_each_epoch) {
				plot.append(printArr(this.weights));
				//plot.append(this.weightsToY(this.weights));
				plot.append("\n");
			}
			for(int i = 0 ; i < matrix.length ; i++) {
				double prediction = predict(matrix[i],weights);
				double error = matrix[i][matrix[i].length-1] - prediction;
				if(verbose) {}
				for(int j = 0 ; j < weights.length ; j++) {
					weights[j] = weights[j]+(learning_rate*error*matrix[i][j]);
				}
			}
		}
		write(this.file,plot.toString());
		return weights;
	}
	
	// Measure accuracy
	private double accuracy(double[][] matrix, double[] weights) {
		int correct = 0;
		List<Double> predictions = new ArrayList();
		for(int i = 0 ; i < matrix.length ; i++) {
			double prediction = predict(matrix[i],weights);
			predictions.add(prediction);
			if(prediction==matrix[i][matrix[i].length-1]){
				correct+=1;
			}
		}
		//if(verbose){printList(predictions);};
		return correct/matrix.length;
	}
	
	// Calculate the activation of the input from weights
	double predict(double[] matrix, double[] weights) {
		double activation = 0;
		//if(verbose){System.out.println("w =" + printArr(weights));System.out.println("m = " + printArr(matrix));}
		for(int i = 0 ; i < weights.length ; i++){
			activation += matrix[i]*weights[i];
			//if(verbose){System.out.println(String.format("matrix[%.2f]*weights[%.2f] = %.2f ",matrix[i],weights[i],matrix[i]*weights[i]));}
		}
		if(activation >= 0) {return 1;}else {return -1;}
	}
	
	// Print an array
	private static String printArr(double[] l) {
		StringBuilder ret = new StringBuilder("");
		//ret.append("[");
		for(int i = 0 ; i < l.length ; i++) {
			ret.append(String.format("%.2f",l[i]));
			ret.append(" ");
		}
		//ret.append("]");
		return ret.toString();
	}
	
	// Print an equiation of a line out of the weithgts
	private static String weightsToY(double[] weights) {
    	double a = -1*(weights[1]/weights[2]);
    	double b = -1*(weights[0]/weights[2]);
    	return String.format("y = %.2fx + %.2f",a,b);
    }
	
	// write to a file
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
	
}
