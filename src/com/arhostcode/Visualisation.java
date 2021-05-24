package com.arhostcode;

import java.awt.*;
import java.util.ArrayList;

public class Visualisation extends Frame {
    public ArrayList<Label> ls = new ArrayList<>();

    public Visualisation(){
        for (int i = 0; i < 100; i++) {
            Label l = new Label(".");
            l.setLocation(0,0);
            l.setBackground(Color.cyan);
            ls.add(l);
            add(l);
        }
        setSize(250,330);
        setLayout(new FlowLayout());

    }

    public void paintWall(int x, int y){
        ls.get(x*10+y).setBackground(Color.BLACK);
    }
    public void paintTexture(int x, int y){
        ls.get(x*10+y).setBackground(Color.WHITE);
    }
    public void paint(int x, int y){
        ls.get(x*10+y).setBackground(Color.GREEN);
    }
    int prevX = 1;
    int prevY = 1;
    public void paintEnemy(int x, int y){
        ls.get(prevX*10+prevY).setBackground(Color.WHITE);
        ls.get(x*10+y).setBackground(Color.RED);
        prevX=x;
        prevY=y;
    }

}
