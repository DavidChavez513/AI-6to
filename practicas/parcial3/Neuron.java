package parcial3;

public class Neuron {

    double[] weights;
    double bias;
    double learningRate = 0.1;

    public Neuron(int size) {
        weights = new double[size];
        bias = Math.random() - 0.7; // Valor inicial del BIAS
    }

    private int sigma(double predict) {
        return predict >= 0 ? 1 : 0;
    }

    public int prediction(double[] inputs) {
        double sum = bias;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * inputs[i];
        }

        return sigma(sum);
    }

    public void train(double[][] data, int[] labels, int epoch) {
        for (int e = 0; e < epoch; e++) {
            for (int i = 0; i < data[0].length; i++) {
                int output = prediction(weights);
                double error = labels[i] - output;
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * error * data[j][i];
                }

                bias -= learningRate * error;
            }
        }
    }

}
