import org.example.Commands;
import org.example.Robot;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class commandsTest {

    private final InputStream standardIn = System.in;
    private ByteArrayInputStream testIn;
    @Test
    public void testArrayInitialization()
    {
        Commands testCommand= new Commands("i");
        int arrSize=6;
        testCommand.InitializeArray(6);
        //Robot testBipBop=new Robot(6);
        assertEquals(5, testCommand.getBipbop().posx);
        assertEquals(0,testCommand.getBipbop().posy);
    }

/*
    @Test
    public void testPenUpDown()
    {
        Commands testCommand=new Commands("u");
       // testCommand.identifyCommand();
        testCommand.setPenUp(true);

        assertTrue(testCommand.penUp);
       // assertEquals(false,testCommand.penDown);

        String userInput = "q"; // Add a newline character to simulate pressing Enter after input
        testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);

        Commands testCommand2=new Commands("d");
        testCommand2.identifyCommand();

        assertTrue(testCommand2.penDown);
        assertEquals(false,testCommand2.penUp);
    }*/


    @Test
    public void testRobotPositionEast()
    {
        Commands testCommand=new Commands("i");
        testCommand.InitializeArray(8);
        testCommand.setTurnRight(true);
        testCommand.MovetoRight(3);

        assertEquals(7,testCommand.getBipbop().posx);
        assertEquals(3,testCommand.getBipbop().posy);

    }

    @Test
    public void testRobotPositionGoingUp()
    {
        Commands testCommand=new Commands("i");
        testCommand.InitializeArray(10);
        testCommand.MoveForward(2);

        assertEquals(7,testCommand.getBipbop().posx);
        assertEquals(0,testCommand.getBipbop().posy);
    }

    @Test
    public void testRobotPositionGoingLeft()
    {
        Commands testCommand=new Commands("i");
        testCommand.InitializeArray(10);
        testCommand.setTurnRight(true);
        testCommand.MovetoRight(2);
        testCommand.MoveForward(3);
        testCommand.setTurnLeft(true);
        testCommand.setTurnRight(false);
        testCommand.MovetoLeft(2);

        assertEquals(6,testCommand.getBipbop().posx);
        assertEquals(0,testCommand.getBipbop().posy);

    }

    /*
    @Test
    public void testOutOfBoundsMovetoRight() {
        Commands testCommand = new Commands("i");
        int arrSize = 6;
        testCommand.InitializeArray(6);

        // Move the robot to the right beyond the array bounds
        testCommand.MovetoRight(8);

        // Verify that the robot position is at the rightmost boundary
        //Robot testBipBop = new Robot(6);
        assertEquals(5, testBipBop.posx);
        assertEquals(0, testBipBop.posy);
    }*/


}
