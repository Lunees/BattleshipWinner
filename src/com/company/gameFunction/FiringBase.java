package com.company.gameFunction;

import com.company.Ship;
import com.company.Shot;

import java.util.ArrayList;
import java.util.List;

public class FiringBase extends Base{
    protected Shot prevShot = new Shot(0, 0);
    protected Ship noShip = new Ship("no ship", 6, 1);
    protected List<Shot> shotList = new ArrayList<>();

    //Generell skjutningsfunktion
    public String shooting(Shot shot) {
        //Sparar skottet
        prevShot = shot;

        //formatet [kolumn siffra][rad bokstav]
        return shot.getIndexColumn() + parse.intToString(shot.getIndexRow());
    }

    public Shot getPrevShot() {
        return prevShot;
    }

    public void setPrevShot(Shot prevShot) {
        this.prevShot = prevShot;
    }

    public Ship getNoShip() {
        return noShip;
    }

    public void setNoShip(Ship noShip) {
        this.noShip = noShip;
    }

    public List<Shot> getShotList() {
        return shotList;
    }

    public void setShotList(List<Shot> shotList) {
        this.shotList = shotList;
    }
}
