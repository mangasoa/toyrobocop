package org.olympe.simulation;

import org.olympe.exception.RobocopException;

public class Robocop {
    private Position position;

    public Robocop() {
    }

    public Robocop(Position position) {
        this.position = position;
    }

    public boolean setPosition(Position position) {
        if (position == null)
            return false;

        this.position = position;
        return true;
    }

    public boolean move() throws RobocopException {
        return move(position.getNextPosition());
    }


    //Robocop moves one unit forward in the direction it is currently facing
    //if successful, return true

    public boolean move(Position newPosition) {
        if (newPosition == null)
            return false;

        // change position
        this.position = newPosition;
        return true;
    }

    public Position getPosition() {
        return this.position;
    }


    //Robocop Rotates 90 degrees LEFT
    //if successful, return true

    public boolean rotateLeft() {
        if (this.position.direction == null)
            return false;

        this.position.direction = this.position.direction.leftDirection();
        return true;
    }

    //Robocop Rotates 90 degrees RIGHT
    //if successful, return true
    public boolean rotateRight() {
        if (this.position.direction == null)
            return false;

        this.position.direction = this.position.direction.rightDirection();
        return true;
    }
}
