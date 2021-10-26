package com.company;

public class Ship {
    private String shipName;
    private char typeOfShip;
    private int length;
    private boolean alive = true;



    public Ship(String shipName, char typeOfShip, int length, boolean alive) {
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

    public char getTypeOfShip() {
        return typeOfShip;
    }

    public void setTypeOfShip(char typeOfShip) {
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
