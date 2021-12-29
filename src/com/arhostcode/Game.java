package com.arhostcode;

public class Game {

    private Bot[] bots;
    private int generationCount;
    private Board board;
    private Visualizer visualizer;

    public Game(int generationCount, int populationCount) {
        this.generationCount = generationCount;
        visualizer = new Visualizer();
        createBoard();
        createFirstPopulation(populationCount);
    }

    private void createFirstPopulation(int populationCount) {
        bots = new Bot[populationCount];
        for (int i = 0; i < populationCount; i++) {
            bots[i] = new Bot();
        }
    }

    private void createBoard() {
        board = new Board();
        board.initialize();
    }

    public void runGame() throws InterruptedException {
        boolean genAlive;
        for (int i = 0; i < generationCount; i++) {
            genAlive = true;
            while (genAlive) {
                genAlive = false;
                for (Bot bot : bots) {
                    while (bot.isAlive()) {
                        bot.checkAlive(board);
                        if (bot.isAlive()) {
                            visualizer.paintBoard(board);
                            visualizer.paintEnemy(bot.getPosition());
                            bot.doStep(board);
                            if(bot.getFitness() > 100)
                                Thread.sleep(100);
                            genAlive = true;
                        }
                    }
                }
            }
            bots = GeneticCore.getNewPopulation(bots);
        }
    }
}
