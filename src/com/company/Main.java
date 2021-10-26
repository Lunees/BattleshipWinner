package com.company;

public class Main {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(10, 10, new int[10][10]);
        gameBoard.createGameBoard();
        // A = Hangar B = Slagskepp C = Kryssare D = Ubåt
        Ship ship = new Ship( "Carrier",'A', 5, true); // Skapar skeppobjekt
        Ship ship2 = new Ship("Battleship",'B',4,true);
        Ship ship3 = new Ship("Cruiser",'C',3,true);
        Ship ship4 = new Ship("Submarine",'D',2,true);

    }
}