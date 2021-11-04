package com.company;

public class Ship {
    //Variabler
    private String shipName;
    private int typeOfShip;
    private int length;

    //Konstructor
    public Ship(String shipName, int typeOfShip, int length) {
        this.shipName = shipName;
        this.typeOfShip = typeOfShip;
        this.length = length;
    }

    //Get n set
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

    }

