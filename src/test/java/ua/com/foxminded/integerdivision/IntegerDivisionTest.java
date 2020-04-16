package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IntegerDivisionTest {

    IntegerDivision integerDivision = new IntegerDivision();

    @Test
    void integerDivision_ThrowsException_IfDivisorIsZero() {

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

    @Test
    void integerDivision_ShouldReturnCertainString_IfDividendHasTwoZeroInside() {
        String expected =
                "_1001|1\n" +
                " 1   |----\n" +
                " -   |1001\n" +
                " _0\n" +
                "  0\n" +
                "  -\n" +
                "  _0\n" +
                "   0\n" +
                "   -\n" +
                "   _1\n" +
                "    1\n" +
                "    -\n" +
                "    0";

        assertEquals(expected, integerDivision.divideInColumn(1001, 1));
    }
    
    @Test
    void integerDivision_ShouldReturnCertainString_IfDivisorAndDividendAreEquals() {
        String expected =
                "_100|100\n" +
                " 100|---\n" +
                " ---|1\n" +
                "   0";

        assertEquals(expected, integerDivision.divideInColumn(100, 100));
    }
    
    @Test
    void integerDivision_ShouldReturnCertainString_IfDividendIsNegative() {
        String expected =
                "_75451|53\n" +
                " 53   |----\n" +
                " --   |1423\n" +
                "_224\n" +
                " 212\n" +
                " ---\n" +
                " _125\n" +
                "  106\n" +
                "  ---\n" +
                "  _191\n" +
                "   159\n" +
                "   ---\n" +
                "    32";

        assertEquals(expected, integerDivision.divideInColumn(-75451, 53));
    }
    
    @Test
    void integerDivision_ShouldReturnCertainString_IfDivisorGreaterThenDividend() {
        String expected =
                "253|6579\n" +
                "   |----\n" +
                "   |0";

        assertEquals(expected, integerDivision.divideInColumn(253, 6579));
    }
}
