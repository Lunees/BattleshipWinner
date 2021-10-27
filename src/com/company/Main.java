package com.company;

public class Main {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(10, 10, new int[10][10]);
        gameBoard.createGameBoard();
        // 5 = Carrier 4 = Battleship 3 = Cruiser 2 = Submarine
        Ship ship = new Ship( "Carrier",5, 5, true); // Skapar skeppobjekt
        Ship ship2 = new Ship("Battleship",4,4,true);
        Ship ship3 = new Ship("Cruiser",3,3,true);
        Ship ship4 = new Ship("Submarine",2,2,true);
        gameBoard.placeHorizontal(ship);
        System.out.println(" ");
        gameBoard.showGameBoard();

    }
}