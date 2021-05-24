package com.arhostcode;



public class GenCore {

    Bot[] bots;

    boolean isGraphical;
    private int gen = 0;
    public Visualisation v;
    private Brain[] newBrains;

    private Brain last;

    public GenCore(int gen, int bot_count,Visualisation v, boolean isGraphical){
        this.gen = gen;
        this.isGraphical = isGraphical;
        bots = new Bot[bot_count];
        for (int i = 0; i < bot_count; i++) {
            bots[i] = new Bot(v,isGraphical);
        }
        this.v = v;
    }

    public void run() throws InterruptedException {
        boolean genAlive = true;
        for (int i = 0; i < gen; i++) {
            genAlive = true;
            while (genAlive){
                genAlive = false;
                for (int j = 0; j < bots.length; j++) {
                    while (bots[j].step()){
                        genAlive=true;
                    }
                    //Thread.sleep(1);
                }
            }

            newBrains = selection(i);

            for (int j = 0; j < bots.length; j++) {
                bots[j] = new Bot(newBrains[j],v,isGraphical);
            }

        }

    }

    public Brain getLast() {
        return last;
    }

    //Test with generic weights
    public void runWithBrains(double[] weights) throws InterruptedException {
        Brain b = new Brain();
        b.weights = weights;
        Bot bot = new Bot(b,v,isGraphical);
        while (bot.step()){
            Thread.sleep(100);
        }
    }

    private Brain[] selection(int k){

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
        System.out.println("The best - " + bots[0].getFitness() + " gen-" + k + " steps - "+bots[0].steps);

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
            if((int)(Math.random()*200) == 5){
                System.out.println("Muttated");
                brains[i].weights[(int)(Math.random()*20)] = (Math.random() * 8 -4);
            }

        }
        last = bots[0].brain;
        return brains;
    }

    public void setGraphical(boolean graphical) {
        isGraphical = graphical;
    }
}
