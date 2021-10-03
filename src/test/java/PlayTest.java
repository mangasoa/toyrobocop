import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.olympe.Play;
import org.olympe.exception.RobocopException;
import org.olympe.simulation.Direction;
import org.olympe.simulation.Position;
import org.olympe.simulation.TableSquare;
import org.olympe.simulation.Robocop;

import java.awt.*;

public class PlayTest {
    final int TABLE_ROWS = 5;
    final int TABLE_COLUMNS = 5;
    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPlacing() throws RobocopException {

        TableSquare board = new TableSquare(TABLE_COLUMNS, TABLE_ROWS);
        Robocop toyRobot = new Robocop();
        Play game = new Play(board, toyRobot);

        Assert.assertTrue(game.placeToyRobot(new Position(0, 1, Direction.NORTH)));
        Assert.assertTrue(game.placeToyRobot(new Position(2, 2, Direction.SOUTH)));
        Assert.assertFalse(game.placeToyRobot(new Position(6, 6, Direction.WEST)));
        Assert.assertFalse(game.placeToyRobot(new Position(-1, 5, Direction.EAST)));
    }

    @Test
    public void testPlacingExceptions() throws RobocopException {

        TableSquare board = new TableSquare(TABLE_COLUMNS, TABLE_ROWS);
        Robocop toyRobot = new Robocop();
        Play game = new Play(board, toyRobot);

        thrown.expect(RobocopException.class);
        game.placeToyRobot(null);
        thrown.expect(RobocopException.class);
        game.placeToyRobot(new Position(0, 1, null));
    }

    @Test
    public void testEval() throws RobocopException {

        TableSquare board = new TableSquare(TABLE_COLUMNS, TABLE_ROWS);
        Robocop toyRobot = new Robocop();
        Play game = new Play(board, toyRobot);

        game.eval("PLACE 0,0,NORTH");
        Assert.assertEquals("0,0,NORTH", game.eval("REPORT"));

        game.eval("MOVE");
        game.eval("RIGHT");
        game.eval("MOVE");
        Assert.assertEquals("1,1,EAST", game.eval("REPORT"));

        // if it goes out of the board it ignores the command
        for (int i = 0; i < 10; i++)
            game.eval("MOVE");
        Assert.assertEquals("5,1,EAST", game.eval("REPORT"));

        //rotate on itself
        for (int i = 0; i < 4; i++)
            game.eval("LEFT");
        Assert.assertEquals("5,1,EAST", game.eval("REPORT"));

        // invalid commands
        thrown.expect(RobocopException.class);
        Assert.assertEquals("Invalid command", game.eval("PLACE12NORTH"));
        thrown.expect(RobocopException.class);
        Assert.assertEquals("Invalid command", game.eval("LEFFT"));
        thrown.expect(RobocopException.class);
        Assert.assertEquals("Invalid command", game.eval("RIGHTT"));
    }
}
