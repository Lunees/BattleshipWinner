package com.company;

public class GameFunction {
    //Variabler
    int playerAmountOfShips = 10;
    GameBoard gameBoard;
    Ship deadShip = new Ship("Dead", -1, 1);

    //Konstruktorer
    public GameFunction() {
    }

    public GameFunction(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    // Metoder
    // Metod när spelare blir beskjuten.
    public boolean gettingShot (int row, int column){
        Ship currentShot = gameBoard.getPlayerBoard()[row][column]; // Hämtar index som har blivit beskjutet.

        if (currentShot != null && !currentShot.equals(deadShip)) { // Om index inte är tomt och om det är ett dött skepp.
            currentShot.setLength(currentShot.getLength()-1); // Om det är en träff så tar den minus på length.
            isShipAlive(currentShot); // Metod kollar om skeppet lever.
            gameBoard.changeIndex(row, column, deadShip); //Sätter index till dött.

            return true;
        }
        else
            return false;
    }
    public String shooting (){

        return "A 3";
    }
    // Metod som kollar om skeppet är sänkt eller inte.
    // Om skeppets length är mindre än eller = 0 så är skeppet sänkt då minskas totalen skepp.
    public void isShipAlive(Ship ship){
    if(ship.getLength() <= 0){
      playerAmountOfShips --;
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
