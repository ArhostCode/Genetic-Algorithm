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
    public static boolean isTest;
    public static boolean reverseStep = false;

    public int x;
    public int y;

    public static int testX;
    public static int testY;

    private int prevx;
    private int prevy;
    private int ppx;
    private int ppy;

    public double[] sensors = new double[4]; // 0 - left, 1 - up, 2 - right, 3 - down

    public Bot(Visualisation v, boolean isGraphical){

        board = new Board();
        brain = new Brain();
        board.fill();
        brain.randomize();
        x = (int)(1+Math.random()*Board.size);
        y = (int)(1+Math.random()*Board.size);
        if(isTest){
            x = 1;
            y = 1;
        }
        this.v = v;
        this.isGraphical = isGraphical;
        if(isTest){
            createTest();
        }
    }

    public Bot(Brain brain, Visualisation v,boolean isGraphical){
        board = new Board();
        board.fill();
        this.brain = brain;
        x = (int)(1+Math.random()*Board.size);
        y = (int)(1+Math.random()*Board.size);
        if(isTest){
            x = 1;
            y = 1;
        }
        this.v = v;
        this.isGraphical = isGraphical;
        if(isTest){
            createTest();
        }
    }

    private void paintBoard(){
        for (int i = 0; i < Board.size+2; i++) {
            for (int j = 0; j < Board.size+2; j++) {
                if(board.field[i][j] == 1){
                    v.paintWall(i,j);
                }else{
                    v.paintTexture(i,j);
                }
            }
        }
    }

    public void createTest(){
        for (int i = 0; i < Board.size+2; i++) {
            for (int j = 0; j < Board.size+2; j++) {
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

        if(steps%5==0 & !isTest){
            int nx = x;
            int ny = y;

            while (nx==x | ny == y){
                nx = (int)(1+Math.random()*Board.size);
                ny = (int)(1+Math.random()*Board.size);
            }

            board.field[nx][ny] = 1;

        }
        if(board.field[x][y] == 1){
            return false;
        }

        if(x == testX & y == testY & isTest){
            steps = 10000;
            System.out.println("Congratulations");
            return false;
        }

        getSensors();
        double res = brain.compute(sensors);


        if(steps>70 & !isTest){
            steps = 0;
            System.out.println("TOOOOOOO MANY");
            try {
                new GenCore(0,0,v,true).save(brain);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.exit(0);
            return false;
        }
        if(isTest)
            board.field[x][y] = 1;

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
