import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.olympe.exception.RobocopException;
import org.olympe.simulation.Direction;
import org.olympe.simulation.Position;
import org.olympe.simulation.Robocop;

import java.awt.*;

public class RobocopTest {
    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMovement() throws RobocopException {

        Robocop robot = new Robocop(new Position(0, 0, Direction.NORTH));

        Assert.assertTrue(robot.move());
        Assert.assertEquals(0, robot.getPosition().getX());
        Assert.assertEquals(1, robot.getPosition().getY());
        Assert.assertEquals(Direction.NORTH, robot.getPosition().getDirection());


        robot.setPosition(new Position(1, 2, Direction.EAST));
        robot.move();
        robot.move();
        robot.rotateLeft();
        robot.move();

        Assert.assertEquals(3, robot.getPosition().getX());
        Assert.assertEquals(3, robot.getPosition().getY());
        Assert.assertEquals(Direction.NORTH, robot.getPosition().getDirection());

        robot.setPosition(new Position(0, 0, Direction.NORTH));
        robot.rotateRight();
        Assert.assertEquals(Direction.EAST, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.SOUTH, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.WEST, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.NORTH, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.EAST, robot.getPosition().getDirection());
    }
}

