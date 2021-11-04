package com.company;

public class GameFunction {
    //Variabler
    GameBoard gameBoard;
    Ship deadShip = new Ship("Dead", -1, 1, false);

    //Konstruktorer
    public GameFunction() {
    }

    public GameFunction(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    //Metoder
    public boolean gettingShot (int row, int column){
        Ship currentShot = gameBoard.getPlayerBoard()[row][column];
        if (currentShot != null) {
            currentShot.setLength(currentShot.getLength()-1);
            gameBoard.changeIndex(row, column, deadShip);

            return true;
        }
        else
            return false;
    }

    public String shooting (){

        return "A 3";
    }

    //Get n set

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}
