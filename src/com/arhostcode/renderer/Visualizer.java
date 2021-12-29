package com.arhostcode.renderer;

import com.arhostcode.world.Board;
import com.arhostcode.world.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Visualizer extends JFrame {
    public ArrayList<JLabel> field = new ArrayList<>();
    public JLabel gen;
    public JLabel step;

    public Visualizer() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gen = new JLabel("0");
        step = new JLabel("0");

        JPanel p = new JPanel();

        p.setLocation(10, 10);

        p.setMaximumSize(new Dimension(250 * (Board.BOARD_SIZE + 2) / 10, 330 * (Board.BOARD_SIZE + 2) / 10));
        p.setSize(new Dimension(250 * (Board.BOARD_SIZE + 2) / 10, 330 * (Board.BOARD_SIZE + 2) / 10));
        p.setPreferredSize(new Dimension(250 * (Board.BOARD_SIZE + 2) / 10, 330 * (Board.BOARD_SIZE + 2) / 10));


        for (int i = 0; i < (Board.BOARD_SIZE + 2) * (Board.BOARD_SIZE + 2); i++) {
            JLabel cell = new JLabel();
            cell.setSize(20, 20);
            cell.setPreferredSize(new Dimension(20, 20));
            cell.setMaximumSize(new Dimension(20, 20));
            cell.setBackground(Color.WHITE);
            cell.setOpaque(true);
            field.add(cell);
            p.add(cell);

        }
        add(p);
        add(gen);
        add(step);
        setSize(1000, 1000);
        setLayout(new FlowLayout());

        show();
    }

    public void paintBoard(Board board) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(board.isBlock(i, j)){
                    field.get(i * (Board.BOARD_SIZE + 2) + j).setBackground(Color.BLACK);
                }else{
                    field.get(i * (Board.BOARD_SIZE + 2) + j).setBackground(Color.WHITE);
                }
            }
        }

    }

    public void paint(int x, int y) {
        field.get(x * (Board.BOARD_SIZE + 2) + y).setBackground(Color.GREEN);
    }

    public void paintEnemy(Position position) {
        field.get(position.getX() * (Board.BOARD_SIZE + 2) + position.getY()).setBackground(Color.RED);
    }

}
