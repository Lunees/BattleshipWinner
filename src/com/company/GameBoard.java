package com.company;
import static java.lang.Math.*;
public class GameBoard {
    //Variabler
    private int rows = 10;
    private int columns = 10;

    private int[][] playerBoard = new int[rows][columns];

    //Constructor
    public GameBoard(int rows, int columns, int[][] playerBoard) {
        this.rows = rows;
        this.columns = columns;
        this.playerBoard = playerBoard;
    }

    //Metoder
    //För att skapa ett tomt spelbräde
    public void createGameBoard(int num){
        for(int i = 0; i < rows; i++ ){
            for (int j = 0; j < columns; j++){
                playerBoard[i][j] = num;
            }
        }
        showGameBoard(); 
    }

    //Visar spelbrädet
    public void showGameBoard(){
        for (int i = 0; i < 10; i++){

            for(int j = 0; j < columns; j++){
                System.out.printf(playerBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------"); // delar upp gameboardsen
    }

    //Get n Set
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
