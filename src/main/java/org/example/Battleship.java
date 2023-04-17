package org.example;

import java.awt.Point;

public class Battleship extends Ship {
    private boolean[] isolatedContainers;

    public Battleship(String name, int size, Point start, Point end) {
        super(name, size, start, end);
        this.isolatedContainers = new boolean[size];
    }

    public boolean[] getIsolatedContainers() {
        return isolatedContainers;
    }

    public boolean getShot(Point shotPoint) {
        boolean hit = false;
        int hitIndex = -1;
        for (int i = 0; i < getSize(); i++) {
            if (getIsolatedContainers()[i]) {
                continue;
            }
            if (getPositions()[i].equals(shotPoint)) {
                hit = true;
                hitIndex = i;
                break;
            }
        }
        if (hit) {
            getIsolatedContainers()[hitIndex] = true;
            if (isSunk()) {
                System.out.println("¡Barco hundido!");
            } else {
                System.out.println("¡Barco impactado!");
            }
        } else {
            System.out.println("¡Agua!");
        }
        return hit;
    }

    private Object[] getPositions() {
        Object[] positions = new Object[getSize()];
        if (getDirection() == CardinalPoints.NORTH) {
            for (int i = 0; i < getSize(); i++) {
                positions[i] = new Point(getStart().x, getStart().y - i);
            }
        } else if (getDirection() == CardinalPoints.EAST) {
            for (int i = 0; i < getSize(); i++) {
                positions[i] = new Point(getStart().x + i, getStart().y);
            }
        } else if (getDirection() == CardinalPoints.SOUTH) {
            for (int i = 0; i < getSize(); i++) {
                positions[i] = new Point(getStart().x, getStart().y + i);
            }
        } else if (getDirection() == CardinalPoints.WEST) {
            for (int i = 0; i < getSize(); i++) {
                positions[i] = new Point(getStart().x - i, getStart().y);
            }
        }
        return positions;
    }

    public boolean isSunk() {
        for (boolean isolated : getIsolatedContainers()) {
            if (!isolated) {
                return false;
            }
        }
        return true;
    }
}




