package com.company;

public class Ship {
    private String shipName;
    private int typeOfShip;
    private int length;
    private boolean alive = true;



    public Ship(String shipName, int typeOfShip, int length, boolean alive) {
        this.shipName = shipName;
        this.typeOfShip = typeOfShip;
        this.length = length;
        this.alive = alive;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getTypeOfShip() {
        return typeOfShip;
    }

    public void setTypeOfShip(int typeOfShip) {
        this.typeOfShip = typeOfShip;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
