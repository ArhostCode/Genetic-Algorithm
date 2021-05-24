package com.arhostcode;



public class GenCore {

    Bot[] bots;

    private int gen = 0;
    public Visualisation v;
    private Brain[] newBrains;

    public GenCore(int gen, int bot_count,Visualisation v){
        this.gen = gen;
        bots = new Bot[bot_count];
        for (int i = 0; i < bot_count; i++) {
            bots[i] = new Bot();
        }
        this.v = v;
        paintBoard();
    }

    private void paintBoard(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(i == 0 | j == 0 | i == 9 | j == 9){
                    v.paintWall(i,j);
                }else {
                    v.paintTexture(i,j);
                }
            }
        }
    }

    public void run() {
        boolean genAlive = true;
        for (int i = 0; i < gen; i++) {
            genAlive = true;
            while (genAlive){
                genAlive = false;
                for (int j = 0; j < bots.length; j++) {
                    while (bots[j].step()){
                        genAlive=true;
                        //v.paintEnemy(bots[j].x,bots[j].y);
                        //Thread.sleep(20);
                    }
                }
            }

            newBrains = selection();

            for (int j = 0; j < bots.length; j++) {
                bots[j] = new Bot(newBrains[j]);
            }

        }
    }


    //Test with generic weights
    public void runWithBrains(double[] weights) throws InterruptedException {
        Brain b = new Brain();
        b.weights = weights;
        Bot bot = new Bot(b);
        v.paintEnemy(bot.x,bot.y);
        while (bot.step()){
            v.paintEnemy(bot.x,bot.y);
            Thread.sleep(100);
        }
    }

    private Brain[] selection(){

        boolean sorted = false;

        Bot sortBot;

        while (!sorted){
            sorted = true;
            for (int i = 0; i < bots.length-1; i++) {
                if(bots[i].getFitness() < bots[i+1].getFitness()){
                    sorted = false;
                    sortBot = bots[i];
                    bots[i] = bots[i+1];
                    bots[i+1] = sortBot;
                }
            }
        }
        System.out.println("The best - " + bots[0].getFitness());
        Brain[] brains = new Brain[bots.length];
        int t = (int)(Math.random()*20);
        int st = (int)(Math.random()*20);
        for (int i = 0; i < brains.length; i++) {
            brains[i] = new Brain();
        }
        for (int i = 0; i < brains.length; i++) {

            //Selection for first 3
            t = (int)(Math.random()*20);
            for (int j = 0; j < t; j++) {
                brains[i].weights[j] = bots[(int)(Math.random()*3)].brain.weights[j];
            }
            st = t;
            t = (int)(t + Math.random()*(20-t));
            for (int j = st; j < t; j++) {
                brains[i].weights[j] = bots[(int)(Math.random()*3)].brain.weights[j];
            }
            for (int j = t; j < 20; j++) {
                brains[i].weights[j] = bots[(int)(Math.random()*3)].brain.weights[j];
            }


            //Mutation
            if((int)(Math.random()*100) == 5){
                System.out.println("Muttated");
                brains[i].weights[(int)(Math.random()*20)] = (Math.random() * 8 -4);
            }

        }
        return brains;
    }

}
