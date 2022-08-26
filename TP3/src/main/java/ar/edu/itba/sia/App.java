package ar.edu.itba.sia;

import java.io.FileWriter;
import java.io.IOException;

import ar.edu.itba.sia.multiLayer.MultiLayerPerceptron;
import ar.edu.itba.sia.multiLayerGeneral.MultiLayerPerceptronGeneral;

public class App
{
    public static void main( String[] args )
    {	
    	//Read configuration file and set parameters so that the program runs as desired
    	ReadConf conf = new ReadConf();
    	conf.getPropValues();
    	System.out.println("CONFIGURATION:	" + conf);
    	
    	System.out.println( "SIA-TP3" );

		double maxError = conf.maxError, currentError = 1;
		StringBuilder stringBuilderExcercise3 = new StringBuilder();
		StringBuilder stringBuilderExcercise3b = new StringBuilder();
		
		// Execute a certain exercise according to what has been configured by user
		
    	if(conf.ej1a) {
    		System.out.println("EJ1A");
    		SpaceReader spaceReaderAND = new SpaceReader(4,4);
    		spaceReaderAND.load("ej1AND.txt");
            //System.out.println(spaceReaderAND.printFile());
    		System.out.println("Epoch   weights in current epoch");
            SimplePerceptron slpAND = new SimplePerceptron(conf.ej1a_epochs,conf.ej1a_learningrate,conf.ej1a_ploteachepoch,conf.ej1a_stopearly,spaceReaderAND.file,"ej1a.txt");
            double[] l1a = slpAND.train();
            System.out.println("The Final Weights are: " + printArr(l1a));
            System.out.println("The equation of the linear separator obtained is " + weightsToY(l1a));
    	}
    	if(conf.ej1b) {
    		System.out.println("EJ1B");
    		SpaceReader spaceReaderOR = new SpaceReader(4,4);
    		spaceReaderOR.load("ej1OR.txt");
    		//spaceReaderOR.load("ej3XOR.txt");
            //System.out.println(spaceReaderOR.printFile());
            System.out.println("Epoch   weights in current epoch");
            SimplePerceptron slpOR = new SimplePerceptron(conf.ej1b_epochs,conf.ej1b_learningrate,conf.ej1b_ploteachepoch,conf.ej1b_stopearly,spaceReaderOR.file,"ej1b.txt");
            double[] l1b = slpOR.train();
            System.out.println("The Final Weights are: " +  printArr(l1b));
            System.out.println("The equation of the linear separator obtained is " + weightsToY(l1b));
    	}
    	if(conf.ej2) {
    		System.out.println("EJ2");
    		TSVReader tsvReader = new TSVReader();
            tsvReader.load("ej2.txt");
            double[][] matrix = tsvReader.file;
            matrix = normailize(matrix);
            System.out.println("Normalize Input");
            System.out.println(printMatrix(matrix));
            //System.out.println(tsvReader.printFile());
            SimplePerceptronNonLinear slpNL = new SimplePerceptronNonLinear(10000,conf.learning_rate,true,true,matrix,"ej2.txt");
            //denormalize(matrix);
            double[] l2 = slpNL.train();
            //System.out.println(printArr(l2));
            //System.out.println(weightsToYinR3(l2));
    	}
    	if(conf.ej3a) {
    		double data[][][] = new double[][][] {{{1,1},{-1}},{{-1,-1},{-1}},{{-1,1},{1}},{{1,-1},{1}}};

            MultiLayerPerceptron multiLayerPerceptron = new MultiLayerPerceptron(2,2,conf.num_epochs,conf.learning_rate, conf.beta);
            double[] result = new double[]{0.0,0.0,0.0,0.0};
            for(int j = 0;j<conf.num_epochs && maxError < currentError;j++){
    			System.out.println("Epoch " + j );
				double errorInThisEpoch[] = new double[data.length];
    			for(int i = 0; i < data.length;i++){
    				multiLayerPerceptron.train(data[i][0]);
    				multiLayerPerceptron.updateError(data[i][1][0]);
    				result[i] = multiLayerPerceptron.getOutput().getOutput();
					errorInThisEpoch[i] = Math.abs(data[i][1][0]) - Math.abs(multiLayerPerceptron.getOutput().getOutput());
    				System.out.println("For Input: ( " + data[i][0][0] + "," + data[i][0][1] + " ) - Output: " + result[i] + " - Expected value: " + data[i][1][0]);

    			}
    			System.out.println("------------------------" );
				currentError = getMaxValueFromArray(errorInThisEpoch);
				stringBuilderExcercise3.append(j + "\t" + currentError + "\n");
    		}
			write("ex3a.txt", stringBuilderExcercise3.toString());
    	}
    	if(conf.ej3b) {
    		SpaceReaderInteger spaceReader2 = new SpaceReaderInteger();
            spaceReader2.load("ej3.txt");
            NumParse np = new NumParse(spaceReader2.printFile());

        	double data[][][] = np.parseDouble();
        	printArr3DDouble(data);
        	
            double dataXOR[][][] = new double[][][] {{{1,1},{-1}},{{-1,-1},{-1}},{{-1,1},{1}},{{1,-1},{1}}};
            printArr3DDouble(dataXOR);

			MultiLayerPerceptronGeneral multiLayerPerceptronGeneral = new MultiLayerPerceptronGeneral(7*5 + 1,conf.hidden_perceptrons,conf.num_epochs,conf.learning_rate, conf.beta);
            double[] result = new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

            for(int j = 0;j<conf.num_epochs && maxError < currentError;j++){
    			System.out.println("Epoch " + j );
    			double errorInThisEpoch[] = new double[data.length];
    			for(int i = 0; i < data.length;i++){
    				multiLayerPerceptronGeneral.train(data[i][0]);
    				multiLayerPerceptronGeneral.updateError(data[i][1][0]);
    				result[i] = multiLayerPerceptronGeneral.getOutput().getOutput();
					errorInThisEpoch[i] = Math.abs(data[i][1][0] - multiLayerPerceptronGeneral.getOutput().getOutput());
					System.out.println("For image " + i + " the output is " + result[i]);

    			}
    			System.out.println("------------------------" );
				currentError = getMaxValueFromArray(errorInThisEpoch);
				stringBuilderExcercise3b.append(j + "\t" + currentError + "\n");
    		}
			write("ex3b.txt", stringBuilderExcercise3b.toString());
    	}
    }
    // Find the max value in an array
	private static double getMaxValueFromArray(double[] errorInThisEpoch) {
		double max = errorInThisEpoch[0];
		for (int i = 1; i < errorInThisEpoch.length; i++) {
			if (errorInThisEpoch[i] > max)
				max = errorInThisEpoch[i];
		}
		return max;
	}
	
