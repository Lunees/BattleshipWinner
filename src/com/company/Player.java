package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Player {
    public Socket player2;
    public PrintWriter out;
    public BufferedReader in;
    public Scanner scanner = new Scanner(System.in);

    public void start(int port){
        try{

            System.out.println("Player 2 har joinat");

            //Skapar "kommunikationsmedel"
            out = new PrintWriter(player2.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(player2.getInputStream()));

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void send (String string){
        try {
            out.println(string);
            System.out.println("sent");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public String receive(){
        String message = null;
        try {
            message = in.readLine();
            System.out.println(message);
        } catch (IOException e) {
            System.out.println(e);
        }
        return message;
    }

    public void stop (){
        try {
            player2.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
