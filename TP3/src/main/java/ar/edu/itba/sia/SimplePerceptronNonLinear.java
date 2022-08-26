package ar.edu.itba.sia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimplePerceptronNonLinear {
	int epochs;
	double learning_rate;
	boolean plot_each_epoch;
	boolean stop_early;
	double[][] matrix;
	double [] weights = {0.01,0.02,0.4,0.3};
	double [] errorArr = new double[28];
	boolean verbose = false;
	static double beta = 2;
	StringBuilder plot = new StringBuilder();
	String file;
	StringBuilder maxes = new StringBuilder();
	int counter = 0;
	public SimplePerceptronNonLinear(int epochs,double learning_rate,boolean plot_each_epoch,boolean stop_early,double[][] matrix,String file) {
		this.epochs = epochs;
		this.learning_rate = learning_rate;
		this.plot_each_epoch = plot_each_epoch;
		this.stop_early = stop_early;
		this.matrix = matrix;
		this.file = file;
	}
	
	public SimplePerceptronNonLinear() {
		
	}
	
	// Train and adjusts weights
	
	public double [] train() {
		for(int epoch = 0 ; epoch < epochs ; epoch++) {
			double current_accuracy = this.accuracy(this.matrix,this.weights);
			if(verbose) {System.out.println(String.format("%d	%s	%.2f",epoch,printArr(this.weights),current_accuracy));};
			//System.out.println(current_accuracy);
			if(current_accuracy==1.0 && stop_early) {
				plot.append(this.printArr(this.weights));
				break;
			}
			
			if(plot_each_epoch) {
				//plot.append(this.weightsToYinR3(weights));
				plot.append(this.printArr(this.weights));
				plot.append("\n");
			}
			if(epochs - epoch < 5) {
				System.out.println("Epoch " + epoch);
				System.out.println("-------------------------------------");
			}
			for(int i = 0 ; i < matrix.length ; i++) {
				double prediction = predict(matrix[i],weights);
				if(epochs - epoch < 5) {
					System.out.println(denormalize(prediction) + "\t" + denormalize(matrix[i][matrix[i].length-1]));
					//errorArr[i] = Math.abs(denormalize(prediction) - denormalize(matrix[i][matrix[i].length-1]));
					errorArr[i] = Math.abs(prediction - matrix[i][matrix[i].length-1]);
				}
				double error = matrix[i][matrix[i].length-1] - prediction;
				if(verbose) {}
				for(int j = 0 ; j < weights.length ; j++) {
					weights[j] = weights[j]+(learning_rate*error*matrix[i][j])*g_derivative(prediction);
				}
			}
		}
		write("ej2Plot.txt",maxes.toString());
		write(file,plot.toString());
		System.out.println(printArr(this.errorArr));
		return weights;
	}
	
	//Denormalize a double
	private double denormalize(double x) {
		double max = 88.184;
		double min = 0.320;
		return min + x*(max-min);
	}

	private double accuracy(double[][] matrix, double[] weights) {
		int correct = 0;
		List<Double> predictions = new ArrayList();
		double max = 0;
		double min = Double.MAX_VALUE;
		for(int i = 0 ; i < matrix.length ; i++) {
			double prediction = predict(matrix[i],weights);
			predictions.add(prediction);
			double err = Math.abs(prediction - matrix[i][matrix[i].length-1]);
			if(err > max) {
				max = err;
			}
			if(err > min) {
				min = err;
			}
			//System.out.println(Math.abs(prediction - matrix[i][matrix[i].length-1]));
			if(Math.abs(prediction - matrix[i][matrix[i].length-1])<0.1){
				correct+=1;
			}
		}
		//if(verbose){printList(predictions);};
		//System.out.println(max);
		maxes.append(counter);
		counter++;
		maxes.append("\t");
		maxes.append(max);
		maxes.append("\n");
		return correct/matrix.length;
	}
	
	// Predict
	double predict(double[] matrix, double[] weights) {
		double activation = 0;
		if(verbose){System.out.println("w =" + printArr(weights));System.out.println("m = " + printArr(matrix));}
		for(int i = 0 ; i < weights.length ; i++){
			activation += matrix[i]*weights[i];
			if(verbose){System.out.println(String.format("matrix[%.2f]*weights[%.2f] = %.2f ",matrix[i],weights[i],matrix[i]*weights[i]));}
		}
		return g(activation);
	}
	
	private static double g(double x) {
		//return Math.tanh(beta*x);
		return 1/(1+Math.exp(-2*beta*x));
	}
	
	private static double g_derivative(double x) {
		//return beta*(1-Math.pow(g(x),2));
		return 2*beta*g(x)*(1-g(x));
	}
	
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
	
	private String weightsToYinR3(double[] weights) {
    	double a = -1*(weights[1]/weights[2]);
    	double b = -1*(weights[3]/weights[2]);
    	double c = -1*(weights[0]/weights[2]);
    	return String.format("y = %.2fx + %.2fz + %.2f",a,b,c);
    }
	
}
