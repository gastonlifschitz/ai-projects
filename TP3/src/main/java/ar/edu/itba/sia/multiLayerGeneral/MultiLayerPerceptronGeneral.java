package ar.edu.itba.sia.multiLayerGeneral;

public class MultiLayerPerceptronGeneral {
    private int nInputs, nHidden, nEpochs;
    private Neuron[] hiddenPerceptrons, inputPerceptrons;
    private double learning_rate;
    private Neuron output;

    public Neuron getOutput() {
        return output;
    }
    public MultiLayerPerceptronGeneral(int nInputs, int nHidden, int nEpochs, double learning_rate, double beta){
        this.nHidden = nHidden;
        this.nInputs = nInputs;
        this.nEpochs = nEpochs;
        this.learning_rate = learning_rate;

        hiddenPerceptrons = new Neuron[nHidden];
        inputPerceptrons = new Neuron[nInputs];
        output = new Neuron(MultiLayerType.OUTPUT, nInputs, nHidden, beta);
        
        for(int i = 0;i<hiddenPerceptrons.length; i++){
            hiddenPerceptrons[i] = new Neuron(MultiLayerType.HIDDEN, nInputs, nHidden, beta);
        }
        for(int i = 0;i<inputPerceptrons.length;i++){
            inputPerceptrons[i] = new Neuron(MultiLayerType.INPUT, nInputs, nHidden, beta);
        }
    }
    public void train(double[] input){
        double sumOfWeights;
        for(int i = 0;i<inputPerceptrons.length;i++){
            inputPerceptrons[i].setOutput(input[i]);
        }

        for(int i = 0;i<hiddenPerceptrons.length;i++){
        	sumOfWeights = dotProduct(hiddenPerceptrons[i].getWeights(),inputPerceptrons);
            hiddenPerceptrons[i].activationFunction(sumOfWeights);
        }

        sumOfWeights = dotProduct(output.getWeights(), hiddenPerceptrons);

        output.activationFunction(sumOfWeights);
    }
	
	private double dotProduct(double[] weights, Neuron[] perceptrons) {
		double sum = 0;
		
		for(int i = 0 ; i < perceptrons.length ; i++ ) {
			sum += weights[i] * perceptrons[i].getOutput();
		}
		
		return sum;
	}
	public void updateError(double expectedResult){
        output.setError((expectedResult-output.getOutput()) * (output.derivative()));
        output.setThreshold(output.getThreshold() + (learning_rate * output.getError()));

        for(int i = 0;i<hiddenPerceptrons.length;i++){
            output.updateWeights(i,learning_rate * output.getError() * hiddenPerceptrons[i].getOutput());
        }

        for(int i = 0;i<hiddenPerceptrons.length;i++){
            hiddenPerceptrons[i].setError(output.getWeights()[i] * output.getError() * hiddenPerceptrons[i].derivative());
            hiddenPerceptrons[i].setThreshold(hiddenPerceptrons[i].getThreshold() + (learning_rate * hiddenPerceptrons[i].getError()));
            for(int j = 0;j<inputPerceptrons.length; j++){
                hiddenPerceptrons[i].updateWeights(j,learning_rate * hiddenPerceptrons[i].getError() * inputPerceptrons[j].getOutput());
            }

            /*hiddenPerceptrons[i].updateWeights(learning_rate * hiddenPerceptrons[i].getError() * inputPerceptrons[0].getOutput(),
                    learning_rate * hiddenPerceptrons[i].getError() * inputPerceptrons[1].getOutput());*/
        }

    }
}
