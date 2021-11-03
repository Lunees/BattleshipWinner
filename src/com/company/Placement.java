package com.company;

public class Placement {

    //Variabler
    private GameBoard gameBoard;

    //Metoder
    //Placera vertikalt eller horisontellt
    public void placeHorizontal(Ship ship){
        int startIndexX = (int)(Math.random()*(10 - ship.getLength())); //slänger ut skeppet vertikalt, inte för nära kanten
        int startIndexY = (int)(Math.random()*10); //slänger ut de random horisontalt

        //Placerar ut skepp
        for(int i = 0; i < ship.getLength(); i++){
            gameBoard.getPlayerBoard()[startIndexY][startIndexX + i] = ship;
        }
    }

    public void placeVertical(Ship ship){
        int startIndexY = (int)(Math.random() * (10 - ship.getLength())); //slänger ut skeppet horisontalt, inte för nära kanten
        int startIndexX = (int)(Math.random() * 10); //slänger ut de random vertikalt

        //Placerar ut skepp
        for(int i = 0; i < ship.getLength(); i++){
            gameBoard.getPlayerBoard()[startIndexY + i][startIndexX] = ship;
        }
    }

    //Placera manuellt
    public void placeHorizontal(Ship ship, int row, int column){
        int startIndexX = column; //slänger ut skeppet vertikalt, inte för nära kanten
        int startIndexY = row; //slänger ut de random horisontalt

        //Placerar ut skepp
        for(int i = 0; i < ship.getLength(); i++){
            gameBoard.getPlayerBoard()[startIndexY][startIndexX + i] = ship;
        }
    }

    public void placeVertical(Ship ship, int row, int column){
        int startIndexY = row; //slänger ut skeppet horisontalt
        int startIndexX = column; //slänger ut skeppet vertikalt

        //Placerar ut skepp
        for(int i = 0; i < ship.getLength(); i++){
            gameBoard.getPlayerBoard()[startIndexY + i][startIndexX] = ship;
        }
    }

    //Get n set
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}
