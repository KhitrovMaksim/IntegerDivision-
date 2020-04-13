package ua.com.foxminded.integerdivision;

import java.util.ArrayList;

public class View {

    public void consoleOutput(int dividend, int divisor) {
        IntegerDivision integerDivision = new IntegerDivision();
        ResultPrepare strings = new ResultPrepare();
        ArrayList<String> input = integerDivision.calculateValues(dividend, divisor);
        ArrayList<String> output = strings.composeStrings(input, dividend, divisor);

        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }
    }
}
