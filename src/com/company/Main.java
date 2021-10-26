package com.company;

public class Main {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(0, 0, new int[10][10]);
        // A = Hangar B = Slagskepp C = Kryssare D = Ubåt
        Ship ship = new Ship('A', 5, true); // Skapar skeppobjekt

    }
}