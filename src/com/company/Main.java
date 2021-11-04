package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        /*Player player; //Om det är spelare 1 eller 2 bestäms senare, detta tack vare att Player är en abstrakt klass

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
       */

        //Skapar objekt
        GameBoard playerBoard = new GameBoard(10, 10, 9);
        GameBoard enemyBoard = new GameBoard(10,10, 0);
        //enemyBoard.createGameBoard(0);  // 0 = fogblock som gör det enklare att deklarera träff eller miss
        //playerBoard.createGameBoard(9); // 9 = vattenblock
        Placement placement = new Placement();
        placement.setGameBoard(playerBoard);

        GameFunction gameFunction = new GameFunction(playerBoard);

        Ship[] shipArray = new Ship[10];

        //lägger till antal skepp till de olika skeppstyperna
        int i = 0;
        int submarine = 4;
        int cruiser = 3;
        int battleship = 2;
        int carrier = 1;


        // initierar submarine i arrayen
        for (; i < submarine; i++){
            shipArray[i] = new Ship("Submarine",2,2,true);
        }
        // lägger till cruisern till den tidigare submarinen
        for (; i < (submarine + cruiser); i++){
            shipArray[i] = new Ship("Cruiser",3,3,true);
        }
        // lägger till battleship till de tidigare cruiserna och submarinesen
        for (; i < (submarine + cruiser + battleship); i++){
            shipArray[i] = new Ship("Battleship",4,4,true);
        }
        // lägger till carriern på slutet
        for (; i < (submarine + cruiser + battleship + carrier); i++){
            shipArray[i] = new Ship("Carrier",5, 5, true);
        }

        //Placerar ut skepp.
        placement.placeHorizontal(shipArray[0],0,0);
        placement.placeHorizontal(shipArray[1],7,0);
        placement.placeVertical(shipArray[2],5,5);
        placement.placeVertical(shipArray[3],2,9);
        placement.placeHorizontal(shipArray[4],2,5);
        placement.placeVertical(shipArray[5],5,9);
        placement.placeVertical(shipArray[6],0,3);
        placement.placeHorizontal(shipArray[7],0,6);
        placement.placeVertical(shipArray[8],2,0);
        placement.placeHorizontal(shipArray[9],9,5);

        //Skriver ut spelbrädet

        playerBoard.showGameBoard();
        while (true) {
            System.out.println(gameFunction.gettingShot(scanner.nextInt(), scanner.nextInt()));

            playerBoard = gameFunction.getGameBoard();
            playerBoard.showGameBoard();
        }

    }
}