package com.arhostcode.world;

public class Board {

    public static final int BORDER_SIZE = 1;

    public static final int BOARD_SIZE = 18;
    private Cell[][] field = new Cell[BOARD_SIZE + BORDER_SIZE * 2][BOARD_SIZE + BOARD_SIZE * 2];

    public void initialize() {
        for (int i = 0; i < BOARD_SIZE + 2; i++) {
            for (int j = 0; j < BOARD_SIZE + 2; j++) {
                field[i][j] = new Cell();
                if (isBorder(i,j)) {
                    field[i][j].setBlockCell();
                } else {
                    field[i][j].setVoidCell();
                }
            }
        }
    }

    private boolean isBorder(int x, int y){
        return x == 0 | y == 0 | x == BOARD_SIZE + 1 | y == BOARD_SIZE + 1;
    }

    public double getDistanceToNearestBlock(Position position, Direction direction){
        double distance = 0.5;
        while (isPositionOnBoard(position)){
            position.add(direction);
            if(isBlock(position))
                return 1 / distance;
            distance += 1;

        }
        return 1 / distance;
    }

    private boolean isPositionOnBoard(Position position){
        return position.getX() >= 0 & position.getX() <= BOARD_SIZE + BORDER_SIZE & position.getY() >= 0 & position.getY() <= BOARD_SIZE + BORDER_SIZE;
    }

    public boolean isBlock(Position position){
        return field[position.getY()][position.getX()].isBlockCell();
    }

    public boolean isBlock(int x, int y){
        return field[y][x].isBlockCell();
    }

    public void setBlock(int x, int y){
        field[y][x].setBlockCell();
    }
}
