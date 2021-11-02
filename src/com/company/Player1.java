package com.company;

import java.io.*;
import java.net.ServerSocket;

public class Player1 extends Player {
    private ServerSocket player1;

    @Override
    public void start(int port) {
        try {
            player1 = new ServerSocket(port);

            System.out.println("Väntar på player 2");
            player2 = player1.accept();
        }
        catch (IOException e) {
            System.out.println("Nu krashade start p1." + e);
        }

        super.start(port);
    }

}
