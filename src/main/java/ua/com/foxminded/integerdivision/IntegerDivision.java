package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivision {
    private static String UNDERSCORE = "_";
    private static String VERTICAL_BAR = "|";
    private static String INDENT = " ";
    private static String DASH = "-";
    private static String LINE_END = "\n";

    public String divideInColumn(int dividend, int divisor) {
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        List<String> divisionSteps = calculateDivisionSteps(dividend, divisor);

        return composeDivisionResult(divisionSteps, dividend, divisor);
    }

    private List<String> calculateDivisionSteps(int dividend, int divisor) {

        if ((divisor == 0) || (divisor > dividend)) {
            throw new IllegalArgumentException("Divisor cannot be 0 or bigger than dividend.");
        }

        List<String> calculatedSteps = new ArrayList<>();
        char[] digitsDividend = Integer.toString(dividend).toCharArray();
        int amountNumbersToDivide = digitsDividend.length - 1;
        int number = Character.getNumericValue(digitsDividend[0]);
        int numberIndex = 1;
        int remainder = 0;
        int difference = 0;

        do {

            if (dividend == divisor) {
                calculatedSteps.add(Integer.toString(dividend));
                calculatedSteps.add(Integer.toString(divisor));
                break;
            }

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

    private String composeDivisionResult(List<String> divisionSteps, int dividend, int divisor) {

        int answer = dividend / divisor;
        StringBuilder output = new StringBuilder("");

        output.append(composeFirstString(dividend, divisor));
        output.append(composeSecondString(Integer.parseInt(divisionSteps.get(1)), dividend, divisor, answer));
        output.append(composeThirdString(Integer.parseInt(divisionSteps.get(1)), dividend, answer));
        output.append(composeDivisionSteps(divisionSteps, dividend, divisor));

        return output.toString();
    }

    private String composeFirstString(int dividend, int divisor) {

        return String.format("%1$s%2$s%3$s%4$s%5$s", UNDERSCORE, dividend, VERTICAL_BAR, divisor, LINE_END);
    }

    private String composeSecondString(int subtrahend, int dividend, int divisor, int answer) {
        StringBuilder stringPrepare = new StringBuilder(INDENT + subtrahend);
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();
        int lengthOfDashes = Integer.toString(answer).length();
        if (Integer.toString(divisor).length() > Integer.toString(answer).length()) {
            lengthOfDashes = Integer.toString(divisor).length();
        }

        for (int i = 0; i < lengthOfSpaces; i++) {
            stringPrepare.append(INDENT);
        }

        stringPrepare.append(VERTICAL_BAR);

        for (int i = 0; i < lengthOfDashes; i++) {
            stringPrepare.append(DASH);
        }

        return String.format("%1$s%2$s", stringPrepare.toString(), LINE_END);
    }

    private String composeThirdString(int subtrahend, int dividend, int answer) {
        StringBuilder stringPrepare = new StringBuilder(INDENT);
        int lengthOfDashes = Integer.toString(subtrahend).length();
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();

        for (int i = 0; i < lengthOfDashes; i++) {
            stringPrepare.append(DASH);
        }

        for (int i = 0; i < lengthOfSpaces; i++) {
            stringPrepare.append(INDENT);
        }

        return String.format("%1$s%2$s%3$s%4$s", stringPrepare.toString(), VERTICAL_BAR, answer, LINE_END);
    }

    private String composeDivisionSteps(List<String> divisionSteps, int dividend, int divisor) {
        List<String> prepareStrings = new ArrayList<>();
        StringBuilder result = new StringBuilder("");
        String indentation = calculatesFirstIndent(divisionSteps) + INDENT;
        String minusSignWithIndent = calculatesFirstIndent(divisionSteps) + UNDERSCORE;

        divisionSteps.remove(0);
        divisionSteps.remove(0);

        int numberOfLines = divisionSteps.size();
        int remainder = dividend % divisor;
        int iterator = 0;

        if (numberOfLines == 0) {
            indentation = indentation.substring(1);

            if (dividend == divisor) {
                for (int i = 0; i < Integer.toString(divisor).length() - 1; i++) {
                    indentation += INDENT;
                }
            }

            prepareStrings.add(indentation + remainder);

        } else {

            for (int i = 0; i < numberOfLines / 2; i++) {

                prepareStrings.add(minusSignWithIndent + divisionSteps.get(i + iterator));
                iterator++;
                prepareStrings.add(indentation + divisionSteps.get(i + iterator));
                prepareStrings.addAll(delimeterWithIndentation(indentation, divisionSteps.get(i).length()));
                indentation += INDENT;
                minusSignWithIndent = INDENT + minusSignWithIndent;

            }

            String lastString = prepareStrings.get(prepareStrings.size() - 1);
            int lastStringLength = lastString.length();
            int remainderLength = Integer.toString(remainder).length();
            indentation = calculatesIndentation(lastStringLength - remainderLength);
            prepareStrings.add(indentation + remainder);

        }

        int prepareStringsSize = prepareStrings.size();

        for (int i = 0; i < prepareStringsSize; i++) {
            if (i == prepareStringsSize - 1) {
                result.append(prepareStrings.get(i));
            } else {
                result.append(prepareStrings.get(i)).append(LINE_END);
            }
        }
        return result.toString();
    }

    private String calculatesIndentation(int requiredLength) {
        StringBuilder indentation = new StringBuilder("");
        for (int i = 0; i < requiredLength; i++) {
            indentation.append(INDENT);
        }

        return indentation.toString();
    }

    private String calculatesFirstIndent(List<String> calculatedValues) {
        String indentation = "";
        int lengthOfFirstNumber = calculatedValues.get(0).length();
        int difference = Integer.parseInt(calculatedValues.get(0)) - Integer.parseInt(calculatedValues.get(1));

        if (lengthOfFirstNumber > Integer.toString(difference).length() || difference == 0) {
            indentation = INDENT;
        }

        return indentation;
    }

    private List<String> delimeterWithIndentation(String indentation, int delimeterLength) {
        List<String> delimeterWithIndentation = new ArrayList<>();
        StringBuilder delimiterWithIndent = new StringBuilder(indentation);

        for (int i = 0; i < delimeterLength; i++) {
            delimiterWithIndent.append(DASH);
        }

        delimeterWithIndentation.add(delimiterWithIndent.toString());

        return delimeterWithIndentation;
    }
}
