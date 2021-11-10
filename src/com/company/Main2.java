package com.company;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        //Skapar objekt
        GameBoard playerBoard = new GameBoard(10, 10, 9);
        GameBoard enemyBoard = new GameBoard(10,10, 0);
        Placement placement = new Placement();
        placement.setGameBoard(playerBoard);
        Parse parse = new Parse();

        GameFunction gameFunction = new GameFunction(playerBoard, enemyBoard);

        Scanner scanner = new Scanner(System.in);

        Player player; //Om det är spelare 1 eller 2 bestäms senare, detta tack vare att Player är en abstrakt klass

        int port = 8900;

        //Spelaren får bestämma om den ska vara spelare 1 eller 2
        System.out.println("Spelare 1 eller 2?");
        int playerChoice = scanner.nextInt();

        String playerAttack = "0a";
        //Sätter upp spelaren
        while(true){
            if (playerChoice == 1){
                player = new Player1(); //Sätter player som player 1
                player.start(port);
                playerAttack = gameFunction.shooting(); //Skapar spelarens skott

                //Spelarens respons
                player.send("i shot " + playerAttack); //Player 1 startar med att skicka meddelande
                break;
            }
            else if (playerChoice == 2){
                player = new Player2(); //Sätter player som player 2
                player.start(port);
                break;
            }
        }
        //spelarna kommunicerar (Test)
        /*
        String message = "";
        while (!message.equals("qq")){
            scanner = new Scanner(System.in);
            player.receive();
            message = scanner.nextLine();
            player.send(message);
        }
        */

        Ship[] shipArray = new Ship[10];

        //lägger till antal skepp till de olika skeppstyperna
        int i = 0;
        int submarine = 4;
        int cruiser = 3;
        int battleship = 2;
        int carrier = 1;

        //initierar submarine i arrayen
        for (; i < submarine; i++){
            shipArray[i] = new Ship("Submarine",2,2);
        }
        //lägger till cruisern till den tidigare submarinen
        for (; i < (submarine + cruiser); i++){
            shipArray[i] = new Ship("Cruiser",3,3);
        }
        //lägger till battleship till de tidigare cruiserna och submarinesen
        for (; i < (submarine + cruiser + battleship); i++){
            shipArray[i] = new Ship("Battleship",4,4);
        }
        //lägger till carriern på slutet
        for (; i < (submarine + cruiser + battleship + carrier); i++){
            shipArray[i] = new Ship("Carrier",5, 5);
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

        //Se ifall fienden blivit träffad, bli beskjuten och skjut
        while (true) {
            //Visar spelplanerna
            playerBoard.showGameBoard();
            enemyBoard.showGameBoard();

            //Tar emot fiendens attack
            System.out.println("Fiendens attack");
            String enemyAttack = player.receive(); //Format "h shot 6c"

            //Variabler för den data som behövs
            char    playerShotRow = playerAttack.charAt(1),     //Vilken rad som spelaren skjutit
                    playerShotColumn = playerAttack.charAt(0),  //vilken column spelaren skjutit
                    didWeHit = enemyAttack.charAt(0),           //om spelaren träffade fienden
                    enemyShotRow = enemyAttack.charAt(8),       //vilken rad fienden skjutit, bokstav
                    enemyShotColumn = enemyAttack.charAt(7);    //vilken column fienden skjutit, siffra

            //Uppdaterar enemyBoard
            gameFunction.updateEnemyBoard(playerShotRow, playerShotColumn, didWeHit);

            //Ser ifall fienden träffade/missade/sänkte ett skepp
            char hitOrMiss = gameFunction.gettingShot(enemyShotRow, enemyShotColumn); //Format 'h'

            playerAttack = gameFunction.shooting(); //Skapar spelarens skott: Format "6c"

            //Spelarens respons
            player.send(hitOrMiss + " shot " + playerAttack);

        }

    }
}