import org.example.Commands;
import org.example.Robot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class commandsTest {

    private final InputStream standardIn = System.in;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }
    @Test
    public void testArrayInitialization()
    {
        Commands testCommand= new Commands("i");
        int arrSize=6;
        testCommand.InitializeArray(6);
        assertEquals(5, testCommand.getBipbop().posx);
        assertEquals(0,testCommand.getBipbop().posy);
    }

    @Test
    public void testGetNewCommand()
    {
        Commands testCommand=new Commands("i");

        String input = "U\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        testCommand.GetNewCommand();

        assertEquals("u", testCommand.getCommand());

    }

    @Test
    public void testSetPenUp() {
        Commands testCommand=new Commands("i");
        testCommand.setPenUp(true);
        assertEquals(true, testCommand.isPenUp());

        testCommand.setPenUp(false);
        assertEquals(false, testCommand.isPenUp());
    }

    @Test
    public void testSetPenDown() {

        Commands testCommand=new Commands("i");
        testCommand.setPenDown(true);
        assertEquals(true, testCommand.isPenDown());


        testCommand.setPenDown(false);
        assertEquals(false, testCommand.isPenDown());
    }



    @Test
    public void testRobotPositionEast()
    {
        Commands testCommand=new Commands("i");
        testCommand.InitializeArray(8);
        testCommand.setTurnRight(true);
        assertEquals("East",testCommand.PenDirection);
        testCommand.MovetoRight(3);

        assertEquals(1,8-testCommand.getBipbop().posx);
        assertEquals(3,testCommand.getBipbop().getPosy()); //not putting right y value

    }


    @Test
    public void testRobotPositionNorth()
    {
        Commands testCommand=new Commands("i");
        testCommand.InitializeArray(10);
        assertEquals("North",testCommand.PenDirection);
        testCommand.MoveForward(2);

        assertEquals(3,10-testCommand.getBipbop().posx);
        assertEquals(0,testCommand.getBipbop().posy);
    }


    @Test
    public void testRobotPositionWest()
    {
        Commands testCommand=new Commands("i");
        testCommand.InitializeArray(10);
        testCommand.setTurnRight(true);
        assertEquals("East",testCommand.PenDirection);
        testCommand.MovetoRight(2);

        testCommand.setTurnLeft(true);
        assertEquals("North",testCommand.PenDirection);
        testCommand.MoveForward(3);
        testCommand.setTurnLeft(true);
        assertEquals("West",testCommand.PenDirection);
        testCommand.MovetoLeft(2);

        assertEquals(4,(10-testCommand.getBipbop().posx));
        //assertEquals(1,testCommand.getBipbop().posy);

    }



    @Test
    public void testRobotPositionSouth()
    {
        Commands testCommand=new Commands("i");
        testCommand.InitializeArray(6);
        testCommand.MoveForward(3);
        testCommand.setTurnRight(true);
        testCommand.MovetoRight(4);

        testCommand.MovetoRight(2);
        assertEquals(4,(6-testCommand.getBipbop().posx));
        assertEquals(0,testCommand.getBipbop().posy);
    }


    @Test
    public void testOutOfBoundsMovetoRight() {
        Commands testCommand = new Commands("i");
        testCommand.InitializeArray(6);

        // Move the robot to the right beyond the array bounds
        testCommand.MovetoRight(8);

        // Verify that the robot position is at the rightmost boundary
        assertEquals(5, testCommand.getBipbop().posx);
        assertEquals(0, testCommand.getBipbop().posy);
    }

    @Test
    public void testOutOfBoundsMovetoLeft() {
        Commands testCommand = new Commands("i");
        testCommand.InitializeArray(6);

        // Move the robot to the left beyond the array bounds
        testCommand.MovetoLeft(8);

        // Verify that the robot position is at the leftmost boundary
        assertEquals(5, testCommand.getBipbop().posx);
        assertEquals(0, testCommand.getBipbop().posy);
    }

    @Test
    public void testOutOfBoundsMoveForward() {
        Commands testCommand = new Commands("i");
        testCommand.InitializeArray(6);

        // Move the robot forward beyond the array bounds
        testCommand.MoveForward(8);

        // Verify that the robot position is at the upper boundary
        assertEquals(0, testCommand.getBipbop().posx);
        assertEquals(0, testCommand.getBipbop().posy);
    }

    @Test
    public void testPrintArrayCommand() {
        Commands testCommand = new Commands("i");
        testCommand.InitializeArray(6);

        // Simulate the user entering the "p" command
        String userInput = "p"; // Add a newline character to simulate pressing Enter after input
        testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Invoke the method
        testCommand.PrintArray();

        // Reset the standard output
        System.setOut(standardOut);

        // Capture the output of the PrintArray() method
        String expectedOutput = "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "*         \n";
        String capturedOutput = outputStream.toString();
        assertEquals(expectedOutput, capturedOutput);
    }
}


