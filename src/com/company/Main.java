package com.company;

public class Main {

    public static void main(String[] args) {
        //Skapar objekt
        GameBoard gameBoard = new GameBoard(10, 10, new int[10][10]);
        gameBoard.createGameBoard();
        Placement placement = new Placement();
        placement.setGameBoard(gameBoard);

        //Skapar skeppobjekt
        // 5 = Carrier 4 = Battleship 3 = Cruiser 2 = Submarine
        Ship ship1 = new Ship( "Carrier",5, 5, true);
        Ship ship2 = new Ship("Battleship",4,4,true);
        Ship ship3 = new Ship("Cruiser",3,3,true);
        Ship ship4 = new Ship("Submarine",2,2,true);

        //Placerar skepp
        placement.placeHorizontal(ship1);
        placement.placeVertical(ship2);
        placement.placeVertical(ship3);
        placement.placeVertical(ship4);
        System.out.println(" ");

        //Skriver ut spelbrädet
        gameBoard.showGameBoard();
    }
}