package ua.com.foxminded.integerdivision;

import java.util.ArrayList;

public class IntegerDivision {
    private static String minusSign = "_";
    private static String verticalBar = "|";
    private static String indent = " ";
    private static String delimiter = "-";

    public String divideInColumn(int dividend, int divisor) {
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        ArrayList<String> input = calculateDivisionSteps(dividend, divisor);

        return composeDivisionResult(input, dividend, divisor);
    }

    private ArrayList<String> calculateDivisionSteps(int dividend, int divisor) {

        if ((divisor == 0) || (divisor > dividend)) {
            throw new IllegalArgumentException("Divisor cannot be 0 or bigger than dividend.");
        }

        ArrayList<String> calculatedSteps = new ArrayList<>();
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

            calculatedSteps.add(Integer.toString(number));
            remainder = number % divisor;
            difference = number - remainder;
            calculatedSteps.add(Integer.toString(difference));
            number = remainder;

        } while (amountNumbersToDivide >= numberIndex);

        return calculatedSteps;
    }

    private String composeDivisionResult(ArrayList<String> input, int dividend, int divisor) {

        int answer = dividend / divisor;
        ArrayList<String> output = new ArrayList<>();

        output.addAll(composeFirstString(dividend, divisor));
        output.addAll(composeSecondString(Integer.parseInt(input.get(1)), dividend, answer));
        output.addAll(composeThirdString(Integer.parseInt(input.get(1)), dividend, answer));
        output.addAll(composeRemainingStrings(input, dividend, divisor));
        StringBuilder result = new StringBuilder("");

        for (int i = 0; i < output.size(); i++) {
            if (i == output.size() - 1) {
                result.append(output.get(i));
            } else {
                result.append(output.get(i) + "\n");
            }
        }

        return result.toString();
    }

    private ArrayList<String> composeFirstString(int dividend, int divisor) {
        ArrayList<String> firstString = new ArrayList<>();

        firstString.add(String.format("%1$s%2$s%3$s%4$s", minusSign, dividend, verticalBar, divisor));

        return firstString;
    }

    private ArrayList<String> composeSecondString(int subtrahend, int dividend, int answer) {
        ArrayList<String> secondString = new ArrayList<>();
        StringBuilder stringPrepare = new StringBuilder(indent + subtrahend);
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();
        int lengthOfDashes = Integer.toString(answer).length();

        for (int i = 0; i < lengthOfSpaces; i++) {
            stringPrepare.append(indent);
        }

        stringPrepare.append(verticalBar);

        for (int i = 0; i < lengthOfDashes; i++) {
            stringPrepare.append(delimiter);
        }

        secondString.add(stringPrepare.toString());

        return secondString;
    }

    private ArrayList<String> composeThirdString(int subtrahend, int dividend, int answer) {
        ArrayList<String> thirdString = new ArrayList<>();
        StringBuilder stringPrepare = new StringBuilder(indent);
        int lengthOfDashes = Integer.toString(subtrahend).length();
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();

        for (int i = 0; i < lengthOfDashes; i++) {
            stringPrepare.append(delimiter);
        }

        for (int i = 0; i < lengthOfSpaces; i++) {
            stringPrepare.append(indent);
        }

        thirdString.add(stringPrepare.append(verticalBar + answer).toString());

        return thirdString;
    }

    private ArrayList<String> composeRemainingStrings(ArrayList<String> input, int dividend, int divisor) {
        ArrayList<String> remainingStrings = new ArrayList<>();

        int inputIterator = 2;
        int numberOfLines = input.size();
        String indentation = lenghtOfFirstIndent(input) + indent;
        String minusSignWithIndent = lenghtOfFirstIndent(input) + minusSign;
        int remainder = dividend % divisor;

        if (inputIterator == input.size()) {
            indentation = indentation.substring(1, indentation.length());
            remainingStrings.add(indentation + remainder);
        } else {

            for (int i = 0; i <= ((numberOfLines - 3) / 2); i++) {

                if (inputIterator == (numberOfLines - 1)) {
                    remainingStrings.add(indentation + input.get(inputIterator));
                    break;
                } else {
                    remainingStrings.add(minusSignWithIndent + input.get(inputIterator));
                    inputIterator++;
                }

                remainingStrings.add(indentation + input.get(inputIterator));
                remainingStrings.addAll(delimeterWithIndentation(indentation, input.get(inputIterator).length()));

                indentation += indent;
                minusSignWithIndent = indent + minusSignWithIndent;
                inputIterator++;
            }

            String lastString = remainingStrings.get(remainingStrings.size() - 1);
            lastString = lastString.trim();
            String delimiterWithIndent = delimiter + delimiter;
            boolean equals = lastString.equals(delimiterWithIndent);

            if (equals) {
                remainingStrings.add(indentation + remainder);
            } else {
                indentation = indentation.substring(1, indentation.length());
                remainingStrings.add(indentation + remainder);
            }
        }

        return remainingStrings;
    }

    private String lenghtOfFirstIndent(ArrayList<String> calculatedValues) {
        String indentation;
        int lengthOfFirstNumber = calculatedValues.get(0).length();
        int difference = Integer.parseInt(calculatedValues.get(0)) - Integer.parseInt(calculatedValues.get(1));

        if (lengthOfFirstNumber > Integer.toString(difference).length() || difference == 0) {
            indentation = indent;
        } else {
            indentation = "";
        }

        return indentation;
    }

    private ArrayList<String> delimeterWithIndentation(String indentation, int delimeterLenght) {
        ArrayList<String> delimeterWithIndentation = new ArrayList<>();
        String delimiterWithIndent = indentation;

        for (int i = 0; i < delimeterLenght; i++) {
            delimiterWithIndent += delimiter;
        }

        delimeterWithIndentation.add(delimiterWithIndent);

        return delimeterWithIndentation;
    }
}
