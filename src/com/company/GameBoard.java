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
     // Y-Led upp , X-Led -

public void placeHorizontal(Ship ship){
    int startIndexX = (int)(Math.random()*5); //slänger ut skeppet vertikalt
    int startIndexY = (int)(Math.random()*10); // slänger ut de random horizontalt

        for(int i = 0; i < ship.getLength(); i++){
          playerBoard[startIndexY][startIndexX+i] = ship.getTypeOfShip();
            System.out.println(i);
        }
    }
public void placeVertical(Ship ship){
    int startIndexY = (int)(Math.random()*5); //slänger ut skeppet
    int startIndexX = (int)(Math.random()*10); // slänger ut de random horizontalt

        for(int i = 0; i < ship.getLength(); i++){
            playerBoard[startIndexY+i][startIndexX] = ship.getTypeOfShip();
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
