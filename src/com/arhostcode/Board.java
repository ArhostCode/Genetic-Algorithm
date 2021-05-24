package com.arhostcode;

public class Board {

    public static final int size = 18;
    double[][] field = new double[size+2][size+2];

    public void fill(){
        for (int i = 0; i < size+2; i++) {
            for (int j = 0; j < size+2; j++) {
                if(i == 0 | j == 0 | i == size+1 | j == size+1){
                    field[i][j] = 1;
                }else{
                    field[i][j] = 0;
                }
            }
        }
    }


}
