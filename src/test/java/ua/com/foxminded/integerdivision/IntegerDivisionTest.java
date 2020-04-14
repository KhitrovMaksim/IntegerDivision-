package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IntegerDivisionTest {

    IntegerDivision integerDivision = new IntegerDivision();

    @Test
    void integerDivision_ThrowsException_IfDivisorsIsZero() {

        assertThrows(IllegalArgumentException.class, () -> {
            integerDivision.divideInColumn(0, 0);
        });
    }
    
    @Test
    void integerDivision_ShouldReturnCertainString_IfDivisorIsOne() {
        String expected =
                "_999|1\n" +
                " 9  |---\n" +
                " -  |999\n" +
                " _9\n" +
                "  9\n" +
                "  -\n" +
                "  _9\n" +
                "   9\n" +
                "   -\n" +
                "   0";

        assertEquals(expected, integerDivision.divideInColumn(999, 1));
    }
    
    @Test
    void integerDivision_ShouldReturnCertainString_IfDivisorAndDividendAreNegativeNumbers() {
        String expected =
                "_4|2\n" +
                " 4|-\n" +
                " -|2\n" +
                " 0";

        assertEquals(expected, integerDivision.divideInColumn(-4, -2));
    }
    
    @Test
    void integerDivision_ShouldReturnCertainString_IfDivisorIsEight() {
        String expected =
                "_512|8\n" +
                " 48 |--\n" +
                " -- |64\n" +
                " _32\n" +
                "  32\n" +
                "  --\n" +
                "   0";

        assertEquals(expected, integerDivision.divideInColumn(512, 8));
    }

}
