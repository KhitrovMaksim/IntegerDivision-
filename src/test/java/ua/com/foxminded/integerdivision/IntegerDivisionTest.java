package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IntegerDivisionTest {
    
    IntegerDivision integerDivision = new IntegerDivision();
    
    @Test
    public void integerDivision_ThrowsException_IfInputNumbersIsZero() {

        assertThrows(IllegalArgumentException.class, () -> {
            integerDivision.console(0, 0);
        });
    }
}
