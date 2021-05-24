package com.arhostcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    Visualisation v = new Visualisation();

    public Main(String[] args) throws InterruptedException, FileNotFoundException {

        int bot_count = 100;
        int gen = 10000;
        boolean isGraphical = false;

        boolean withBrains = false;

        GenCore.selecting_algorithm = GenCore.SELECTING_ALGORITHM.POINT_SELECTION;
        GenCore.pointSelectionNumber = 50;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]){
                case "--point": {
                    GenCore.selecting_algorithm = GenCore.SELECTING_ALGORITHM.POINT_SELECTION;
                    GenCore.pointSelectionNumber = Integer.parseInt(args[i + 1]);
                    i++;
                    break;
                }
                case "--block":{
                    GenCore.selecting_algorithm = GenCore.SELECTING_ALGORITHM.BLOCK_SELECTION;
                    break;
                }
                case "--gen":{
                    gen = Integer.parseInt(args[i+1]);
                    i++;
                    break;
                }
                case "--bots":{
                    bot_count = Integer.parseInt(args[i+1]);
                    i++;
                    break;
                }
                case "--graphical":{
                    isGraphical = true;
                    break;
                }
                case "--test":{
                    Bot.isTest = true;
                    Bot.testX = Integer.parseInt(args[i+1]);
                    Bot.testY = Integer.parseInt(args[i+2]);
                    i+=2;
                    break;
                }
                case "--brains":{
                    withBrains = true;
                    break;
                }

            }
        }

        v.setVisible(true);
        GenCore g = new GenCore(gen, bot_count, v,isGraphical);

        if(!withBrains) {
            g.run();
        } else {
            g.setGraphical(true);
            g.runWithBrains(load(), 100);
        }
    }


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        new Main(args);
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
