package com.arhostcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    Visualisation v = new Visualisation();

    public Main() throws InterruptedException, FileNotFoundException {
        v.setVisible(true);
        GenCore g = new GenCore(10000, 500, v,false);
        Bot.isTest = false;
        g.run();
        g.setGraphical(true);
        g.runWithBrains(load());
        System.out.println(g.getLastBot().steps);
    }



    public Main(Brain brain) throws InterruptedException {
        v.setVisible(true);
        GenCore g = new GenCore(1000, 300, v,true);
        g.runWithBrains(brain.weights);
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        new Main();
    }

    public double[] load() throws FileNotFoundException {
        File f = new File("weights");
        Scanner sc = new Scanner(f);
        double[] weights = new double[40];
        int k = 0;
        while (sc.hasNextLine()){
            weights[k] = Double.parseDouble(sc.nextLine());
            k++;
        }
        return weights;
    }
}
