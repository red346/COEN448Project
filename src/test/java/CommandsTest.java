import org.example.Commands;
import org.example.Robot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class CommandsTest {

    @Test
    public void testMovetoRightWithinBounds() {
        Commands commands = new Commands("");
        commands.sizeOfArray = 5;
        commands.penDown = true;
        commands.posX = 0;
        commands.posY = 0;

        commands.MovetoRight(3);

        // Verify the new position and array state
        assertEquals(0, commands.posX);
        assertEquals(3, commands.posY);
        assertEquals("0 0 0 - - - 0 0 0 0", commands.x.get(0).toString());
    }

    @Test
    public void testMovetoRightOutOfBounds() {
        Commands commands = new Commands("");
        commands.sizeOfArray = 5;
        commands.penDown = true;
        commands.posX = 0;
        commands.posY = 3;

        commands.MovetoRight(3);

        // Verify the position and array state should not change
        assertEquals(0, commands.posX);
        assertEquals(3, commands.posY);
        assertEquals("0 0 0 0 0 0 0 0 0 0", commands.x.get(3).toString());
    }

    @Test
    public void testMovetoLeftWithinBounds() {
        Commands commands = new Commands("");
        commands.sizeOfArray = 5;
        commands.penDown = true;
        commands.posX = 0;
        commands.posY = 3;

        commands.MovetoLeft(3);

        // Verify the new position and array state
        assertEquals(0, commands.posX);
        assertEquals(0, commands.posY);
        assertEquals("- - - 0 0 0 0 0 0 0", commands.x.get(0).toString());
    }

    @Test
    public void testMovetoLeftOutOfBounds() {
        Commands commands = new Commands("");
        commands.sizeOfArray = 5;
        commands.penDown = true;
        commands.posX = 0;
        commands.posY = 0;

        commands.MovetoLeft(3);

        // Verify the position and array state should not change
        assertEquals(0, commands.posX);
        assertEquals(0, commands.posY);
        assertEquals("0 0 0 0 0 0 0 0 0 0", commands.x.get(0).toString());
    }

    @Test
    public void testMoveForwardWithinBounds() {
        Commands commands = new Commands("");
        commands.sizeOfArray = 5;
        commands.penDown = true;
        commands.posX = 3;
        commands.posY = 0;

        commands.MoveForward(3);

        // Verify the new position and array state
        assertEquals(0, commands.posX);
        assertEquals(0, commands.posY);
        assertEquals("- - - 0 0 0 0 0 0 0", commands.x.get(0).toString());
    }

    @Test
    public void testMoveForwardOutOfBounds() {
        Commands commands = new Commands("");
        commands.sizeOfArray = 5;
        commands.penDown = true;
        commands.posX = 0;
        commands.posY = 0;

        commands.MoveForward(3);

        // Verify the position and array state should not change
        assertEquals(0, commands.posX);
        assertEquals(0, commands.posY);
        assertEquals("0 0 0 0 0 0 0 0 0 0", commands.x.get(0).toString());
    }
}

