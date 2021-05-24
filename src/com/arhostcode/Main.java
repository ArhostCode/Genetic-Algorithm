package com.arhostcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    Visualisation v = new Visualisation();

    public Main() throws InterruptedException, FileNotFoundException {
        v.setVisible(true);
        Bot.isTest = true;
        GenCore g = new GenCore(100000, 300, v,true);
        GenCore.selecting_algorithm = GenCore.SELECTING_ALGORITHM.POINT_SELECTION;
        GenCore.pointSelectionNumber = 2;
        g.run();
        g.setGraphical(true);
        g.runWithBrains(load(),100);
        System.out.println(g.getLastBot().steps);
    }



    public Main(Brain brain) throws InterruptedException {
        v.setVisible(true);
        GenCore g = new GenCore(1000, 300, v,true);
        g.runWithBrains(brain.weights, 100);
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
