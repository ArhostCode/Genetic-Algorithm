package com.arhostcode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Visualisation extends JFrame {
    public ArrayList<JLabel> ls = new ArrayList<>();
    public JLabel gen;
    public JLabel step;
    public Visualisation(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gen = new JLabel("0");
        step = new JLabel("0");

        JPanel p = new JPanel();

        p.setLocation(10,10);
        p.setSize(250,330);
        p.setMaximumSize(new Dimension(250,330));
        p.setPreferredSize(new Dimension(250,330));

        for (int i = 0; i < 100; i++) {
            JLabel l = new JLabel();
            l.setSize(20,20);
            l.setPreferredSize(new Dimension(20,20));
            l.setMaximumSize(new Dimension(20,20));
            l.setBackground(Color.cyan);
            l.setOpaque(true);
            ls.add(l);
            p.add(l);

        }
        add(p);
        add(gen);
        add(step);
        setSize(500,500);
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
