package com.company;

import java.util.ArrayList;
import java.util.List;

public class GameFunction {
    //Variabler
    private int playerAmountOfShips = 10;
    private boolean alive = true;
    private Shot prevShot = new Shot(0,0);
    private GameBoard enemyBoard,
            gameBoard;
    private Ship deadShip = new Ship("Dead", -1, 1);
    private Parse parse = new Parse();
    private List<Shot> shotList = new ArrayList<>();

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

    //Väljer ett random ställe att skjuta på
    //Att lägga till: Så att datorn baserat på om det är hit eller miss samt tidigare skott anpassar skottet
    public String shootingRandom() {
        int randomShotX;
        int randomShotY;

        do {
            randomShotX = (int) (Math.random()*10);
            randomShotY = (int) (Math.random()*10);
        }
        while (enemyBoard.getPlayerBoard()[randomShotY][randomShotX] != null); //Ser ifall spelaren sedan skjutit på indexet

        //System.out.println(randomShotX + " " + randomShotY); //för att debugga

        //formatet [column siffra][rad bokstav]
        return shooting(randomShotX,randomShotY);
        }

    //Generell skjutningsfunktion
    public String shooting(int shotX, int shotY) {
        //Sparar skottet
        prevShot = new Shot(shotY,shotX);

        //formatet [column siffra][rad bokstav]
        return shotX + parse.intToString(shotY);
    }

    //Huvudsatsen i planeringen av skottet (pågående)
    public void planAttack(char hitOrMiss){
        if (shotList.isEmpty()){
            if (hitOrMiss == 'h'){
                addToShotList(hitOrMiss);
                System.out.println("Nu ska vi leta efter andra träffen");
                System.out.println(findSecondHit());
            }
            else{
                System.out.println("Nu ska vi skjuta random");
            }
        }
        else{
            addToShotList(hitOrMiss);

        }
    }

    //För att hitta det andra skottet (Kan kanske göra om till en kortare funktion)
    public String findSecondHit (){
        //Ser om det går att skjuta till höger
        if (prevShot.getIndexColumn() + 1 < gameBoard.getPlayerBoard().length){
            //Ser om det redan är skjutet till höger
            if (enemyBoard.getPlayerBoard()[prevShot.getIndexRow()][prevShot.getIndexColumn() + 1] == null){
                System.out.println("Skjuter till höger");
                return "höger";
            }
        }
        //Samma som ovan fast vänster
        if (prevShot.getIndexColumn() - 1 >= 0){
            if (enemyBoard.getPlayerBoard()[prevShot.getIndexRow()][prevShot.getIndexColumn() - 1] == null){
                System.out.println("Skjuter till vänster");
                return "vänster";
            }
        }
        //Samma som ovan fast neråt
        if (prevShot.getIndexRow() + 1 < gameBoard.getPlayerBoard().length ){
            if (enemyBoard.getPlayerBoard()[prevShot.getIndexRow() + 1][prevShot.getIndexColumn()] == null){
                System.out.println("Skjuter neråt");
                return "ner";
            }
        }
        //Om ingen tidigare returnerat så kvarstår bara uppåt
        System.out.println("Skjuter upp");
        return "upp";
    }

    //Lägger till skottet till en lista av skott, detta för att kunna planera skotten senare
    //Aktiveras när ett skepp hittas
    public void addToShotList(char hitOrMiss){
        prevShot.setHitOrMiss(hitOrMiss);
        shotList.add(prevShot);
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

    //Uppdaterar enemyboard ifall det är en träff
    public void updateEnemyBoard (char rowChar, char columnChar, char hitOrMiss){
        //Gör om char till rätt slags data
        int row = prevShot.getIndexRow();
        int column = prevShot.getIndexColumn();

        if (hitOrMiss == 'h' || hitOrMiss == 's') {
            //Gör om till ett dött skepp
            enemyBoard.changeIndex(row, column, deadShip); //Sätter index till dött.
        } else if(hitOrMiss == 'm'){
            //Gör om till missat skepp
            enemyBoard.changeIndex(row, column, new Ship("missed", 8, 1));
        }
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

    public Shot getPrevShot() {
        return prevShot;
    }

    public void setPrevShot(Shot prevShot) {
        this.prevShot = prevShot;
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

    public List<Shot> getShotList() {
        return shotList;
    }

    public void setShotList(List<Shot> shotList) {
        this.shotList = shotList;
    }
}
