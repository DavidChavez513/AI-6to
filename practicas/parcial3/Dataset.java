package parcial3;

public class Dataset {

    private double[][] andGateInputs = {
        {0, 0},
        {0, 1},
        {1, 0},
        {1, 1}
    };

    private int [] andGateOutputs = {0, 0, 0, 1};

    public double[][] getAndGateInputs() {
        return andGateInputs;
    }

    public int[] getAndGateOutputs() {
        return andGateOutputs;
    }

    private double[][] orGateInputs = {
        {0, 0},
        {0, 1},
        {1, 0},
        {1, 1}
    };

    private int[] orGateOutputs = {0, 1, 1, 1};

    public double[][] getOrGateInputs() {
        return orGateInputs;
    }

    public int[] getOrGateOutputs() {
        return orGateOutputs;
    }

    private double[][] andGateInputs3Inputs = {
        {0, 0, 0},
        {0, 0, 1},
        {0, 1, 0},
        {0, 1, 1},
        {1, 0, 0},
        {1, 0, 1},
        {1, 1, 0},
        {1, 1, 1}
    };

    private int[] andGateOutputs3Inputs = {0, 0, 0, 0, 0, 0, 0, 1};

    public double[][] getAndGateInputs3Inputs() {
        return andGateInputs3Inputs;
    }

    public int[] getAndGateOutputs3Inputs() {
        return andGateOutputs3Inputs;
    }

    private double[][] orGateInputs3Inputs = {
        {0, 0, 0},
        {0, 0, 1},
        {0, 1, 0},
        {0, 1, 1},
        {1, 0, 0},
        {1, 0, 1},
        {1, 1, 0},
        {1, 1, 1}
    };

    private int[] orGateOutputs3Inputs = {0, 1, 1, 1, 1, 1, 1, 1};

    public double[][] getOrGateInputs3Inputs() {
        return orGateInputs3Inputs;
    }

    public int[] getOrGateOutputs3Inputs() {
        return orGateOutputs3Inputs;
    }
}
