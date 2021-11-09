package com.company;

import java.util.Random;

public class GameFunction {
    Random random = new Random();
    //Variabler
    GameBoard enemyBoard;
    int playerAmountOfShips = 10;
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

    public String shooting() {
        int randomShotX;
        int randomShotY;
        do {
            randomShotX = (int) (Math.random()*10);
            randomShotY = (int) (Math.random()*10);
        }
        while (enemyBoard.getPlayerBoard()[randomShotY][randomShotX] != null);

        //System.out.println(randomShotX + " " + randomShotY); //för att debugga
        return parse.intToString(randomShotY) + randomShotX;
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
        //Get n set

        public GameBoard getGameBoard () {
            return gameBoard;
        }

        public void setGameBoard (GameBoard gameBoard){
            this.gameBoard = gameBoard;
        }
    }
