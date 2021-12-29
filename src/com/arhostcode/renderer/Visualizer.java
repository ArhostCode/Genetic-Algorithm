package com.arhostcode.renderer;

import com.arhostcode.world.Board;
import com.arhostcode.world.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Visualizer extends JFrame {

    public ArrayList<JLabel> field = new ArrayList<>();

    public Visualizer() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        setSize(600, 600);
        setLayout(new FlowLayout());

        show();
    }

    public void paintBoard(Board board) {
        for (int i = 0; i < Board.BOARD_SIZE + Board.BORDER_SIZE * 2; i++) {
            for (int j = 0; j < Board.BOARD_SIZE + Board.BORDER_SIZE * 2; j++) {
                if(board.isBlock(j, i)){
                    field.get(i * (Board.BOARD_SIZE + Board.BORDER_SIZE * 2) + j).setBackground(Color.BLACK);
                }else{
                    field.get(i * (Board.BOARD_SIZE + Board.BORDER_SIZE * 2) + j).setBackground(Color.WHITE);
                }
            }
        }

    }

    public void paintEnemy(Position position) {
        field.get(position.getY() * (Board.BOARD_SIZE + Board.BORDER_SIZE * 2) + position.getX()).setBackground(Color.RED);
    }

}
