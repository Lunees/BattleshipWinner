package com.company.gameFunction;

import com.company.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Firing extends FiringBase {

    //Konstruktorv
    public Firing() {
    }

    public Firing(GameBoard enemyBoard, GameBoard gameBoard) {
        this.enemyBoard = enemyBoard;
        this.gameBoard = gameBoard;
    }

    //Metoder
    //Väljer ett random ställe att skjuta på
    public String shootingRandom() {
        int randomShotX, randomShotY;
        
        do {
            randomShotX = (int) (Math.random() * 10);
            randomShotY = (int) (Math.random() * 10);
        }
        while (enemyBoard.getPlayerBoard()[randomShotY][randomShotX] != null); //Ser ifall spelaren sedan skjutit på indexet

        //formatet [kolumn siffra][rad bokstav]
        return shooting(new Shot(randomShotY, randomShotX));
    }

    //Huvudsatsen i planeringen av skottet
    public String planAttack(char hitOrMiss) {
        //Kollar om spelaren inte har hittat ett skepp, representerat av en lista
        if (shotList.isEmpty()) {
            if (hitOrMiss == 'h') { //Om det är en träff har spelaren hittat ett skepp och ska hitta näsa skott
                addToShotList(hitOrMiss, prevShot);
                System.out.println("Nu ska vi leta efter andra träffen");
                Shot newShot = findSecondHit();
                return shooting(newShot);
            } else { //Om det är en miss fortsätter spelaren att leta efter ett skepp
                System.out.println("Nu ska vi skjuta random");
                removeSingles(); //Tar bort alla oskjutna rutor som är ensamma :(
                return shootingRandom();
            }
        } else { //Om spelaren hittat ett skepp
            addToShotList(hitOrMiss, prevShot);
            if (hitOrMiss == 's') { //Om skeppet är sänkt
                createFrame(); //finns inga fler skepp runt skeppet, då ska en ram skapas runt
                shotList.clear(); //Listan töms
                return shootingRandom(); //Spelaren ska fortsätta leta efter ett skepp till
            } else { //Om skeppet inte är sänkt ska spelaren fortsätta leta
                if (findLastIndexByHitOrMiss(shotList, 'h') == 0) { //Har ännu inte hittat den andra träffen
                    System.out.println("Vi fortsätter leta efter andra träffen");
                    return shooting(findSecondHit());
                } else { //Har hittat nästa del av skeppet
                    System.out.println("Nu ska vi sänka skeppet");
                    return shooting(findNextHit());
                }
            }
        }
    }

    //För att hitta det andra skottet
    public Shot findSecondHit() {
        //Ser om det går att skjuta till höger från första träffen
        if (canShotBeFired(shotList.get(0), 1, 0)) {
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
    }

    //Hittar vart spelaren ska skjuta härnäst
    public Shot findNextHit() {
        //Utifrån vilket skott ska spelaren utgå ifrån i planering
        Shot shot;
        if (prevShot.getHitOrMiss() == 'h') {
            shot = prevShot; //Skeppet kan ligga åt hållet spelaren skjutit åt
        } else {
            shot = shotList.get(0); //Skeppet ligger åt andra hållet spelaren skjutit mot
        }

        //Om kolumnen är samma på första och sista hits så är skeppet vertikalt
        if (shotList.get(0).isVertical(shotList.get(findLastIndexByHitOrMiss(shotList, 'h')))) {
            System.out.println("Vertical");
            //Ser om det går att skjuta neråt
            if (canShotBeFired(shot, 0, 1))
                return new Shot(shot.getIndexRow() + 1, shot.getIndexColumn());

            //Ifall det inte går att skjuta neråt ska spelaren skjuta uppåt
            System.out.println("Skjuter upp");
            int goUp = 1; //Ifall skottet utgår från kanten måste spelaren kolla vart det är tomt

            //Ser om det redan är skjutet där
            while (enemyBoard.getPlayerBoard()[shot.getIndexRow() - goUp][shot.getIndexColumn()] != null) {
                goUp++; //Om det är skjutet fortsätter spelaren kolla högre upp
            }
            return new Shot(shot.getIndexRow() - goUp, shot.getIndexColumn());

        } else { //Om kolumnen inte är samma på första och sista hits så är skeppet horisontalt
            System.out.println("Horizontal");
            //Ser om det går att skjuta till höger
            if (canShotBeFired(shot, 1, 0))
                return new Shot(shot.getIndexRow(), shot.getIndexColumn() + 1);

            //Ifall det inte går att skjuta till höger ska spelaren skjuta till vänster
            System.out.println("Skjuter till vänster");
            int goLeft = 1; //Ifall skottet utgår från kanten måste spelaren kolla vart det är tomt
            // Ser om det redan är skjutet där
            while (enemyBoard.getPlayerBoard()[shot.getIndexRow()][shot.getIndexColumn() - goLeft] != null) {
                goLeft++; //Om det är skjutet fortsätter spelaren kolla till vänster
            }
            return new Shot(shot.getIndexRow(), shot.getIndexColumn() - goLeft);
        }

    }

    //Skapar en ram runt ett redan funnet skepp så att spelaren inte behöver kolla där
    //Enligt reglerna kan inget skepp ligga precis bredvid ett annat skepp
    public void createFrame() {
        //Ser om första skottet (initierande träff) och sista skottet (när skeppet sänktes) är vertikalt
        if (shotList.get(0).isVertical(shotList.get(shotList.size() - 1))) {
            //Sorterar för att lätt kunna lägga till skott nedanför
            sortHitsByRow();
            //Lägger till extra skott nedanför skeppet i listan för att kunna fylla ut ramen
            addToShotList('h', new Shot(shotList.get(shotList.size() - 1).getIndexRow() + 1, shotList.get(shotList.size() - 1).getIndexColumn()));
            addToShotList('h', new Shot(shotList.get(shotList.size() - 1).getIndexRow() + 1, shotList.get(shotList.size() - 1).getIndexColumn()));

            //Kollar vart ramen kan placeras
            for (Shot s : shotList) {
                if (s.getHitOrMiss() == 'h' || s.getHitOrMiss() == 's') {
                    //Ser höger uppåt
                    if (canShotBeFired(s, 1, -1)) {
                        enemyBoard.changeIndex(s.getIndexRow() - 1, s.getIndexColumn() + 1, noShip);
                    }
                    //Ser uppåt
                    if (canShotBeFired(s, 0, -1)) {
                        enemyBoard.changeIndex(s.getIndexRow() - 1, s.getIndexColumn(), noShip);
                    }
                    //Ser vänster uppåt
                    if (canShotBeFired(s, -1, -1)) {
                        enemyBoard.changeIndex(s.getIndexRow() - 1, s.getIndexColumn() - 1, noShip);
                    }
                }
            }
        }
        //Annars horisontell
        else {
            //Sorterar för att lätt kunna lägga till skott bredvid
            sortHitsByColumn();
            //Lägger till extra skott till höger om skeppet i listan för att fylla ut ramen
            addToShotList('h', new Shot(shotList.get(shotList.size() - 1).getIndexRow(), shotList.get(shotList.size() - 1).getIndexColumn() + 1));
            addToShotList('h', new Shot(shotList.get(shotList.size() - 1).getIndexRow(), shotList.get(shotList.size() - 1).getIndexColumn() + 1));

            for (Shot s : shotList) {
                if (s.getHitOrMiss() == 'h' || s.getHitOrMiss() == 's') {
                    //Ser vänster nedanför
                    if (canShotBeFired(s, -1, 1)) {
                        enemyBoard.changeIndex(s.getIndexRow() + 1, s.getIndexColumn() - 1, noShip);
                    }
                    //Ser vänster
                    if (canShotBeFired(s, -1, 0)) {
                        enemyBoard.changeIndex(s.getIndexRow(), s.getIndexColumn() - 1, noShip);
                    }
                    //Ser vänster uppåt
                    if (canShotBeFired(s, -1, -1)) {
                        enemyBoard.changeIndex(s.getIndexRow() - 1, s.getIndexColumn() - 1, noShip);
                    }
                }
            }
        }
    }

    //Är det mindre än två tomma oskjutna platser vid varandra så finns inget skepp där
    public void removeSingles(){
        //går igenom varje ruta i griden
        for (int row = 0; row < enemyBoard.getRows(); row++){
            for (int column = 0; column <enemyBoard.getColumns(); column++){
                //Ser om det går att skjuta på platsen eller runt om
                if (enemyBoard.getPlayerBoard()[row][column] == null &&
                        !canShotBeFired(new Shot(row, column), 1, 0)&&
                        !canShotBeFired(new Shot(row, column), -1, 0)&&
                        !canShotBeFired(new Shot(row, column), 0, 1)&&
                        !canShotBeFired(new Shot(row, column), 0, -1))
                    enemyBoard.changeIndex(row, column, noShip);
            }
        }
    }

    //Hitta när senaste träffen/missen var
    public int findLastIndexByHitOrMiss(List<Shot> list, char letter) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getHitOrMiss() == letter)
                return i;
        }
        return -1;
    }

    //Sorterar alla shots som är träffar i ShotList utefter kolumn
    public void sortHitsByColumn() {
        Comparator<Shot> columnComparator = (s1, s2) -> s1.getIndexColumn() - s2.getIndexColumn();
        removeMisses();
        Collections.sort(shotList, columnComparator);
    }

    //Sorterar alla shots som är träffar i shotList utefter rad
    public void sortHitsByRow() {
        Comparator<Shot> rowComparator = (s1, s2) -> s1.getIndexRow() - s2.getIndexRow();
        removeMisses();
        Collections.sort(shotList, rowComparator);
    }

    //Tar bort alla missar i shotList
    public void removeMisses() {
        for (int i = shotList.size() - 1; i >= 0; i--) {
            if (shotList.get(i).getHitOrMiss() == 'm')
                shotList.remove(i);
        }
    }

    public boolean canShotBeFired(Shot shot, int plusRight, int plusDown) {
        //Ser om det planerade skottet är innanför spelplanen
        if (shot.getIndexColumn() + plusRight < enemyBoard.getPlayerBoard().length &&
                shot.getIndexColumn() + plusRight >= 0 &&
                shot.getIndexRow() + plusDown < enemyBoard.getPlayerBoard().length &&
                shot.getIndexRow() + plusDown >= 0) {
            //Ser om det redan är skjutet på den planerade skottet
            if (enemyBoard.getPlayerBoard()[shot.getIndexRow() + plusDown][shot.getIndexColumn() + plusRight] == null) {
                return true;
            }
        }
        return false;
    }

    public void addToShotList(char hitOrMiss, Shot shot){
        shot.setHitOrMiss(hitOrMiss);
        shotList.add(shot);
    }
}
