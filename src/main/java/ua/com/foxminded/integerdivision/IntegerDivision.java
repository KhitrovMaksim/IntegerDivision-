package ua.com.foxminded.integerdivision;

import java.util.ArrayList;

public class IntegerDivision {

    private ArrayList<String> calculateValues(int dividend, int divisor) {
        ArrayList<String> calculatedValues = new ArrayList<String>();
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

    private String composeFirstString(int dividend, int divisor) {
        return "_" + Integer.toString(dividend) + "|" + Integer.toString(divisor);
    }

    private String composeSecondString(int subtrahend, int dividend, int answer) {
        StringBuilder secondString = new StringBuilder(" " + subtrahend);
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();
        int lengthOfDashes = Integer.toString(answer).length();
        for (int i = 0; i < lengthOfSpaces; i++) {
            secondString.append(" ");
        }
        secondString.append("|");
        for (int i = 0; i < lengthOfDashes; i++) {
            secondString.append("-");
        }
        return secondString.toString();
    }

    private String composeThirdString(int subtrahend, int dividend, int answer) {
        StringBuilder thirdString = new StringBuilder(" ");
        int lengthOfDashes = Integer.toString(subtrahend).length();
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();

        for (int i = 0; i < lengthOfDashes; i++) {
            thirdString.append("-");
        }

        for (int i = 0; i < lengthOfSpaces; i++) {
            thirdString.append(" ");
        }

        thirdString.append("|" + answer);

        return thirdString.toString();
    }

    public void console(int dividend, int divisor) {
        
        if ((dividend == 0) || (divisor == 0)) {
            throw new IllegalArgumentException("Inputs data cannot be 0.");
        }
        
        ArrayList<String> input = calculateValues(dividend, divisor);
        ArrayList<String> output = new ArrayList<String>();
        int inputSize = input.size();
        int remainder = dividend % divisor;
        
        input.add(Integer.toString(remainder));
        
        int answer = dividend / divisor;
        
        output.add(composeFirstString(dividend, divisor));
        output.add(composeSecondString(Integer.parseInt(input.get(1)), dividend, answer));
        output.add(composeThirdString(Integer.parseInt(input.get(1)), dividend, answer));
        int j = 2;
        String space = " ";
        String underscore = "_";

        int lengthOfFirstNumber = input.get(0).length();
        int difference = Integer.parseInt(input.get(0)) - Integer.parseInt(input.get(1));
        if (lengthOfFirstNumber > Integer.toString(difference).length()) {
            space += " ";
            underscore = " " + underscore;
        }

        int iter = (inputSize - 3) / 2;
        for (int i = 0; i <= iter; i++) {

            if (j == (inputSize - 1)) {
                output.add(space + input.get(j));
            } else {
                output.add(underscore + input.get(j));
            }

            if (j == (inputSize - 1)) {
                break;
            } else
                j++;

            output.add(space + input.get(j));

            String delimiter = space;
            int lengthOfDashes = input.get(j).length();
            for (int n = 0; n < lengthOfDashes; n++) {
                delimiter += "-";
            }
            space += " ";
            underscore = " " + underscore;
            output.add(delimiter);

            j++;
        }

        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }

    }

}