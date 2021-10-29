package com.company;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Player player; //Om det är spelare 1 eller 2 bestäms senare

        //Spelaren får bestämma om den ska vara spelare 1 eller 2
        System.out.println("Spelare 1 eller 2?");
        int playerChoice = scanner.nextInt();

        //Startar kommunikation
        while(true){
            if (playerChoice == 1){
                player = new Player1();
                player.start(8900);
                player.send("hej");
                break;
            }
            else if (playerChoice == 2){
                player = new Player2();
                player.start(8900);
                break;
            }
        }
        System.out.println("Klar, går vidare till while");

        String message = "";
        while (!message.equals("qq")){
            scanner = new Scanner(System.in);
            System.out.println("Tog emot: " + player.receive());
            message = scanner.nextLine();
            player.send(message);
        }




        //Skapar objekt
        GameBoard gameBoard = new GameBoard(10, 10, new int[10][10]);
        gameBoard.createGameBoard();
        Placement placement = new Placement();
        placement.setGameBoard(gameBoard);

        GameFunction gameFunction = new GameFunction(gameBoard);

        //Skapar skeppobjekt
        // 5 = Carrier 4 = Battleship 3 = Cruiser 2 = Submarine
        Ship ship1 = new Ship("Carrier",5, 5, true);
        Ship ship2 = new Ship("Battleship",4,4,true);
        Ship ship3 = new Ship("Cruiser",3,3,true);
        Ship ship4 = new Ship("Submarine",2,2,true);

        //Placerar skepp
        placement.placeHorizontal(ship1);
        placement.placeVertical(ship2);
        placement.placeVertical(ship3,3,5);
        placement.placeVertical(ship4);
        System.out.println(" ");

        System.out.println("Is hit? : " + gameFunction.gettingShot(scanner.nextInt(), scanner.nextInt()));

        //Skriver ut spelbrädet
        gameBoard.showGameBoard();
    }
}