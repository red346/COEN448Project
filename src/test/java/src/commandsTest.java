package src;

import org.example.Commands;
import org.example.Robot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class commandsTest {
    @Test
    public void testArrayInitialization()
    {
        Commands testCommand= new Commands("i");
        int arrSize=6;
        testCommand.InitializeArray(6);
        Robot testBipBop=new Robot(6);
        assertEquals(5, testBipBop.posx);
        assertEquals(0,testBipBop.posy);
    }

    @Test
    public void testOutOfBoundsMovetoRight() {
        Commands testCommand = new Commands("i");
        int arrSize = 6;
        testCommand.InitializeArray(6);

        // Move the robot to the right beyond the array bounds
        testCommand.MovetoRight(8);

        // Verify that the robot position is at the rightmost boundary
        Robot testBipBop = new Robot(6);
        assertEquals(5, testBipBop.posx);
        assertEquals(0, testBipBop.posy);
    }

    @Test
    public void testOutOfBoundsMovetoLeft() {
        Commands testCommand = new Commands("i");
        int arrSize = 6;
        testCommand.InitializeArray(6);

        // Move the robot to the left beyond the array bounds
        testCommand.MovetoLeft(8);

        // Verify that the robot position is at the leftmost boundary
        Robot testBipBop = new Robot(6);
        assertEquals(0, testBipBop.posx);
        assertEquals(0, testBipBop.posy);
    }

    @Test
    public void testOutOfBoundsMoveForward() {
        Commands testCommand = new Commands("i");
        int arrSize = 6;
        testCommand.InitializeArray(6);

        // Move the robot forward beyond the array bounds
        testCommand.MoveForward(8);

        // Verify that the robot position is at the upper boundary
        Robot testBipBop = new Robot(6);
        assertEquals(0, testBipBop.posx);
        assertEquals(0, testBipBop.posy);
    }

    @Test
    public void testRobotPosition()
    {

    }
}
