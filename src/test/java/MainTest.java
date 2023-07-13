import org.example.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    public void testFirstCommandCorrect() {
        String userInput = "i\nu\n";
        InputStream sysInBackup = System.in; // Backup System.in to restore it later
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String consoleOutput = out.toString().trim();
        assertTrue(consoleOutput.contains("user command lowercase is: i"), "Expected output to contain 'user command lowercase is: i'");

        // Restore System.in
        System.setIn(sysInBackup);
    }

    @Test
    public void testFirstCommandIncorrect() {
        String userInput = "u\ni\nu\n";
        InputStream sysInBackup = System.in; // Backup System.in to restore it later
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String consoleOutput = out.toString().trim();
        assertTrue(consoleOutput.contains("First command should be i, try again."), "Expected output to contain 'First command should be i, try again.'");
        assertTrue(consoleOutput.contains("user command lowercase is: i"), "Expected output to contain 'user command lowercase is: i'");

        // Restore System.in
        System.setIn(sysInBackup);
    }
}
