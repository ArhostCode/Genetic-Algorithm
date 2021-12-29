package com.arhostcode;

import com.arhostcode.ai_core.GeneticCore;
import com.arhostcode.renderer.Visualizer;
import com.arhostcode.world.Board;
import com.arhostcode.world.Bot;

public class Game {

    private Bot[] bots;
    private final int generationCount;
    private Board board;
    private final Visualizer visualizer;

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
        for (int i = 0; i < generationCount; i++) {
            runPopulation();
            bots = GeneticCore.getNewPopulation(bots);
        }
    }

    private void runPopulation() throws InterruptedException {
        for (Bot bot : bots) {
            while (bot.isAlive()) {
                bot.checkAlive(board);
                if (bot.isAlive()) {
                    bot.doStep(board);
                    if (bot.getFitness() > 100)
                        Thread.sleep(100);
                    visualize(bot);
                }
            }
        }
    }

    private void visualize(Bot bot){
        visualizer.paintBoard(board);
        visualizer.paintEnemy(bot.getPosition());
    }
}
