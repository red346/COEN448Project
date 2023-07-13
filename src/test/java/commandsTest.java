import org.example.Commands;
import org.example.Robot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testRobotPosition()
    {

    }
}
