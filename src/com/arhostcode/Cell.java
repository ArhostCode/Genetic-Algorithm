package com.arhostcode;

public class Cell {

    enum CellFill {
        BLOCK,
        VOID
    }

    private CellFill cellFill;

    public boolean isBlockCell(){
        return cellFill == CellFill.BLOCK;
    }

    public void setVoidCell() {
        this.cellFill = CellFill.VOID;
    }

    public void setBlockCell() {
        this.cellFill = CellFill.BLOCK;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "cellFill=" + cellFill +
                '}';
    }
}


