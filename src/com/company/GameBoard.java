package com.company;

public class GameBoard {

    private int height;
    private int width;

    private int[][] playerBoard = new int[10][10];

    public GameBoard(int height, int width, int[][] playerBoard) {
        this.height = height;
        this.width = width;
        this.playerBoard = playerBoard;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(int[][] playerBoard) {
        this.playerBoard = playerBoard;
    }
}
