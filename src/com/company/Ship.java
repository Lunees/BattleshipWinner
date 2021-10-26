package com.company;

public class Ship {
    private char typeOfShip;
    private int length;
    private boolean alive = true;


    public Ship(char typeOfShip, int length, boolean alive) {
        this.typeOfShip = typeOfShip;
        this.length = length;
        this.alive = alive;
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
