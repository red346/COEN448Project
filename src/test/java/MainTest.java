
import org.example.Commands;
import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private ByteArrayInputStream testIn;
    private ByteArrayInputStream testIn2;
    private ByteArrayOutputStream testOut;
    private ByteArrayOutputStream testOut2;

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


        new Main();
        Main.main(new String[]{});

        String consoleOutput = testOut.toString().trim();
        assertTrue(consoleOutput.contains("user command lowercase is: i"),
                "Expected output to contain 'user command lowercase is: i'");

        consoleOutput=testOut.toString().trim();
        assertTrue(consoleOutput.contains("Enter array size"),"Expected output to contain 'Enter array size'");

        String userInput2="7";
        testIn2=new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(testIn2);

        consoleOutput=testOut.toString().trim();
        assertTrue(consoleOutput.contains("arraysize is: 7"),"Expected output to contain 'arraysize: 7'");

    }


    @Test
    public void testFirstCommandIncorrect() {
        String userInput = "u\ni\nu\n";
        testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);


        new Main();
        Main.main(new String[]{});

        String userInput2="7";
        testIn2=new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(testIn2);

        String consoleOutput = testOut.toString().trim();
        assertTrue(consoleOutput.contains("First command should be i, try again."),
                "Expected output to contain 'First command should be i, try again.'");
        assertTrue(consoleOutput.contains("user command lowercase is: i"),
                "Expected output to contain 'user command lowercase is: i'");

    }
}

