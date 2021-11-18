package com.company;

import com.company.gameFunction.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //Starta både main och main2, i console för main ska man trycka enter efter varje runda
        //Skapar objekt
        GameBoard playerBoard = new GameBoard(10, 10, 9);
        GameBoard enemyBoard = new GameBoard(10,10, 0);

        Placement placement = new Placement();
        placement.setGameBoard(playerBoard);

        Besieged besieged = new Besieged(playerBoard, enemyBoard);

        Firing firing = new Firing(enemyBoard, playerBoard);

        Scanner scanner = new Scanner(System.in);

        Player player; //Om det är spelare 1 eller 2 bestäms senare, detta tack vare att Player är en abstrakt klass

        int port = 8900; //Bestämmer port
        int pause = 2; //Bestämmer tid att vänta mellan varje skott

        //Spelaren får bestämma om den ska vara spelare 1 eller 2
        System.out.println("Spelare 1 eller 2?");
        int playerChoice = scanner.nextInt();

        String playerAttack = "0a";
        //Sätter upp spelaren
        while(true){
             if (playerChoice == 1){
                 player = new Player1(); //Sätter player som player 1
                 player.start(port);
                 playerAttack = firing.shootingRandom(); //Skapar spelarens skott

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
            //Väntar i några sekunder innan programmet fortsätter
            try {
                TimeUnit.SECONDS.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Tar emot fiendens attack
            System.out.println("Fiendens attack");
            String enemyAttack = player.receive(); //Format "h shot 6c"

            System.out.println(playerAttack);
            //Variabler för den data som behövs
            char    playerShotRow = playerAttack.charAt(1),     //Vilken rad som spelaren skjutit
                    playerShotColumn = playerAttack.charAt(0),  //vilken column spelaren skjutit
                    didWeHit = enemyAttack.charAt(0),           //om spelaren träffade fienden
                    enemyShotRow = enemyAttack.charAt(8),       //vilken rad fienden skjutit, bokstav
                    enemyShotColumn = enemyAttack.charAt(7);    //vilken column fienden skjutit, siffra

            //Kolla om spelaren vunnit
            if(player.receive().equals("game over")){
                System.out.println("Vinnare i spelet!");
                break;
            }
            //Uppdaterar enemyBoard
            enemyBoard.updateBoard(playerShotRow, playerShotColumn, didWeHit);

            //Ser ifall fienden träffade/missade/sänkte ett skepp
            char hitOrMiss = besieged.gettingShot(enemyShotRow, enemyShotColumn); //Format 'h'

            //Kollar om vi lever och skickar i så fall game over till fienden
            if(!besieged.isAlive()){
                player.send("game over");
                playerBoard.showGameBoard();
                break;
            }
            //Visar spelplanerna
            playerBoard.showGameBoard();
            enemyBoard.showGameBoard();

            playerAttack = firing.planAttack(didWeHit); //Skapar spelarens skott: Format "6c"

            //Spelarens respons
            System.out.println("Spelarens attack");
            player.send(hitOrMiss + " shot " + playerAttack);


        }

        player.stop();

    }
}