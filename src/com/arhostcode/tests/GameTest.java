package com.arhostcode.tests;

import com.arhostcode.ai_core.Brain;
import com.arhostcode.world.Board;
import com.arhostcode.world.Cell;
import com.arhostcode.world.Direction;
import com.arhostcode.world.Position;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void isBlockWithoutPositionBorderIsBlock() {
        Board board = new Board();
        board.initialize();

        boolean excepted = true;
        boolean actual = board.isBlock(0, 0);

        Assert.assertEquals("Block on (0,0) is not Block", excepted, actual);
    }

    @Test
    public void isBlockWithPositionBorderIsBlock() {
        Board board = new Board();
        board.initialize();

        boolean excepted = true;
        Position position = new Position(0, 0);
        boolean actual = board.isBlock(position);

        Assert.assertEquals("Block on (0,0) is not Block", excepted, actual);
    }

    @Test
    public void getDistanceToNearestBlockExceptedEqualsActual() {
        Board board = new Board();
        board.initialize();
        board.setBlock(5, 5);

        double excepted = 0.25;
        Position position = new Position(5, 1);
        double actual = board.getDistanceToNearestBlock(position, Direction.DOWN);

        Assert.assertEquals("Distance to nearest block calculate wrong", excepted, actual, 0.01);
    }

    @Test
    public void addPositionDirectionUPPositionYDecrease() {
        Position position = new Position(1, 1);

        int exceptedX = 1;
        int exceptedY = 0;

        position.add(Direction.UP);
        int actualX = position.getX();
        int actualY = position.getY();

        Assert.assertEquals("Direction.UP change X value, but it wrong", exceptedX, actualX, 0.01);
        Assert.assertEquals("Direction.UP wrong change Y value", exceptedY, actualY, 0.01);
    }

    @Test
    public void addPositionDirectionDOWNPositionYAdding() {
        Position position = new Position(1, 1);

        int exceptedX = 1;
        int exceptedY = 2;

        position.add(Direction.DOWN);
        int actualX = position.getX();
        int actualY = position.getY();

        Assert.assertEquals("Direction.DOWN change X value, but it wrong", exceptedX, actualX, 0.01);
        Assert.assertEquals("Direction.DOWN wrong change Y value", exceptedY, actualY, 0.01);
    }

    @Test
    public void addPositionDirectionRIGHTPositionYAdding() {
        Position position = new Position(1, 1);

        int exceptedX = 2;
        int exceptedY = 1;

        position.add(Direction.RIGHT);
        int actualX = position.getX();
        int actualY = position.getY();

        Assert.assertEquals("Direction.RIGHT wrong change X value", exceptedX, actualX, 0.01);
        Assert.assertEquals("Direction.RIGHT change Y value, but it wrong", exceptedY, actualY, 0.01);
    }

    @Test
    public void addPositionDirectionLEFTPositionYDecrease() {
        Position position = new Position(1, 1);

        int exceptedX = 0;
        int exceptedY = 1;

        position.add(Direction.LEFT);
        int actualX = position.getX();
        int actualY = position.getY();

        Assert.assertEquals("Direction.LEFT wrong change X value", exceptedX, actualX, 0.01);
        Assert.assertEquals("Direction.LEFT change Y value, but it wrong", exceptedY, actualY, 0.01);
    }

    @Test
    public void isBlockCellThisCellWillBeBlock() {
        Cell cell = new Cell();
        cell.setBlockCell();

        boolean excepted = true;
        boolean actual = cell.isBlockCell();

        Assert.assertEquals("Block cell is not a block", excepted, actual);
    }

    @Test
    public void setBlockCellThisCellWillNotBeBlock() {
        Cell cell = new Cell();
        cell.setVoidCell();

        boolean excepted = false;
        boolean actual = cell.isBlockCell();

        Assert.assertEquals("Block cell is a block, but it is a void", excepted, actual);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getWeightIndexOfTheBoundsThrowException() {
        Brain brain = new Brain();
        brain.randomizeWeights();

        brain.setWeight(1000, 1);
    }

}

