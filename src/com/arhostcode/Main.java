package com.arhostcode;

public class Main {

    Visualisation v = new Visualisation();

    public Main() throws InterruptedException {
        v.setVisible(true);
        GenCore g = new GenCore(5000, 300, v,false);
        g.run();
        g.setGraphical(true);
        for (int i = 0; i < 20; i++) {
            System.out.print(g.getLast().weights[i] + ", ");
        }
        g.runWithBrains(g.getLast().weights);
    }



    public Main(Brain brain) throws InterruptedException {
        v.setVisible(true);
        GenCore g = new GenCore(1000, 300, v,true);
        g.runWithBrains(brain.weights);
    }

    public static void main(String[] args) throws InterruptedException {
        new Main();
    }
}
