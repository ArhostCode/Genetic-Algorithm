package com.arhostcode;

public class Brain {



    public double[] weights = new double[40];

    private double[] hidden = new double[8];

    public void randomize(){
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random() * 8 -4);
        }
    }

    public double compute(double[] sensors){
        hidden = new double[8];
        int k = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                hidden[i] += sensors[j] * weights[k];
                k++;
            }
        }
        double res = 0;

        for (int i = 0; i < 8; i++) {
            res += hidden[i] * weights[k];
            k++;
        }

        return res;

    }

    public Brain(){}
    public Brain(double[] weights){
        this.weights = weights;
    }

}
