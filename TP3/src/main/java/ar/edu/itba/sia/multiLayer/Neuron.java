package ar.edu.itba.sia.multiLayer;

public class Neuron {
    private MultiLayerType type;
    private double[]  weights = {-0.3,-.8};
    private double threshold = .1, error = 0, output = 0, beta = 0.1;

    public Neuron(MultiLayerType type, double beta){
        this.type = type;
        this.beta = beta;
        System.out.println("Threshold: " + threshold + " - weight: ( " + weights[0] + "," + weights[1] +" )");
    }
    public double derivative(){
        //return (1 + output)*(1 - output);
        return beta * (1-Math.pow(Math.tanh(output),2));
    }
    public MultiLayerType getType() {
        return type;
    }
    public void setType(MultiLayerType type) {
        this.type = type;
    }
    public double[] getWeights() {
        return weights;
    }
    public void setWeights(double[] weights) {
        this.weights = weights;
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
        //We will be using the sigmoid function
        //output = 1.0 / (1.0 + Math.exp(-1.0 * sum));
        output = Math.tanh(beta * sum);
        return output;
    }

    public void updateWeights(double add1, double add2){
        weights[0] += add1;
        weights[1] += add2;
    }

}
