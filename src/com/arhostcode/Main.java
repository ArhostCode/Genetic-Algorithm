package com.arhostcode;

public class Main {

    public Main(String[] args) throws InterruptedException {
        Game game = new Game(100, 10);
        game.runGame();
    }


    public static void main(String[] args) throws InterruptedException{
        new Main(args);
    }
}
