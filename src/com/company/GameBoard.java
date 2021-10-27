package com.company;
import static java.lang.Math.*;
public class GameBoard {

    private int rows = 10;
    private int columns = 10;

    private int[][] playerBoard = new int[rows][columns];

    public GameBoard(int rows, int columns, int[][] playerBoard) {
        this.rows = rows;
        this.columns = columns;
        this.playerBoard = playerBoard;
    }

    public void createGameBoard(){
        for(int i = 0; i < rows; i++ ){
            for (int j = 0; j < columns; j++){
                playerBoard[i][j] = 0;
            }
        }
        showGameBoard(); 
    }

    public void showGameBoard(){
        for (int i = 0; i < 10; i++){

            for(int j = 0; j < columns; j++){
                System.out.printf(playerBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
public void placeHorizontal(Ship ship){
    int startIndex = (int)(Math.random()*5);

        for(int i = 0; i < ship.getLength(); i++){
          playerBoard[0][startIndex+i] = ship.getTypeOfShip();
            System.out.println(i);
        }
    }





    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(int[][] playerBoard) {
        this.playerBoard = playerBoard;
    }


}
