package com.company;

public class GameFunction {
    //Variabler
    GameBoard gameBoard;

    //Konstruktorer
    public GameFunction() {
    }

    public GameFunction(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    //Metoder
    public boolean gettingShot (int row, int column){
        if (gameBoard.getPlayerBoard()[row][column] != 9) {
            gameBoard.getPlayerBoard()[row][column] = -1;
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
