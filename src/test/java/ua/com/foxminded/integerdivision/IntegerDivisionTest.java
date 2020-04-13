package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IntegerDivisionTest {

    IntegerDivision integerDivision = new IntegerDivision();

    @Test
    void integerDivision_ThrowsException_IfDivisorsIsZero() {

        assertThrows(IllegalArgumentException.class, () -> {
            integerDivision.calculateValues(0, 0);
        });
    }
}
