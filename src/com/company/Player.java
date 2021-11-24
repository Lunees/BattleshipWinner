package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//Default player-klass
public abstract class Player {
    //Variabler
    public Socket player2;
    public PrintWriter out;
    public BufferedReader in;

    //Startar uppkoppling till server/client
    public void start(int port){
        try{

            System.out.println("Player 2 har joinat");

            //Skapar "kommunikationsmedel"
            out = new PrintWriter(player2.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(player2.getInputStream()));

        }
        catch (Exception e){
            System.out.println("Nu krashade Start." + e);
        }
    }

    //Skickar ut meddelande
    public void send (String message){
        try {
            out.println(message);
            System.out.println(message);
        }
        catch (Exception e){
            System.out.println("Nu krashade Send." + e);
        }
    }

    //Tar emot meddelande
    public String receive(){
        String message = null;
        try {
            message = in.readLine();
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Nu krashade Receive." + e);
        }
        return message;
    }

    //Stoppar uppkopplingen
    public void stop (){
        try {
            player2.close();
        }
        catch (IOException e){
            System.out.println("Nu krashade stop." + e);
        }
    }
}