	// Normalize the input matrix
	private static double[][] normailize(double[][] matrix) {
		double [][] ret = new double[matrix.length][matrix[0].length];
		int x = 0;
		for(int j = 0 ; j < matrix[0].length ; j++) {
			double[] arr = new double[matrix.length];
			for(int i = 0 ; i < matrix.length ; i++) {
				arr[i] = matrix[i][j];
			}
			//System.out.println(printArr(arr));
			//ret[x] = normalize(arr);
			arr = normalize(arr);
			for(int k = 0 ; k < arr.length ; k++) {
				 ret[k][x] = arr[k];
			}
			
			x++;
		}
		return ret;
	}

	private static double[] normalize(double[] arr) {
		//Look for min and max in arr
		double [] ret = new double[arr.length];
		double min = Double.MAX_VALUE;
		double max = 0;
		
		for(int i = 0 ; i < arr.length ; i++) {
			if(arr[i] < min) {
				min = arr[i];
			}
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		
		//System.out.println("MAX = " + max);
		

		//System.out.println("MIN = " + min);
		
		// Normalize
		
		for(int i = 0 ; i < arr.length ; i++) {
			ret[i] = (arr[i] - min ) / (max-min) ;
		}
		
		//System.out.println(printArr(ret));
		return ret;
	}
	
	// Print a 3D array
	private static void printArr3DDouble(double[][][] nums) {
    	for(int i = 0 ; i < nums.length;i++) {
			for(int j = 0 ; j < nums[i].length;j++) {
				for(int k = 0 ; k < nums[i][j].length;k++) {
					System.out.print(nums[i][j][k] + " ");
				}
			}
			System.out.println();
		}
		
	}
	
	// Given a weights array return a string of an equation 2D
	private static String weightsToY(double[] weights) {
    	double a = -1*(weights[1]/weights[2]);
    	double b = -1*(weights[0]/weights[2]);
    	return String.format("y = %.2fx + %.2f",a,b);
    }
    
	// Given a weights array return a string of an equation 3D
    private static String weightsToYinR3(double[] weights) {
    	double a = -1*(weights[1]/weights[2]);
    	double b = -1*(weights[3]/weights[2]);
    	double c = -1*(weights[0]/weights[2]);
    	return String.format("y = %.2fx + %.2fz + %.2f",a,b,c);
    }
    
	private static String printArr(double[] l) {
		StringBuilder ret = new StringBuilder("");
		ret.append("[");
		for(int i = 0 ; i < l.length ; i++) {
			ret.append(String.format("%.2f",l[i]));
			ret.append(" ");
		}
		ret.append("]");
		return ret.toString();
	}
	
	private static String printMatrix(double[][]matrix) {
		StringBuilder ret = new StringBuilder();
		for(int i = 0 ; i < matrix.length ; i++) {
			ret.append(printArr(matrix[i]));
			ret.append("\n");
		}
		return ret.toString();
	}
	
	// Write to a file
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
