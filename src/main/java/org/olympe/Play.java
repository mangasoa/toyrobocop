package org.olympe;
import org.olympe.exception.RobocopException;
import org.olympe.simulation.*;

import java.awt.*;

public class Play {

    ITable tableSquare;
    Robocop robot;

    public Play(ITable tableSquare, Robocop robot) {
        this.tableSquare = tableSquare;
        this.robot = robot;
    }

    //place robocop on the tableSquare in position X,Y and facing north, south, east or west
    public boolean placeToyRobot(Position position) throws RobocopException {

        if (tableSquare == null)
            throw new RobocopException("Invalid squareBoard object");

        if (position == null)
            throw new RobocopException("Invalid position object");

        if (position.getDirection() == null)
            throw new RobocopException("Invalid direction value");

        // validate the position
        if (!tableSquare.isValidPosition(position))
            return false;

        // if position is valid then assign values to fields
        robot.setPosition(position);
        return true;
    }

    //Evaluation and execution of a string command
    //@param inputString command string
    //@returns string value of the executed command

    public String eval(String inputString) throws RobocopException {
        String[] args = inputString.split(" ");

        // validate command
        Consolecommand command;
        try {
            command = Consolecommand.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new RobocopException("Invalid command");
        }
        if (command == Consolecommand.PLACE && args.length < 2) {
            throw new RobocopException("Invalid command");
        }

        // validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;
        if (command == Consolecommand.PLACE) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                commandDirection = Direction.valueOf(params[2]);
            } catch (Exception e) {
                throw new RobocopException("Invalid command");
            }
        }

        String output;

        switch (command) {
            case PLACE:
                output = String.valueOf(placeToyRobot(new Position(x, y, commandDirection)));
                break;
            case MOVE:
                Position newPosition = robot.getPosition().getNextPosition();
                if (!tableSquare.isValidPosition(newPosition))
                    output = String.valueOf(false);
                else
                    output = String.valueOf(robot.move(newPosition));
                break;
            case LEFT:
                output = String.valueOf(robot.rotateLeft());
                break;
            case RIGHT:
                output = String.valueOf(robot.rotateRight());
                break;
            case REPORT:
                output = report();
                break;
            default:
                throw new RobocopException("Invalid command");
        }

        return output;
    }

    /**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {
        if (robot.getPosition() == null)
            return null;

        return robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();
    }
}
