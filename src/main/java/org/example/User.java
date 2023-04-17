import org.example.Ship;

import java.awt.*;
import java.util.ArrayList;

public class User {

    private ArrayList<Ship> ships;
    private boolean isAlive;

    public User(ArrayList<Ship> ships) throws Exception {
        if (ships == null || ships.size() < 1) {
            throw new Exception("Se debe proporcionar al menos un barco para crear al usuario.");
        }
        this.ships = ships;
        this.isAlive = true;
    }

    // getters y setters
    public ArrayList<Ship> getShips() {
        return ships;
    }

    public boolean isAlive() {
        return isAlive;
    }

    private void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * Realiza un ataque al usuario pasado como parámetro en el punto especificado. Si algún
     * barco del usuario es impactado, se devuelve true. En caso contrario, se devuelve false.
     */
    public boolean attack(Point shotPoint, User user) throws Exception {
        if (user == null) {
            throw new Exception("Se debe proporcionar un usuario válido para realizar un ataque.");
        }

        boolean hit = false;
        for (Ship ship : user.getShips()) {
            if (ship.getShot(shotPoint)) {
                hit = true;
                if (ship.isSunk()) {
                    System.out.println("¡Barco hundido!");
                }
            }
        }
        if (!hit) {
            System.out.println("¡Agua!");
        }
        return hit;
    }

    /**
     * Recibe un disparo en el punto especificado. Si algún barco del usuario es impactado, se
     * devuelve true. En caso contrario, se devuelve false.
     */
    public boolean getShot(Point shotPoint) {
        boolean hit = false;
        for (Ship ship : ships) {
            if (ship.getShot(shotPoint)) {
                hit = true;
                if (ship.isSunk()) {
                    System.out.println("¡Barco hundido!");
                }
            }
        }
        if (!hit) {
            System.out.println("¡Agua!");
        }
        if (allShipsSunk()) {
            die();
        }
        return hit;
    }

    /**
     * Verifica si todos los barcos del usuario han sido hundidos. Devuelve true si es el caso.
     */
    private boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Mata al usuario estableciendo el atributo isAlive en false.
     */
    private void die() {
        setAlive(false);
    }
}
