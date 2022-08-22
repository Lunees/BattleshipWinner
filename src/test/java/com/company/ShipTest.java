package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void getShipName() {
    }

    @Test
    void setShipName() {
    }

    @Test
    void getTypeOfShip() {
    }

    @Test
    void setTypeOfShip() {
    }

    @Test
    void getLengthShouldBeOne() {
        Ship ship = new Ship("Submarine", 2 , 1);
        assertEquals(1, ship.getLength());
    }
    @Test
    void getLengthShouldBeThree() {
        Ship ship = new Ship("Submarine", 2 , 3);
        assertEquals(3, ship.getLength());
    }

    @Test
    void setLength() {
        Ship ship = new Ship("Hangar", 3, 3);
        ship.setLength(4);
        assertEquals(4, ship.getLength());
    }
}