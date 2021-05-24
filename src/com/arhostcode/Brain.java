package com.arhostcode;

public class Brain {

    public double[] weights = new double[20];

    private int[] hidden = new int[4];

    public void randomize(){
        for (int i = 0; i < 20; i++) {
            weights[i] = (Math.random() * 8 -4);
        }
    }

    public int compute(int[] sensors){
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                hidden[i] += sensors[j] * weights[k];
                k++;
            }
        }
        int res = 0;

        for (int i = 0; i < 4; i++) {
            res += hidden[i] * weights[k];
            k++;
        }

        return res;

    }
}
