package com.company;

public class GameBoard {
    //Variabler
    private int rows = 10;
    private int columns = 10;
    private int defaultBlock;
    private Parse parse = new Parse();

    //private int[][] playerBoard = new int[rows][columns];
    private Ship[][] playerBoard = new Ship[rows][columns];

    //Constructor
    public GameBoard(int rows, int columns, int defaultBlock) {
        this.rows = rows;
        this.columns = columns;
        this.defaultBlock = defaultBlock;
    }

    public GameBoard() {
    }

    //Metoder
    //För att skapa ett tomt spelbräde
   /* public void createGameBoard(int num){
        for(int i = 0; i < rows; i++ ){
            for (int j = 0; j < columns; j++){
                playerBoard[i][j] = null;
            }
        }
        showGameBoard();
    }*/

    //Visar spelbrädet
    public void showGameBoard(){
        for (int row = 0; row < rows; row++){

            for(int column = 0; column < columns; column++){
                if (playerBoard[row][column] == null)
                    System.out.print(defaultBlock + " ");
                else
                    System.out.print(playerBoard[row][column].getTypeOfShip() + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------"); // delar upp gameboardsen
    }

    //Uppdaterar board ifall det är en träff
    public void updateBoard (char rowChar, char columnChar, char hitOrMiss){
        //Gör om char till rätt slags data
        int row = parse.letterToIndex(rowChar);
        int column = parse.numberToIndex(columnChar);

        if (hitOrMiss == 'h' || hitOrMiss == 's') {
            //Gör om till ett dött skepp
            changeIndex(row, column, new Ship("deadShip", -1, 1)); //Sätter index till dött.
        } else if(hitOrMiss == 'm'){
            //Gör om till missat skepp
            changeIndex(row, column, new Ship("missed", 8, 1));
        }
    }

    public void changeIndex(int row, int column, Ship ship){
        playerBoard[row][column] = ship;
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

    public int getDefaultBlock() {
        return defaultBlock;
    }

    public void setDefaultBlock(int defaultBlock) {
        this.defaultBlock = defaultBlock;
    }

    public Ship[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(Ship[][] playerBoard) {
        this.playerBoard = playerBoard;
    }
}
