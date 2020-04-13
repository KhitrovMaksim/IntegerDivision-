package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ResultPrepareTest {

    ResultPrepare strings = new ResultPrepare();
    IntegerDivision result = new IntegerDivision();

    @Test
    void ResultPrepare_ShouldReturnCertainStrings_IfSetsTheSamePositiveVariables() {
        ArrayList<String> output = new ArrayList<>();
        output.add("_4|2");
        output.add(" 4|-");
        output.add(" -|2");
        output.add(" 0");

        assertEquals(output, strings.composeStrings(result.calculateValues(4, 2), 4, 2));
    }
    
    @Test
    void ResultPrepare_ShouldReturnCertainStrings_IfSetsTheNegativeVariables() {
        ArrayList<String> output = new ArrayList<>();
        output.add("_4|2");
        output.add(" 4|-");
        output.add(" -|2");
        output.add(" 0");

        assertEquals(output, strings.composeStrings(result.calculateValues(-4, -2), 4, 2));
    }
}
