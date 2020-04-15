package ua.com.foxminded.integerdivision;

import java.util.ArrayList;

public class IntegerDivision {
    private static String UNDERSCORE = "_";
    private static String VERTICAL_BAR = "|";
    private static String INDENT = " ";
    private static String DASH = "-";
    private static String LINE_END = "\n";

    public String divideInColumn(int dividend, int divisor) {
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        ArrayList<String> divisionSteps = calculateDivisionSteps(dividend, divisor);

        return composeDivisionResult(divisionSteps, dividend, divisor);
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

    private String composeDivisionResult(ArrayList<String> divisionSteps, int dividend, int divisor) {

        int answer = dividend / divisor;
        StringBuilder output = new StringBuilder("");

        output.append(composeFirstString(dividend, divisor));
        output.append(composeSecondString(Integer.parseInt(divisionSteps.get(1)), dividend, answer));
        output.append(composeThirdString(Integer.parseInt(divisionSteps.get(1)), dividend, answer));
        output.append(composeDivisionSteps(divisionSteps, dividend, divisor));

        return output.toString();
    }

    private String composeFirstString(int dividend, int divisor) {

        return String.format("%1$s%2$s%3$s%4$s%5$s", UNDERSCORE, dividend, VERTICAL_BAR, divisor, LINE_END);
    }

    private String composeSecondString(int subtrahend, int dividend, int answer) {
        String secondString = "";
        StringBuilder stringPrepare = new StringBuilder(INDENT + subtrahend);
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();
        int lengthOfDashes = Integer.toString(answer).length();

        for (int i = 0; i < lengthOfSpaces; i++) {
            stringPrepare.append(INDENT);
        }

        stringPrepare.append(VERTICAL_BAR);

        for (int i = 0; i < lengthOfDashes; i++) {
            stringPrepare.append(DASH);
        }

        secondString = String.format("%1$s%2$s", stringPrepare.toString(), LINE_END);

        return secondString;
    }

    private String composeThirdString(int subtrahend, int dividend, int answer) {
        String thirdString = "";
        StringBuilder stringPrepare = new StringBuilder(INDENT);
        int lengthOfDashes = Integer.toString(subtrahend).length();
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();

        for (int i = 0; i < lengthOfDashes; i++) {
            stringPrepare.append(DASH);
        }

        for (int i = 0; i < lengthOfSpaces; i++) {
            stringPrepare.append(INDENT);
        }

        thirdString = String.format("%1$s%2$s%3$s%4$s", stringPrepare.toString(), VERTICAL_BAR, answer, LINE_END);

        return thirdString;
    }

    private String composeDivisionSteps(ArrayList<String> divisionSteps, int dividend, int divisor) {
        ArrayList<String> prepareStrings = new ArrayList<>();
        StringBuilder remainingStrings = new StringBuilder("");
        String indentation = lenghtOfFirstIndent(divisionSteps) + INDENT;
        String minusSignWithIndent = lenghtOfFirstIndent(divisionSteps) + UNDERSCORE;

        divisionSteps.remove(0);
        divisionSteps.remove(0);

        int numberOfLines = divisionSteps.size();
        int remainder = dividend % divisor;
        int iterator = 0;

        if (numberOfLines == 0) {
            indentation = indentation.substring(1);
            prepareStrings.add(indentation + remainder);
        } else {
            for (int i = 0; i < numberOfLines / 2; i++) {

                prepareStrings.add(minusSignWithIndent + divisionSteps.get(i + iterator));
                prepareStrings.add(indentation + divisionSteps.get(i + iterator));
                prepareStrings.addAll(delimeterWithIndentation(indentation, divisionSteps.get(i).length()));
                indentation += INDENT;
                minusSignWithIndent = INDENT + minusSignWithIndent;
                iterator++;
            }

            String lastString = prepareStrings.get(prepareStrings.size() - 1);
            lastString = lastString.trim();
            String delimiterWithIndent = DASH + DASH;
            boolean equals = lastString.equals(delimiterWithIndent);

            if (!equals) {
                indentation = indentation.substring(1);
            }
            prepareStrings.add(indentation + remainder);

        }

        int prepareStringsSize = prepareStrings.size();

        for (int i = 0; i < prepareStringsSize; i++) {
            if (i == prepareStringsSize - 1) {
                remainingStrings.append(prepareStrings.get(i));
            } else {
                remainingStrings.append(prepareStrings.get(i)).append(LINE_END);
            }
        }

        return remainingStrings.toString();
    }

    private String lenghtOfFirstIndent(ArrayList<String> calculatedValues) {
        String indentation;
        int lengthOfFirstNumber = calculatedValues.get(0).length();
        int difference = Integer.parseInt(calculatedValues.get(0)) - Integer.parseInt(calculatedValues.get(1));

        if (lengthOfFirstNumber > Integer.toString(difference).length() || difference == 0) {
            indentation = INDENT;
        } else {
            indentation = "";
        }

        return indentation;
    }

    private ArrayList<String> delimeterWithIndentation(String indentation, int delimeterLenght) {
        ArrayList<String> delimeterWithIndentation = new ArrayList<>();
        StringBuilder delimiterWithIndent = new StringBuilder(indentation);

        for (int i = 0; i < delimeterLenght; i++) {
            delimiterWithIndent.append(DASH);
        }

        delimeterWithIndentation.add(delimiterWithIndent.toString());

        return delimeterWithIndentation;
    }
}
