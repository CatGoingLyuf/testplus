package jacoco;

import jacoco.MessageBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageBuilderTest {

    @Test
    public void testGetMessage1() {
        MessageBuilder obj = new MessageBuilder();
        String message = obj.getMessage("");
        System.out.println(message);
    }
}