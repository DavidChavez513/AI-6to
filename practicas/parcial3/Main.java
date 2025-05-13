package parcial3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Dataset dst = new Dataset();

        double[][] inputs = dst.getAndGateInputs();
        int[] labels = dst.getAndGateOutputs(); // Etiquetas esperadas para la compuerta AND

        Neuron perceptron1 = new Neuron(inputs[0].length);

        System.out.println("======================= Perceptron 2 inputs AND ===================================");
        perceptron1.train(dst.getAndGateInputs(), dst.getAndGateOutputs(), 10000);

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Entrada: " + Arrays.toString(inputs[i]) +
                    " => Esperado: " + labels[i] +
                    " | Predicho: " + perceptron1.prediction(inputs[i]));
        }


        System.out.println("======================= Perceptron 3 inputs AND ===================================");

        inputs = dst.getAndGateInputs3Inputs();
        labels = dst.getAndGateOutputs3Inputs();

        Neuron percep2 = new Neuron(inputs.length);
        percep2.train(inputs, labels, 10000);
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Entrada: " + Arrays.toString(inputs[i]) +
                    " => Esperado: " + labels[i] +
                    " | Predicho: " + percep2.prediction(inputs[i]));
        }

        System.out.println("======================= Perceptron 2 inputs OR ===================================");


        inputs = dst.getOrGateInputs();
        labels = dst.getOrGateOutputs();

        Neuron percep3 = new Neuron(inputs.length);
        percep3.train(inputs, labels, 10000);
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Entrada: " + Arrays.toString(inputs[i]) +
                    " => Esperado: " + labels[i] +
                    " | Predicho: " + percep3.prediction(inputs[i]));
        }

        System.out.println("======================= Perceptron 3 inputs OR ===================================");


        inputs = dst.getOrGateInputs3Inputs();
        labels = dst.getOrGateOutputs3Inputs();

        Neuron percep4 = new Neuron(inputs.length);
        percep4.train(inputs, labels, 10000);
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Entrada: " + Arrays.toString(inputs[i]) +
                    " => Esperado: " + labels[i] +
                    " | Predicho: " + percep4.prediction(inputs[i]));
        }

    }
}