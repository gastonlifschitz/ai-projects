package ar.edu.itba.sia.multiLayerGeneral;

public class Neuron {
    private MultiLayerType type;
    private double[]  weights;
    private double threshold = .1, error = 0, output = 0, beta;

    public Neuron(MultiLayerType type, int nInput, int nHidden, double beta){
        this.type = type;
        this.beta = beta;
        int size=1;
        switch (type){
            case OUTPUT:
                size = nHidden;
                weights = new double[size];
                break;
            case HIDDEN:
                size = nInput;
                weights = new double[size];
                break;
            case INPUT:
            default:
                size = 2;
                weights = new double[size];
        }

        for (int i = 0;i< size;i++){
            weights[i] = 0.0 - Math.random();
        }
    }
    public double derivative(){
        return beta * (1-Math.pow(Math.tanh(output),2));
    }
    public double[] getWeights() {
        return weights;
    }
    public double getThreshold() {
        return threshold;
    }
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    public double getError() {
        return error;
    }
    public void setError(double error) {
        this.error = error;
    }
    public double getOutput() {
        return output;
    }
    public void setOutput(double output) {
        this.output = output;
    }
    public double activationFunction(double sum){
        output = Math.tanh(beta * sum);
        return output;
    }
    public void updateWeights(int index, double deltaW){
        weights[index] += deltaW;
    }

}
