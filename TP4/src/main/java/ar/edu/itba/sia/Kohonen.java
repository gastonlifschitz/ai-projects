package ar.edu.itba.sia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Kohonen {
	
	double[][] weights = {
			{5.0,14.0,2.0,3.0,5.0,14.0,2.0,3.0},
			{13.0,7.0,12.0,3.0,5.0,14.0,2.0,3.0},
			{20.0,3.0,30.0,3.0,5.0,14.0,2.0,3.0}
			};
	double[][] inputs = {
			{0.1,0.2,0.13},
			{0.5,0.3,0.7}
			};
	
	/*
	x0 x1 x2
	
	.
	.
	.
	
	The COLS of the input matrix has to match with the weight matrix ROWS
	
	The COLS of the weight matrix has to match with the amount of neurons
	
 	|x0|| w00 w01 w02 w03 w04 ... w0n|
 	|x1|| w10 w11 w12 w13 w14 ... w1n|
 	|x2|| w20 w21 w22 w23 w24 ... w2n|
 	
 	Means : the input attribute i has a weight connected with the neuron j
  	
  	As a simplification I am going to say:
  	
  	EXAPLES is the amount of ROWS in the input matrix
  	ATTR is the amount of COLS in the input matrix
  	ATTR is also the amount of ROWS in the weights matrix
  	NEURONS is the amount of COLS in the weights matrix
	*/
	
	private int R;
	
	private int X = 2;
	private int Y = 4;
	
//	private int EXAMPLES;
//	private int ATTR;
//	private int NEURONS;
	
	private int EXAMPLES = inputs.length;
	private int ATTR = inputs[0].length;
	private int NEURONS = weights[0].length;
	
	private boolean verbose = false;
	
	public void train(int q) {
		// Wrapper function, create an input matrix with q rows of the original data set
		// Pre-process data by standarization
		//standarize(inputs);
		// Create a q-bounded set
		//train(q-bounded-set);
		train(inputs);
	}
	
	public void train(double [][] input) {
		// Training algorithm
		
		/*
		
		1- Initialize neural network weights
		2- Randomly select an input
		3- Select the winning neuron using Euclidean distance
		4- Update neurons using weights
		5- Go back to 2 until done training
		
		*/
		this.inputs = input;
		EXAMPLES = input.length;
		ATTR = input[0].length;
		//NEURONS = weights[0].length;
		
		NEURONS = 9;
		X = 3;
		Y = 3;
		
		//INPUT -> PESOS
		
		initWeightsWithInputs();
		
		//if(verbose) {
			System.out.println(this.printMatrix(input));
			System.out.println(this.printMatrix(weights));
		//}
		
		
		
		// The total amount of iterations should be chosen as a function of the length of the input vector ie: 500 * n
		StringBuilder film = new StringBuilder();
		for(int i = 0 ; i < 370 ; i++) {
			R = getRandomNumberInRange(0,input.length - 1);
			double[] x = input[R];
			if(verbose) {System.out.println(this.printArray(x));}
			int winner = getWinningNeuron(x);
			if(verbose){System.out.println("\nWinning Neuron: " + winner);}
			/*
				After selecting the winning neuron we will use it's weights to update
				the weights of the winning neuron and the neurons around it.
				Using the following weight update formula.
				deltaW[j,i] = n(t) * T[j,I(x)](t) * (xi - w[j,i])
				
				t = epoch
				i , j  = neurons
				I(x)  =  the winning neuron
				
				n(t) = n0*Math.exp((-1*t)/tn);
				
				T[j,I(x)](t) = Math.exp(-1*(S(j,I(x))/Math.pow(2*sigma(t),2));

				S(j,I(x)) = ||wj - wi||; // Lateral distance between neurons
				
				Neighborhood size
				
				sigma(t) = sigma0 * Math.exp(-1*(t/t0));
				
				The topological neighborhood is important for magnifying the learning rate value.
				
				(x[i][winningNeuron] - w[j][i])
			*/
			update(i,winner);
			if(verbose) {
				System.out.println("\nWeights in epoch " + i + "\n");
				System.out.println(this.printMatrix(weights));
			}
			
			String str = this.navgd();
			film.append(str);
		}
		write("film.txt",film.toString());
		System.out.println(this.printMatrix(weights));
	}
		
	private int getWinningNeuron(double[] x) {
		double min = Double.MAX_VALUE;
		int ret = 0;
		for(int i = 0 ; i < NEURONS ; i++) {
			double d = getEuclideanDistance(x,i);
			if(d < min) {
				min = d;
				ret = i;
			}
		}
		return ret;
	}

	private double getEuclideanDistance(double[] x , int col) {
		double ret = 0;
		double sum = 0;
		for(int i = 0 ; i < ATTR ; i++) {
			sum += Math.pow((x[i] - weights[i][col]),2);
			if(verbose) {System.out.println(String.format("%.2f - %.2f",x[i],weights[i][col]));}
		}
		ret = Math.sqrt(sum);
		if(verbose) {System.out.println(ret + "	Current Neuron: " + col);}
		return ret;
	}

	public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public void update(int t , int winningNeuron) {
		// winningNeuron is in [0 - NEURONS]
		for(int i = 0 ; i < weights.length ; i++) { //ATTRS
			for(int j = 0 ; j < weights[i].length ; j++) { //NEURONS
				double deltaW = n(t) * (inputs[R][i] - weights[i][j]) * T(j,winningNeuron,t);//MIND: inputs[R] is the same as x[](Then inputs[R][i] is x[i] ), calling it this way just saves us parameter handling!
				weights[i][j] += deltaW;
			}
		}
	}
	
	private void initWeights() {
		double[][] w = new double[ATTR][X*Y];
		Random rand = new Random();
		for(int i = 0 ; i < ATTR ; i++) {
			for(int j = 0 ; j < NEURONS ; j++) {
				w[i][j] = rand.nextDouble();
			}
		}
		weights = w;
	}
	
	public void initWeightsWithInputs() {
		double[][] w = new double[ATTR][X*Y];
		Random rand = new Random();
		for(int i = 0 ; i < ATTR ; i++) {
			for(int j = 0 ; j < NEURONS ; j++) {
				int x = getRandomNumberInRange(0,EXAMPLES-1);
				int y = getRandomNumberInRange(0,ATTR-1);
				w[i][j] = inputs[x][y];
			}
		}
		weights = w;
	}
	
	private double T(int j , int winningNeuron , int t) {		
		return Math.exp(-1*(S(j,winningNeuron)/Math.pow(2*sigma(t),2)));
	}
	
	public double n(int t) {
		//TODO: make hyperparemeters configurable!
		double n0 = 1;
		double tn = 2;
		return n0*Math.exp((-1*t)/tn);
	}
	
	public double sigma(int t) {
		//TODO: make hyperparemeters configurable!
		double sigma0 = 1;
		double t0 = 1;
		return sigma0 * Math.exp(-1*(t/t0));
	}
	
	public double S(int a,int b) {
		// Lateral distance between neurons Si,j = || wi - wj ||
		double ret = 0;
		
		
		double[] A = new double[ATTR];
		double[] B = new double[ATTR];
		
		for(int i = 0 ; i < NEURONS ; i++) {
			if(i==a) {
				for(int j = 0 ; j < ATTR ; j++) {
					A[j] = weights[j][i];
				}
			}
			if(i==b) {
				for(int j = 0 ; j < ATTR ; j++) {
					B[j] = weights[j][i];
				}
			}
		}
		
		double sum = 0;
		if(verbose) {
			System.out.println("A: " + this.printArray(A));
			System.out.println("B: " + this.printArray(B));
		}
		for(int i = 0 ; i < ATTR ; i++) {
			sum += Math.pow((A[i] - B[i]),2);
			if(verbose) {System.out.println(String.format("%.2f - %.2f",A[i],B[i]));};
		}
		
		ret = Math.sqrt(sum);
		if(verbose) {System.out.println(ret);};
		return ret;
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

	public void results() {
		// TODO Auto-generated method stub
		
	}

	public String navgd() {
		//System.out.println("NAVGD");
		double[][] navgd = new double[X][Y];
		// NeuronAverageDistance
		// Return a matrix that has the average distance between neighbor neurons
		int currentNeuron = 0;
		for(int i = 0; i < X ; i++) {
			for(int j = 0 ; j < Y ; j++) {
				double a = 0, b = 0 , c = 0 , d = 0;
				int count = 0;
				if(i-1 > 0) {
					a = S(currentNeuron,currentNeuron - X);
					count++;
				}
				if(i+1 < X) {
					b = S(currentNeuron,currentNeuron + X);
					count++;
				}
				if(j-1 < 0) {
					c = S(currentNeuron,currentNeuron - 1);
					count++;
				}
				if(j+1 > Y) {
					d = S(currentNeuron,currentNeuron + 1);
					count++;
				}
				double x = (a+b+c+d)/count;
				navgd[i][j] = x ;
				currentNeuron++;
			}
		}
		
		double aux[][] = new double[Y][X];
		for(int i = 0 ; i < Y ; i++) {
			for(int j = 0 ; j < X ; j++) {
				aux[i][j] = navgd[j][i];
			}
		}
		
		for(int i = 0 ; i < Y ; i++) {
			double max = max(aux[i]); 
			double min = min(aux[i]);
			for(int j = 0 ; j < X ; j++) {
				aux[i][j] = (aux[i][j] - min) / (max - min);
			}
		}
		
		for(int i = 0 ; i < X ; i++) {
			for(int j = 0 ; j < Y ; j++) {
				navgd[i][j] = aux[j][i];
			}
		}
		
		//String str = printMatrix(navgd);
		//System.out.println(str);
		StringBuilder ovito = new StringBuilder();
		ovito.append(NEURONS);
		ovito.append("\n\n");
		int id = 0;
		for(int i = 0 ; i < X ; i++) {
			for(int j = 0 ; j < Y ; j++) {
				String color = "1 1 1";
				if(navgd[i][j] <= 0.33) {
					color = " 0 0 1";//Green
				}
				if(navgd[i][j] >= 0.34 && navgd[i][j] <= 0.66) {
					color = " 0 1 0";//Blue
				}
				if(navgd[i][j] >= 0.67) {
					color = " 1 0 0";//Red
				}
				ovito.append(id + " " + i + " " + j + " 0.5 " + color + "\n");
				id++;
			}
		}
		write("navgd.txt",ovito.toString());
		return ovito.toString();
	}
	
	public double max(double x[]) {
		double max = 0;
		for(int i = 0 ; i < x.length ; i++) {
			if(x[i] > max) {
				max = x[i];
			}
		}
		return max;
	}
	
	public double min(double x[]) {
		double min = Double.MAX_VALUE;
		for(int i = 0 ; i < x.length ; i++) {
			if(x[i] < min) {
				min = x[i];
			}
		}
		return min;
	}
	
	public void nodecount() {
		double [][] classification = new double[NEURONS][EXAMPLES];
		String [] countries = {"Austria","Belgium","Bulgaria","Croatia","Czech Republic","Denmark","Estonia","Finland","Germany","Greece","Hungary","Iceland","Ireland","Italy","Latvia","Lithuania","Luxembourg","Netherlands","Norway","Poland","Portugal","Slovakia","Slovenia","Spain","Sweden","Switzerland","Ukraine","United Kingdom"};
		double[] count = new double[NEURONS];
		for(int i = 0 ; i < EXAMPLES ; i++) {
			int winner = this.getWinningNeuron(inputs[i]);
			count[winner]++;
			classification[winner][i] = 1;
		}
		for(int i = 0 ; i < NEURONS ; i++) {
			System.out.print("Clase " + i + "->");
			for(int j = 0 ; j < EXAMPLES ; j++) {
				if(classification[i][j]==1) {
					System.out.print(" " + countries[j]);
				}
			}
			System.out.println();
		}
		
		String str = this.printArray(count);
		System.out.println(str);
		int idc = 0;
		for(int i = 0 ; i < X ; i++) {
			for(int j = 0 ; j < Y ; j++) {
				System.out.print(count[idc] + "\t");
				idc++;
			}
			System.out.println();
		}
		/*
		0  - 3   -> B
		4 - 7 -> G
		8 - 27 -> R
		
		Ovito colors
		
		000 White
		001	Blue
		010 Green
		011 Yellow
		100	Red
		101 Pink ?
		111 Black
		
		*/
		StringBuilder ovito = new StringBuilder();
		ovito.append(NEURONS);
		ovito.append("\n\n");
		int id = 0;
		for(int i = 0 ; i < X ; i++) {
			for(int j = 0 ; j < Y ; j++) {
				String color = "1 1 1";
				if(count[id]==Double.NaN) {
					color = "0 0 0";
				}
				if(count[id] <= 3) {//Blue
					color = "0 0 1";
				}
				if(count[id] >= 4 && count[id] <= 7) {//Green
					color = "0 1 0";
				}
				if(count[id] >= 8) {//Red
					color = "1 0 0";
				}
				ovito.append(id + " " + i + " " + j + " 0.5 " + color + "\n");
				id++;
			}
		}
		write("viskohonen.txt",ovito.toString());
	}
	
	public void vis() {
		//int x , y; // x*y = NEURONS -> print as a table!! x and y are parameters.
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
