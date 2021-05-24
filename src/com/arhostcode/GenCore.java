package com.arhostcode;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GenCore {

    Bot[] bots;

    boolean isGraphical;
    private int gen = 0;
    public Visualisation v;
    private Brain[] newBrains;

    private Brain last;
    private Bot lastBot;

    public GenCore(int gen, int bot_count,Visualisation v, boolean isGraphical){
        this.gen = gen;
        createTest();
        lastBot = new Bot(v, isGraphical);
        this.isGraphical = isGraphical;
        bots = new Bot[bot_count];
        for (int i = 0; i < bot_count; i++) {
            bots[i] = new Bot(v,isGraphical);
        }
        this.v = v;

    }

    public void createTest(){
        int[][] field = new int[10][10];
        File f = new File("test");
        try {
            Scanner sc = new Scanner(f);
            int k = 0;
            String q = "";
            while (sc.hasNextLine()){
                q = sc.nextLine();
                //System.out.println(q);
                for (int i = 0; i < q.length(); i++) {
                    field[k][i] = Integer.parseInt(q.split("")[i]);
                }
                k++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bot.test = field;
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
//                        Thread.sleep(50);
                    }

                }
            }
            System.out.println("Selection");
            newBrains = selection(i);

            for (int j = 0; j < bots.length; j++) {
                bots[j] = new Bot(newBrains[j],v,isGraphical);
            }

        }
        try {
            save(lastBot.brain);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Brain getLast() {
        return last;
    }

    public Bot getLastBot() {
        return lastBot;
    }

    //Test with generic weights
    public void runWithBrains(double[] weights) throws InterruptedException {
        Brain b = new Brain();
        b.weights = weights;
        Bot bot = new Bot(b,v,isGraphical);
        while (bot.step()){
            Thread.sleep(100);
        }
        lastBot=bot;
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
        lastBot = bots[0];
        Brain[] brains = new Brain[bots.length];

        for (int i = 0; i < brains.length; i++) {

            brains[i] = pointSelection();


            //Mutation
            if(k!=gen-1) {  //Need
                if ((int) (Math.random() * 100) == 5) {
                    System.out.println("Muttated");
                    brains[i].weights[(int) (Math.random() * 40)] = (Math.random() * 4 - 2);
                }
            }
        }
        last = bots[0].brain;
        return brains;
    }

    public void setGraphical(boolean graphical) {
        isGraphical = graphical;
    }

    public void save(Brain brain) throws FileNotFoundException {
        File f = new File("weights");
        PrintWriter pw = new PrintWriter(f);
        for (int i = 0; i < brain.weights.length; i++) {
            pw.println(brain.weights[i]);
        }
        pw.flush();
        pw.close();
    }

    public Brain pointSelection(){
        Brain brain = new Brain();
        for (int j = 0; j < 40; j++) {
            brain.weights[j] = bots[(int)(Math.random()*(bots.length/2))].brain.weights[j];
        }
        return brain;
    }

    int t = 0;
    int st = 0;

    public Brain blockSelection(){
        Brain brain = new Brain();
        t = (int)(Math.random()*40);
        for (int j = 0; j < t; j++) {
            brain.weights[j] = bots[(int)(Math.random()*3)].brain.weights[j];
        }
        st = t;
        t = (int)(t + Math.random()*(40-t));
        for (int j = st; j < t; j++) {
            brain.weights[j] = bots[(int)(Math.random()*3)].brain.weights[j];
        }
        for (int j = t; j < 40; j++) {
            brain.weights[j] = bots[(int)(Math.random()*3)].brain.weights[j];
        }
        return brain;
    }


}
