package com.company;

public class Placement {

    private GameBoard gameBoard;



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






    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}
