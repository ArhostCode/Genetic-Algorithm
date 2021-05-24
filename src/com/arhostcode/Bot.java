package com.arhostcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bot {

    public int steps = 0;

    public Board board;
    public Brain brain;
    private Visualisation v;
    private boolean isGraphical;

    public static int[][] test;

    public int x;

    public int y;

    private int prevx;
    private int prevy;
    private int ppx;
    private int ppy;

    public int[] sensors = new int[4]; // 0 - left, 1 - up, 2 - right, 3 - down

    public Bot(Visualisation v, boolean isGraphical){
        board = new Board();
        brain = new Brain();
        board.fill();
        brain.randomize();
//        x = (int)(1+Math.random()*8);
//        y = (int)(1+Math.random()*8);
        x = 1;
        y = 1;
        this.v = v;
        this.isGraphical = isGraphical;
        createTest();
    }

    public Bot(Brain brain, Visualisation v,boolean isGraphical){
        board = new Board();
        board.fill();
        this.brain = brain;
//        x = (int)(1+Math.random()*8);
//        y = (int)(1+Math.random()*8);
        x = 1;
        y = 1;
        this.v = v;
        this.isGraphical = isGraphical;
        createTest();
    }

    private void paintBoard(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(board.field[i][j] == 1){
                    v.paintWall(i,j);
                }else{
                    v.paintTexture(i,j);
                }
            }
        }
    }

    public void createTest(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board.field[i][j] = test[i][j];
            }
        }

    }


    private void paintBot(){
        v.paintEnemy(x,y);
    }

    public void getSensors(){
        sensors[0] = board.field[x-1][y];
        sensors[1] = board.field[x][y-1];
        sensors[2] = board.field[x+1][y];
        sensors[3] = board.field[x][y+1];
    }

    public boolean step(){
        if(isGraphical){
            paintBoard();
            paintBot();
        }

        ppx = prevx;
        ppy = prevy;
        prevx = x;
        prevy = y;

//        if(steps%5==0){
//            int nx = x;
//            int ny = y;
//
//            while (nx==x | ny == y){
//                nx = (int)(1+Math.random()*8);
//                ny = (int)(1+Math.random()*8);
//            }
//
//            board.field[nx][ny] = 1;
//
//        }

        /*if (getFitness() > 1000000){
            for (int i = 0; i < 20; i++) {
                System.out.print(brain.weights[i] + ", ");
            }
            try {
                new Main(brain);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        if(board.field[x][y] == 1){
            return false;
        }

        if(x == 8 & y == 8){
            steps = 10000;
            return false;
        }

        getSensors();
        double res = brain.compute(sensors);

        if(steps>150){
            steps = 0;
            return false;
        }

        if(res<=-3 & res > -4){
            x = x-1;
            steps++;
        } else if(res<=-2 & res > -3){
            y = y-1;
            steps++;
        } else if(res<=-1 & res > -2){
            x = x+1;
            steps++;
        }else if(res<=0 & res > -1){
            y = y+1;
            steps++;
        } else {
            return false;
        }

//        System.out.println("x " + x + " prevX " + prevx);
//        System.out.println("y " + y + " prevY " + prevy);

        if(x == ppx & y == ppy){
            steps = 0;
            return false;
        }
        return true;
    }

    public int getFitness(){
        return steps;
    }

}
