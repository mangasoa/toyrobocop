package org.olympe;

import org.olympe.exception.RobocopException;
import org.olympe.simulation.TableSquare;
import org.olympe.simulation.Robocop;

import java.awt.*;
import java.io.Console;


public class Main
{
    public static void main( String[] args )
    {
        Console console = System.console();

        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        TableSquare tableSquare = new TableSquare(4, 4);
        Robocop robot = new Robocop();
        Play game = new Play(tableSquare, robot);

        System.out.println("Robocop Simulator");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        boolean walking = true;
        while (walking) {
            String inputString = console.readLine(": ");
            if ("EXIT".equals(inputString)) {
                walking = false;
            } else {
                try {
                    String outputVal = game.eval(inputString);
                    System.out.println(outputVal);
                } catch (RobocopException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
