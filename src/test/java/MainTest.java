import org.example.Main;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    public void testFirstCommandCorrect() {
        String userInput = "i\nu\n";
        InputStream sysInBackup = System.in; // Backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String consoleOutput = out.toString().trim();
        assertTrue(consoleOutput.contains("user command lowercase is: i"));

        // Restore System.in
        System.setIn(sysInBackup);
    }

    @Test
    public void testFirstCommandIncorrect() {
        String userInput = "u\ni\nu\n";
        InputStream sysInBackup = System.in;
        // Backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String consoleOutput = out.toString().trim();
        assertTrue("Expected output to contain 'First command should be i, try again.'", consoleOutput.contains("First command should be i, try again."));
        assertTrue("Expected output to contain 'user command lowercase is: i'", consoleOutput.contains("user command lowercase is: i"));

        // Restore System.in
        System.setIn(sysInBackup);
    }
}
