package com.company.gameFunction;
import com.company.*;

public class Besieged {
    //Variabler
    private GameBoard enemyBoard,
            gameBoard;
    private int playerAmountOfShips = 10;
    private boolean alive = true;
    private Ship deadShip = new Ship("Dead", -1, 1);
    private Parse parse = new Parse();


    //Konstruktorer
    public Besieged() {
    }

    public Besieged(GameBoard gameBoard, GameBoard enemyBoard) {
        this.gameBoard = gameBoard;
        this.enemyBoard = enemyBoard;
    }

    // Metoder
    // Metod när spelare blir beskjuten.
    public char gettingShot(char rowChar, char columnChar) {
        int row = parse.letterToIndex(rowChar);
        int column = parse.numberToIndex(columnChar);
        char hitOrMiss = 'm';
        Ship currentShot = gameBoard.getPlayerBoard()[row][column]; // Hämtar index som har blivit beskjutet.

        if (currentShot != null && !currentShot.equals(deadShip)) { // Om index inte är tomt och om det är ett dött skepp.
            currentShot.setLength(currentShot.getLength() - 1); // Om det är en träff så tar den minus på length.
            hitOrMiss = isShipAlive(currentShot); // Metod kollar om skeppet lever.
            gameBoard.changeIndex(row, column, deadShip); //Sätter index till dött.
        }
        return hitOrMiss;
    }

    // Metod som kollar om skeppet är sänkt eller inte.
    // Om skeppets length är mindre än eller = 0 så är skeppet sänkt då minskas totalen skepp.
    public char isShipAlive (Ship ship){
        if (ship.getLength() <= 0) {
            playerAmountOfShips--;
            areWeDeadYet();
            return 's';
        }
        return 'h';
    }

    //Kollar om spelaren lever
    public void areWeDeadYet(){
        if (playerAmountOfShips <= 0)
            alive = false;
    }

    //Get n set
    public int getPlayerAmountOfShips() {
        return playerAmountOfShips;
    }

    public void setPlayerAmountOfShips(int playerAmountOfShips) {
        this.playerAmountOfShips = playerAmountOfShips;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }

    public void setEnemyBoard(GameBoard enemyBoard) {
        this.enemyBoard = enemyBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Ship getDeadShip() {
        return deadShip;
    }

    public void setDeadShip(Ship deadShip) {
        this.deadShip = deadShip;
    }

    public Parse getParse() {
        return parse;
    }

    public void setParse(Parse parse) {
        this.parse = parse;
    }
}
