package ar.edu.itba.sia;

import java.util.Random;

public class Oja {
	
	double[][] weights = {
			{5.0,14.0,2.0,3.0,5.0,14.0,2.0,3.0},
			{13.0,7.0,12.0,3.0,5.0,14.0,2.0,3.0},
			{20.0,3.0,30.0,3.0,5.0,14.0,2.0,3.0}
			};
	
	double[][] inputs = {
			{0.1,0.2,0.13},
			{0.5,0.3,0.7}
			};
	
	private int EXAMPLES = inputs.length;
	private int ATTR = inputs[0].length;
	private int NEURONS = weights[0].length;
	private double learningRate = 0.001;
	
	private boolean verbose = false;
	
	public void train() {
		train(inputs);
	}
	
	public void train(int q) {
		// Wrapper function, create an input matrix with q rows of the original data set | This separates between data set and training set
		// Pre-process data by standarization
		// Standarize(inputs);
		// Create a q-bounded set
		// Train(q-bounded-set);
		train(inputs);
	}
	
	public void train(double [][] input) {		
		EXAMPLES = input.length;
		ATTR = input[0].length;		
		NEURONS = 5;
		initWeights();
		//if(verbose) {
			//System.out.println(this.printMatrix(input));
			System.out.println(this.printMatrix(weights));
		//}
		// The total amount of iterations : 
		for(int epoch = 0 ; epoch < 1000 ; epoch++) {
			for(int i = 0 ; i < 28 /*EXAMPLES*/ ; i++) {
				double[] x = input[i];
				//System.out.println(printArray(x));
				for(int j = 0 ; j < NEURONS ; j++) {
					double[] w = getWeights(j);
					//System.out.println("Weight " + j + " " + printArray(w));
					double y = dotProduct(x,w);
					//Oja's rule
					double[] deltaW = escProduct(learningRate,minus(escProduct(y,x),escProduct(y*y,w)));
					//Update weights
					updateWeights(j,plus(w,deltaW));
				}
				
			}
			if(verbose) {
				//System.out.println("\nWeights in epoch " + epoch + "\n");
				System.out.println(this.printMatrix(weights));
			}
		}
		//if(verbose) {
			System.out.println(this.printMatrix(weights));
		//};
	}
	
	private double[] plus(double[] u , double[] v) {
		double[] ret = new double[v.length];
		for(int i = 0 ; i < v.length ; i++) {
			ret[i] = u[i]+v[i];
		}
		return ret;
	}
	
	private double[] minus(double[] u , double[] v) {
		double[] ret = new double[v.length];
		for(int i = 0 ; i < v.length ; i++) {
			ret[i] = u[i]-v[i];
		}
		return ret;
	}
	
	private double[] escProduct(double k , double[] v) {
		double[] ret = new double[v.length];
		for(int i = 0 ; i < v.length ; i++) {
			ret[i] = k*v[i];
		}
		return ret;
	}
	
	private double dotProduct(double[] x, double[] w) {
		double ret = 0;
		for(int i = 0 ; i < ATTR ; i++) {
			ret += x[i]*w[i];
		}
		return ret;
	}

	private double[] getWeights(int col) {
		double[] ret = new double[ATTR];
		for(int i = 0 ; i < ATTR ; i++) {
			ret[i] = weights[i][col];
		}
		return ret;
	}
	
	private void updateWeights(int col , double[] update) {
//		System.out.println(printMatrix(weights));
//		System.out.println(printArray(update));
//		System.out.println(col);
		for(int i = 0 ; i < ATTR ; i++) {
			weights[i][col] = update[i];
		}
//		System.out.println(printMatrix(weights));
	}
	
	private void initWeights() {
		double[][] w = new double[ATTR][NEURONS];
		Random rand = new Random();
		for(int i = 0 ; i < ATTR ; i++) {
			for(int j = 0 ; j < NEURONS ; j++) {
				w[i][j] = rand.nextDouble();
			}
		}
		weights = w;
	}
	
	private String printArray(double[] arr) {
		StringBuilder ret = new StringBuilder();
		for(int i = 0 ; i < arr.length ; i++) {
			ret.append(String.format("%.2f\t",arr[i]));
		}
		ret.append("\n");
		return ret.toString();
	}
	
	private String printMatrix(double[][] matrix) {
		StringBuilder ret = new StringBuilder();
		for(int i = 0 ; i < matrix.length ; i++ ) {
			for(int j = 0 ; j < matrix[i].length ; j++) {
				ret.append(String.format("%.2f\t",matrix[i][j]));
			}
			ret.append("\n");
		}
		return ret.toString();
	}
	
}
