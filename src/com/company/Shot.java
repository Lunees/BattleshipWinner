package com.company;

public class Shot {
    //Variabler
    private int indexRow,
                indexColumn;
    private char hitOrMiss;

    //Konstruktorer
    public Shot(int indexRow, int indexColumn, char hitOrMiss) {
        this.indexRow = indexRow;
        this.indexColumn = indexColumn;
        this.hitOrMiss = hitOrMiss;
    }

    public Shot(int indexRow, int indexColumn) {
        this.indexRow = indexRow;
        this.indexColumn = indexColumn;
    }

    public boolean isVertical(Shot shot2){
        return indexColumn == shot2.getIndexColumn();
    }

    //Get n set
    public int getIndexRow() {
        return indexRow;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

    public int getIndexColumn() {
        return indexColumn;
    }

    public void setIndexColumn(int indexColumn) {
        this.indexColumn = indexColumn;
    }

    public char getHitOrMiss() {
        return hitOrMiss;
    }

    public void setHitOrMiss(char hitOrMiss) {
        this.hitOrMiss = hitOrMiss;
    }


}
