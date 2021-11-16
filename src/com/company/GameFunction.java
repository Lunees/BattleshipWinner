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
        return shooting(new Shot(randomShotY,randomShotX));
        }

    //Generell skjutningsfunktion
    public String shooting(Shot shot) {
        //Sparar skottet
        prevShot = shot;

        //formatet [kolumn siffra][rad bokstav]
        return shot.getIndexColumn() + parse.intToString(shot.getIndexRow());
    }

    //Huvudsatsen i planeringen av skottet (pågående)
    public String planAttack(char hitOrMiss){
        //Kollar om spelaren inte har hittat ett skepp, representerat av en lista
        if (shotList.isEmpty()){
            if (hitOrMiss == 'h'){ //Om det är en träff har spelaren hittat ett skepp och ska hitta näsa skott
                addToShotList(hitOrMiss);
                System.out.println("Nu ska vi leta efter andra träffen");
                return shooting(findSecondHit());
            }
            else{ //Om det är en miss fortsätter spelaren att leta efter ett skepp
                System.out.println("Nu ska vi skjuta random");
                return shootingRandom();
            }
        }
        else{ //Om spelaren hittat ett skepp
            if (hitOrMiss == 's'){ //Om skeppet är sänkt så ska listan försvinna och spelaren leta efter ett nytt skepp
                shotList.clear();
                //createFrame();
                return shootingRandom();
            } else { //Om skeppet inte är sänkt ska spelaren fortsätta leta
                addToShotList(hitOrMiss);

                if (findLastIndexByHitOrMiss(shotList, 'h') == 0){ //Har ännu inte hittat den andra träffen
                    return shooting(findSecondHit());
                }else{ //Har hittat nästa del av skeppet
                    System.out.println("Nu ska vi sänka skeppet"); //Debug
                    return shooting(findNextHit());
                }
            }
        }
    }

    //Hitta när senaste träffen/missen var
    public int findLastIndexByHitOrMiss(List<Shot> list, char letter){
        for (int i = list.size() - 1; i >= 0; i--){
            if (list.get(i).getHitOrMiss() == letter)
                return i;
        }
        return -1;
    }

    public boolean canShotBeFired(Shot shot, int plusHorizontal, int plusVertical){
        //Ser om det planerade skottet är innanför spelplanen
        if (shot.getIndexColumn() + plusHorizontal < enemyBoard.getPlayerBoard().length &&
                shot.getIndexColumn() + plusHorizontal >= 0 &&
                shot.getIndexRow() + plusVertical < enemyBoard.getPlayerBoard().length &&
                shot.getIndexRow() + plusVertical >= 0){
            //Ser om det redan är skjutet till höger
            if (enemyBoard.getPlayerBoard()[shot.getIndexRow() + plusVertical][shot.getIndexColumn() + plusHorizontal] == null){
                return true;
            }
        }
        return false;
    }

    //För att hitta det andra skottet (Kan kanske göra om till en kortare funktion)
    public Shot findSecondHit (){
        //Ser om det går att skjuta till höger från första träffen
        if(canShotBeFired(shotList.get(0), 1, 0)) {
            System.out.println("Skjuter höger");
            return new Shot(shotList.get(0).getIndexRow(), shotList.get(0).getIndexColumn() + 1);
        }
        //Ser om det går att skjuta till vänster från första träffen
        else if (canShotBeFired(shotList.get(0), -1, 0)) {
            System.out.println("Skjuter till vänster");
            return new Shot(shotList.get(0).getIndexRow(), shotList.get(0).getIndexColumn() - 1);
        }
        //Ser om det går att skjuta neråt från första träffen
        else if (canShotBeFired(shotList.get(0), 0, 1)) {
            System.out.println("Skjuter neråt");
            return new Shot(shotList.get(0).getIndexRow() + 1, shotList.get(0).getIndexColumn());
        }
        //Om ingen tidigare returnerat så kvarstår bara uppåt
        System.out.println("Skjuter upp");
        return new Shot(shotList.get(0).getIndexRow() - 1, shotList.get(0).getIndexColumn());
        /*if (shotList.get(0).getIndexColumn() + 1 < gameBoard.getPlayerBoard().length){
            //Ser om det redan är skjutet till höger
            if (enemyBoard.getPlayerBoard()[shotList.get(0).getIndexRow()][shotList.get(0).getIndexColumn() + 1] == null){
                System.out.println("Skjuter till höger");

            }
        }
        //Samma som ovan fast vänster
        if (shotList.get(0).getIndexColumn() - 1 >= 0){
            if (enemyBoard.getPlayerBoard()[shotList.get(0).getIndexRow()][shotList.get(0).getIndexColumn() - 1] == null){
                System.out.println("Skjuter till vänster");
                return new Shot(shotList.get(0).getIndexRow(), shotList.get(0).getIndexColumn() - 1);
            }
        }
        //Samma som ovan fast neråt
        if (shotList.get(0).getIndexRow() + 1 < gameBoard.getPlayerBoard().length ){
            if (enemyBoard.getPlayerBoard()[shotList.get(0).getIndexRow() + 1][shotList.get(0).getIndexColumn()] == null){
                System.out.println("Skjuter neråt");
                return new Shot(shotList.get(0).getIndexRow() + 1, shotList.get(0).getIndexColumn());
            }
        }*/

    }

    //Hittar vart spelaren ska skjuta härnäst
    //BUGG: kan välja att skjuta på redan skjutna ifall senast träffad är precis intill kanten
    public Shot findNextHit(){
        Shot shot;

        //Varifrån ska spelaren utgå ifrån i planering
        if (prevShot.getHitOrMiss() == 'h'){
            shot = prevShot; //Skeppet kan ligga åt hållet spelaren skjutit åt
        }else {
            shot = shotList.get(0); //Skeppet ligger åt andra hållet spelaren skjutit mot
        }

        //Om kolumnen är samma på första och sista hits så är skeppet vertikalt
        if (shotList.get(0).getIndexColumn() ==
                shotList.get(findLastIndexByHitOrMiss(shotList, 'h')).getIndexColumn()){
            System.out.println("Vertical");
            //Ser om det går att skjuta neråt
            if (canShotBeFired(shot, 0, 1))
                return new Shot(shot.getIndexRow() + 1, shot.getIndexColumn());

            //Ifall det inte går att skjuta neråt ska spelaren skjuta uppåt
            System.out.println("Skjuter upp");
            int goUp = 1; //Ifall skottet utgår från kanten måste spelaren kolla vart det är tomt
            //Ser om det redan är skjutet där
            while (enemyBoard.getPlayerBoard()[shot.getIndexRow() - goUp][shot.getIndexColumn()] != null){
                    goUp++; //Om det är skjutet fortsätter spelaren kolla högre upp
            }
            return new Shot(shot.getIndexRow() - goUp, shot.getIndexColumn());

        }else { //Om kolumnen inte är samma på första och sista hits så är skeppet horisontalt
            System.out.println("Horizontal");
            //Ser om det går att skjuta till höger
            if (canShotBeFired(shot, 1, 0))
                return new Shot(shot.getIndexRow(), shot.getIndexColumn() + 1);

            //Ifall det inte går att skjuta till höger ska spelaren skjuta till vänster
            System.out.println("Skjuter till vänster");
            int goLeft = 1; //Ifall skottet utgår från kanten måste spelaren kolla vart det är tomt
            // Ser om det redan är skjutet där
            while (enemyBoard.getPlayerBoard()[shot.getIndexRow()][shot.getIndexColumn() - goLeft] != null){
                    goLeft++; //Om det är skjutet fortsätter spelaren kolla till vänster
            }
            return new Shot(shot.getIndexRow(), shot.getIndexColumn() - goLeft);
        }
    }

    //Lägger till skottet till en lista av skott, detta för att kunna planera skotten senare
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
