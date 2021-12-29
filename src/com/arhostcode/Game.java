package com.arhostcode;

import com.arhostcode.ai_core.GeneticCore;
import com.arhostcode.renderer.Visualizer;
import com.arhostcode.world.Board;
import com.arhostcode.world.Bot;

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

        board.setBlock(10,10);
        board.setBlock(10,8);
        board.setBlock(10,6);
        board.setBlock(10,4);
        board.setBlock(6,4);
        board.setBlock(6,6);
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
