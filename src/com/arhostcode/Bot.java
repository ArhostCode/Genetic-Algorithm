package com.arhostcode;

public class Bot {

    private int steps = 0;

    public Board board;
    public Brain brain;

    public int x;

    public int y;

    public int[] sensors = new int[4]; // 0 - left, 1 - up, 2 - right, 3 - down

    public Bot(){
        board = new Board();
        brain = new Brain();
        board.fill();
        brain.randomize();
        x = 4;
        y = 4;
    }

    public Bot(Brain brain){
        board = new Board();
        board.fill();
        this.brain = brain;
        x = 4;
        y = 4;
    }

    public void getSensors(){
        sensors[0] = board.field[x-1][y];
        sensors[1] = board.field[x][y-1];
        sensors[2] = board.field[x+1][y];
        sensors[3] = board.field[x][y+1];
    }

    public boolean step(){

        if (steps>100){
            for (int i = 0; i < 20; i++) {
                System.out.print(brain.weights[i] + " ");

            }
            System.exit(0);
        }
        if(x == Board.size+1 | y == Board.size+1 | x == 0 | y == 0){
            return false;
        }
        getSensors();
        int res = brain.compute(sensors);

        if(res<=-4){
            x = x-1;
        }
        if(res>-4 & res <= -3){
            y = y-1;
        }
        if(res>-3 & res <= -2){
            x = x+1;
        }
        if(res>-2){
            y = y+1;
        }

        steps++;
        return true;
    }

    public int getFitness(){
        return steps*steps;
    }

}
