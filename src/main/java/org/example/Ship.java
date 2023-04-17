package org.example;

import java.awt.*;

public class Ship {
    private String name;
    private int size;
    private Point start;
    private Point end;
    private CardinalPoints direction;
    private int hits;

    public Ship(String name, int size, Point start, Point end) {
        this.name = name;
        this.size = size;
        this.start = start;
        this.end = end;
        this.direction = calculateDirection();
        this.hits = 0;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public CardinalPoints getDirection() {
        return direction;
    }

    public int getHits() {
        return hits;
    }

    public boolean isSunk() {
        return hits == size;
    }

    public boolean getShot(Point shotPoint) {
        if (isOnShip(shotPoint)) {
            hits++;
            return true;
        } else {
            return false;
        }
    }

    private CardinalPoints calculateDirection() {
        if (start.x == end.x) {
            if (start.y < end.y) {
                return CardinalPoints.NORTH;
            } else {
                return CardinalPoints.SOUTH;
            }
        } else {
            if (start.x < end.x) {
                return CardinalPoints.EAST;
            } else {
                return CardinalPoints.WEST;
            }
        }
    }

    private boolean isOnShip(Point point) {
        if (point.x == start.x && point.y == start.y) {
            return true;
        } else if (point.x == end.x && point.y == end.y) {
            return true;
        } else {
            switch (direction) {
                case NORTH:
                case SOUTH:
                    if (point.x == start.x && point.y >= start.y && point.y <= end.y) {
                        return true;
                    }
                    break;
                case EAST:
                case WEST:
                    if (point.y == start.y && point.x >= start.x && point.x <= end.x) {
                        return true;
                    }
                    break;
            }
            return false;
        }
    }
}


