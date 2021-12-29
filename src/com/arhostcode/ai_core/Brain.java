package com.arhostcode.ai_core;

import com.arhostcode.world.Direction;

import java.util.Arrays;

public class Brain {

    public static final int INPUT_LAYER_NEURON_COUNT = 4;
    public static final int OUTPUT_LAYER_NEURON_COUNT = 4;
    public static final int HIDDEN_LAYER_NEURON_COUNT = 5;

    private final int RANDOM_WEIGHT_OFFSET = 4;

    private double[] layer0 = new double[INPUT_LAYER_NEURON_COUNT];
    private double[] layer1 = new double[HIDDEN_LAYER_NEURON_COUNT];
    private double[] layer2 = new double[OUTPUT_LAYER_NEURON_COUNT];

    private double[] layer1Weights = new double[INPUT_LAYER_NEURON_COUNT * HIDDEN_LAYER_NEURON_COUNT];
    private double[] layer2Weights = new double[OUTPUT_LAYER_NEURON_COUNT * HIDDEN_LAYER_NEURON_COUNT];

    public Direction compute(double[] sensorsValue) {
        fillWeightsArraysWithZero();
        layer0 = sensorsValue;

        calculateHiddenLayerNeurons();
        calculateOutputLayerNeurons();

        return getDirectionFromOutputNeurons();
    }

    private void calculateHiddenLayerNeurons() {
        int weightIterator = 0;
        for (int i = 0; i < layer1.length; i++) {
            for (double layer0NeuronValue : layer0) {
                layer1[i] += layer0NeuronValue * layer1Weights[weightIterator];
                weightIterator += 1;
            }
        }
    }

    private void calculateOutputLayerNeurons() {
        int weightIterator = 0;
        for (int i = 0; i < layer2.length; i++) {
            for (double layer1NeuronValue : layer1) {
                layer2[i] += layer1NeuronValue * layer2Weights[weightIterator];
                weightIterator += 1;
            }
        }
    }

    private Direction getDirectionFromOutputNeurons() {
        if (layer2[0] > layer2[1] & layer2[0] > layer2[2] & layer2[0] > layer2[3])
            return Direction.UP;
        if (layer2[1] > layer2[0] & layer2[1] > layer2[2] & layer2[1] > layer2[3])
            return Direction.DOWN;
        if (layer2[2] > layer2[0] & layer2[2] > layer2[1] & layer2[2] > layer2[3])
            return Direction.LEFT;
        if (layer2[3] > layer2[0] & layer2[3] > layer2[1] & layer2[3] > layer2[2])
            return Direction.RIGHT;
        return Direction.UP;
    }


    public void randomizeWeights() {
        for (int i = 0; i < layer1Weights.length; i++) {
            layer1Weights[i] = Math.random() * (RANDOM_WEIGHT_OFFSET * 2) - RANDOM_WEIGHT_OFFSET;
        }
        for (int i = 0; i < layer2Weights.length; i++) {
            layer2Weights[i] = Math.random() * (RANDOM_WEIGHT_OFFSET * 2) - RANDOM_WEIGHT_OFFSET;
        }

    }

    public double getWeight(int weightID) {
        if (weightID < INPUT_LAYER_NEURON_COUNT * HIDDEN_LAYER_NEURON_COUNT)
            return layer1Weights[weightID];
        else if (weightID < INPUT_LAYER_NEURON_COUNT * HIDDEN_LAYER_NEURON_COUNT + HIDDEN_LAYER_NEURON_COUNT * OUTPUT_LAYER_NEURON_COUNT)
            return layer2Weights[weightID - INPUT_LAYER_NEURON_COUNT * HIDDEN_LAYER_NEURON_COUNT];
        else throw new ArrayIndexOutOfBoundsException("Goes beyond the arrays of weights");
    }

    public void setWeight(int weightID, double weightValue) {
        if (weightID < INPUT_LAYER_NEURON_COUNT * HIDDEN_LAYER_NEURON_COUNT)
            layer1Weights[weightID] = weightValue;
        else if (weightID < INPUT_LAYER_NEURON_COUNT * HIDDEN_LAYER_NEURON_COUNT + HIDDEN_LAYER_NEURON_COUNT * OUTPUT_LAYER_NEURON_COUNT)
            layer2Weights[weightID - INPUT_LAYER_NEURON_COUNT * HIDDEN_LAYER_NEURON_COUNT] = weightValue;
        else throw new ArrayIndexOutOfBoundsException("Goes beyond the arrays of weights");
    }

    private void fillWeightsArraysWithZero() {
        Arrays.fill(layer1, 0);
        Arrays.fill(layer2, 0);
    }

}
