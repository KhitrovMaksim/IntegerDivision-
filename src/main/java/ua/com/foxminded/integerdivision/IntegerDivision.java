package ua.com.foxminded.integerdivision;

import java.util.ArrayList;

public class IntegerDivision {

    public ArrayList<String> calculateValues(int dividend, int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be 0.");
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        ArrayList<String> calculatedValues = new ArrayList<>();
        char[] digitsDividend = Integer.toString(dividend).toCharArray();
        int amountNumbersToDivide = digitsDividend.length - 1;
        int number = Character.getNumericValue(digitsDividend[0]);
        int numberIndex = 1;
        int remainder = 0;
        int difference = 0;

        do {

            if (number < divisor) {
                number = number * 10 + Character.getNumericValue(digitsDividend[numberIndex]);
                ++numberIndex;
            }

            calculatedValues.add(Integer.toString(number));
            remainder = number % divisor;
            difference = number - remainder;
            calculatedValues.add(Integer.toString(difference));
            number = remainder;

        } while (amountNumbersToDivide >= numberIndex);

        remainder = dividend % divisor;

        calculatedValues.add(Integer.toString(remainder));

        return calculatedValues;
    }
}
