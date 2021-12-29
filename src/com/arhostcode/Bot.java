package com.arhostcode;

public class Bot {

    private int stepsCount = 0;
    private boolean alive = true;

    private Brain brain;

    private Position position;
    private Position previousPosition;
    private Position previousLastPosition;

    public Bot() {
        brain = new Brain();
        brain.randomizeWeights();
        position = new Position((int) (1 + Math.random() * Board.BOARD_SIZE), (int) (1 + Math.random() * Board.BOARD_SIZE));
        previousPosition = new Position(0, 0);
        previousLastPosition = new Position(0, 0);
    }

    public Bot(Brain brain) {
        this();
        this.brain = brain;
    }

    public void doStep(Board board) {
        double[] sensors = getSensorsValue(board);
        previousLastPosition = previousPosition.clone();
        previousPosition = position.clone();
        Direction actualDirection = brain.compute(sensors);
        translate(actualDirection);
        stepsCount++;
    }

    public void checkAlive(Board board) {
        if (board.isBlock(position))
            alive = false;

//        if (stepsCount > 80)
//            alive = false;

        if (position.equals(previousLastPosition)) {
            alive = false;
        }
    }

    private double[] getSensorsValue(Board board) {
        double[] sensorsValues = new double[4];
        sensorsValues[0] = board.getDistanceToNearestBlock(position.clone(), Direction.UP);
        sensorsValues[1] = board.getDistanceToNearestBlock(position.clone(), Direction.DOWN) * -1;
        sensorsValues[2] = board.getDistanceToNearestBlock(position.clone(), Direction.RIGHT);
        sensorsValues[3] = board.getDistanceToNearestBlock(position.clone(), Direction.LEFT) * -1;
        return sensorsValues;
    }

    public int getFitness() {
        return stepsCount;
    }

    private void translate(Direction direction) {
        position.add(direction);
    }

    public Brain getBrain() {
        return brain;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getPosition() {
        return position;
    }
}
