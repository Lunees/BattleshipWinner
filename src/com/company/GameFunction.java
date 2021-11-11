package com.company;

import java.util.Random;

public class GameFunction {
    Random random = new Random();
    //Variabler
    int playerAmountOfShips = 10;
    GameBoard enemyBoard;
    GameBoard gameBoard;
    Ship deadShip = new Ship("Dead", -1, 1);
    Parse parse = new Parse();

    //Konstruktorer
    public GameFunction() {
    }

    public GameFunction(GameBoard gameBoard, GameBoard enemyBoard) {
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

    //Skjuter random
    //Att lägga till: Så att datorn baserat på om det är hit eller miss samt tidigare skott anpassar skottet
    public String shooting() {
        int randomShotX;
        int randomShotY;

        do {
            randomShotX = (int) (Math.random()*10);
            randomShotY = (int) (Math.random()*10);
        }
        while (enemyBoard.getPlayerBoard()[randomShotY][randomShotX] != null);

        //System.out.println(randomShotX + " " + randomShotY); //för att debugga
        //formatet [column siffra][rad bokstav]
        return randomShotX + parse.intToString(randomShotY);
        }

    // Metod som kollar om skeppet är sänkt eller inte.
    // Om skeppets length är mindre än eller = 0 så är skeppet sänkt då minskas totalen skepp.
    public char isShipAlive (Ship ship){
        if (ship.getLength() <= 0) {
            playerAmountOfShips--;
            return 's';
        }
        return 'h';
    }

    //Uppdaterar enemyboard ifall det är en träff
    public void updateEnemyBoard (char rowChar, char columnChar, char letter){
        //Gör om char till rätt slags data
        int row = parse.letterToIndex(rowChar);
        int column = parse.numberToIndex(columnChar);

        if (letter == 'h' || letter == 's') {
            //Gör om till ett dött skepp
            enemyBoard.changeIndex(row, column, deadShip); //Sätter index till dött.
        } else if(letter == 'm'){
            //Gör om till missat skepp
            enemyBoard.changeIndex(row, column, new Ship("missed", 8, 1));
        }
    }

    //Get n set
    public GameBoard getGameBoard () {
        return gameBoard;
    }

    public void setGameBoard (GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }

    public void setEnemyBoard(GameBoard enemyBoard) {
        this.enemyBoard = enemyBoard;
    }

    public int getPlayerAmountOfShips() {
        return playerAmountOfShips;
    }

    public void setPlayerAmountOfShips(int playerAmountOfShips) {
        this.playerAmountOfShips = playerAmountOfShips;
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
