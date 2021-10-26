package com.company;

public class Player {
    private String name;
    private boolean startingPlayer;

    public Player(String name, boolean startingPlayer) {
        this.name = name;
        this.startingPlayer = startingPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(boolean startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
}
