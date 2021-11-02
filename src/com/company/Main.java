package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Player player; //Om det är spelare 1 eller 2 bestäms senare, detta tack vare att Player är en abstrakt klass

        int port = 8900;

        //Spelaren får bestämma om den ska vara spelare 1 eller 2
        System.out.println("Spelare 1 eller 2?");
        int playerChoice = scanner.nextInt();

        //Sätter upp spelaren
        while(true){
             if (playerChoice == 1){
                 player = new Player1(); //Sätter player som player 1
                 player.start(port);
                 player.send("hej"); //Player 1 startar med att skicka meddelande
                 break;
             }
             else if (playerChoice == 2){
                 player = new Player2(); //Sätter player som player 2
                 player.start(port);
                 break;
             }
        }
        //System.out.println("Klar, går vidare till while");

        //spelarna kommunicerar (Test)
        String message = "";
        while (!message.equals("qq")){
            scanner = new Scanner(System.in);
            player.receive();
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
        // 1x Carrier, 2x battleship, 3x cruiser, 4x submarine
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

        Ship[] shipArray = new Ship[10];

        int i = 0;
        for (; i < 4; i++){
            shipArray[i] = new Ship("Submarine",2,2,true);
            System.out.println("Skriv 4 ggr" + shipArray[i]);
        }
        for (; i < (4+3); i++){
            shipArray[i] = new Ship("Cruiser",3,3,true);
            System.out.println("Skriv 3 ggr" + shipArray[i]);
        }
        for (; i < (4+3+2); i++){
            shipArray[i] = new Ship("Battleship",4,4,true);
            System.out.println("Skriv 2 ggr" + shipArray[i]);
        }
        for (; i < (4+3+2+1); i++){
            shipArray[i] = new Ship("Carrier",5, 5, true);
            System.out.println("Skriv 1 gång" + shipArray[i]);
        }
    }
}