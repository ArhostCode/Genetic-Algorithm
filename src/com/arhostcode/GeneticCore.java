package com.arhostcode;

import java.util.ArrayList;

public class GeneticCore {

    public static Bot[] getNewPopulation(Bot[] bots) {
        sort(bots);
        return createNewPopulation(bots);
    }

    private static Bot getRandomWithWeights(Bot[] bots, int allFit) {
        int fit = (int) (Math.random() * allFit);
        for (Bot b : bots) {
            fit -= b.getFitness();
            if (fit <= 0)
                return b;
        }
        return null;
    }

    private static Bot[] createNewPopulation(Bot[] bots) {
        Bot[] newGen = new Bot[bots.length];
        int fitSum = calculateFitSum(bots);
        Brain brain;
        for (int i = 0; i < bots.length; i++) {
            brain = new Brain();
            for (int j = 0; j < Brain.INPUT_LAYER_NEURON_COUNT * Brain.HIDDEN_LAYER_NEURON_COUNT + Brain.HIDDEN_LAYER_NEURON_COUNT * Brain.OUTPUT_LAYER_NEURON_COUNT; j++) {
                brain.setWeight(j, getRandomWithWeights(bots, fitSum).getBrain().getWeight(j));
                if ((int) (Math.random() * 20) == 5) {
                    brain.setWeight(j, Math.random() * 8 - 4);
                }
            }
            newGen[i] = new Bot(brain);
        }
        return newGen;
    }

    private static int calculateFitSum(Bot[] bots) {
        int fitSum = 0;
        for (Bot b : bots) {
            fitSum += b.getFitness();
        }
        return fitSum;
    }

    private static Bot[] sort(Bot[] bots) {
        Bot s;
        for (int out = bots.length - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (bots[in].getFitness() < bots[in + 1].getFitness()) {
                    s = bots[in];
                    bots[in] = bots[in + 1];
                    bots[in + 1] = s;
                }
            }
        }
        return bots;
    }

}
