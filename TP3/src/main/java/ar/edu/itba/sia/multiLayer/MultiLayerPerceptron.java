package ar.edu.itba.sia.multiLayer;

public class MultiLayerPerceptron {
    private int nInputs, nHidden, nEpochs;
    private Neuron[] hiddenPerceptrons, inputPerceptrons;
    private double learning_rate;
    private Neuron output;

    public int getnInputs() {
        return nInputs;
    }
    public void setnInputs(int nInputs) {
        this.nInputs = nInputs;
    }
    public int getnHidden() {
        return nHidden;
    }
    public void setnHidden(int nHidden) {
        this.nHidden = nHidden;
    }
    public int getnEpochs() {
        return nEpochs;
    }
    public void setnEpochs(int nEpochs) {
        this.nEpochs = nEpochs;
    }
    public Neuron[] getHiddenPerceptrons() {
        return hiddenPerceptrons;
    }
    public void setHiddenPerceptrons(Neuron[] hiddenPerceptrons) {
        this.hiddenPerceptrons = hiddenPerceptrons;
    }
    public Neuron[] getInputPerceptrons() {
        return inputPerceptrons;
    }
    public void setInputPerceptrons(Neuron[] inputPerceptrons) {
        this.inputPerceptrons = inputPerceptrons;
    }
    public double getlearning_rate() {
        return learning_rate;
    }
    public void setlearning_rate(double LEARNING_RATE) {
        this.learning_rate = LEARNING_RATE;
    }
    public Neuron getOutput() {
        return output;
    }
    public void setOutput(Neuron output) {
        this.output = output;
    }

    public MultiLayerPerceptron(int nInputs, int nHidden, int nEpochs, double learning_rate, double beta){
        this.nHidden = nHidden;
        this.nInputs = nInputs;
        this.nEpochs = nEpochs;
        this.learning_rate = learning_rate;

        hiddenPerceptrons = new Neuron[nHidden];
        inputPerceptrons = new Neuron[nInputs];
        output = new Neuron(MultiLayerType.OUTPUT, beta);
        for(int i = 0;i<hiddenPerceptrons.length; i++){
            hiddenPerceptrons[i] = new Neuron(MultiLayerType.HIDDEN, beta);
        }
        for(int i = 0;i<inputPerceptrons.length;i++){
            inputPerceptrons[i] = new Neuron(MultiLayerType.INPUT, beta);
        }
    }
    public void train(double[] input){
        double sumOfWeights = 0;

        //Seteo los inputs
        for(int i = 0;i<inputPerceptrons.length;i++){
            inputPerceptrons[i].setOutput(input[i]);
        }

        //For hidden layer
        for(int i = 0;i<hiddenPerceptrons.length;i++){
            sumOfWeights = hiddenPerceptrons[i].getThreshold()
                    + hiddenPerceptrons[i].getWeights()[0] * inputPerceptrons[0].getOutput()
                    + hiddenPerceptrons[i].getWeights()[1] * inputPerceptrons[1].getOutput();
            hiddenPerceptrons[i].activationFunction(sumOfWeights);
        }
        //For output
        sumOfWeights = output.getThreshold()
                + (output.getWeights()[0] * hiddenPerceptrons[0].getOutput())
                + (output.getWeights()[1] * hiddenPerceptrons[1].getOutput());
        output.activationFunction(sumOfWeights);
    }
    public void updateError(double expectedResult){
        output.setError((expectedResult-output.getOutput()) * (output.derivative()));
        output.setThreshold(output.getThreshold() + (learning_rate * output.getError()));
        output.updateWeights(learning_rate * output.getError() * hiddenPerceptrons[0].getOutput(),
                learning_rate * output.getError() * hiddenPerceptrons[1].getOutput());
        for(int i = 0;i<hiddenPerceptrons.length;i++){
            hiddenPerceptrons[i].setError(output.getWeights()[i] * output.getError() * hiddenPerceptrons[i].derivative());
            hiddenPerceptrons[i].setThreshold(hiddenPerceptrons[i].getThreshold() + (learning_rate * hiddenPerceptrons[i].getError()));
            hiddenPerceptrons[i].updateWeights(learning_rate * hiddenPerceptrons[i].getError() * inputPerceptrons[0].getOutput(),
                    learning_rate * hiddenPerceptrons[i].getError() * inputPerceptrons[1].getOutput());
        }

    }
}
