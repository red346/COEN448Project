package src;
import org.example.Commands;
import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.Robot;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class mainTest {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private ByteArrayInputStream testIn;
    private ByteArrayInputStream testIn2;
    private ByteArrayOutputStream testOut;

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
    public void testFirstCommandCorrect() {
        String userInput = "i\n"; // Add a newline character to simulate pressing Enter after input
        testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);

        Main.main(new String[]{});

        String consoleOutput = testOut.toString().trim();
        assertTrue(consoleOutput.contains("user command lowercase is: i"),
                "Expected output to contain 'user command lowercase is: i'");

        String userInput2="7";
        testIn2=new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(testIn2);
    }

    @Test
    public void testFirstCommandIncorrect() {
        String userInput = "u\ni\nu\n";
        testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);

        Main.main(new String[]{});

        String consoleOutput = testOut.toString().trim();
        assertTrue(consoleOutput.contains("First command should be i, try again."),
                "Expected output to contain 'First command should be i, try again.'");
        assertTrue(consoleOutput.contains("user command lowercase is: i"),
                "Expected output to contain 'user command lowercase is: i'");
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
}



