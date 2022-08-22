package com.company.gameFunction;

import com.company.GameBoard;
import com.company.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BesiegedTest {

    @Test
    void gettingShot() {
        Besieged besieged = new Besieged();
        GameBoard gameBoard = new GameBoard(10,10,19);
        Ship currentShot = new Ship("Submarine", 2, 4);
        besieged.gettingShot('a','b');

    }

    @Test
    void ShipShouldBeAlive() {
        Besieged besieged = new Besieged();
        Ship ship = new Ship("Submarine", 2, 1);
        assertEquals('h',besieged.isShipAlive(ship));
    }
    @Test
    void ShipShouldBeDead() {
        Besieged besieged = new Besieged();
        Ship ship = new Ship("Hangar", 2 , 0);
        assertEquals('s',besieged.isShipAlive(ship));
    }

    @Test
    void PlayerShouldBeAlive() {
        Besieged besieged = new Besieged();
    }

    @Test
    void getPlayerAmountOfShips() {
    }

    @Test
    void setPlayerAmountOfShips() {
    }

    @Test
    void isAlive() {
    }

    @Test
    void setAlive() {
    }

    @Test
    void getDeadShip() {
    }

    @Test
    void setDeadShip() {
    }
}