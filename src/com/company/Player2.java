package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Player2 extends Player {

    @Override
    public void start(int port) {
        //Skapar koppling till player 1
        try {
            player2 = new Socket(InetAddress.getLocalHost(), port);
        } catch (IOException e) {
            System.out.println("Nu krashade start p2." + e);
        }

        super.start(port);
    }
}
