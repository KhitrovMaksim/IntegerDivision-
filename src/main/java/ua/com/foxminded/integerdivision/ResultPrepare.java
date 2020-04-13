package ua.com.foxminded.integerdivision;

import java.util.ArrayList;

public class ResultPrepare {

    private ArrayList<String> composeFirstString(int dividend, int divisor) {
        ArrayList<String> firstString = new ArrayList<>();

        firstString.add("_" + Integer.toString(dividend) + "|" + Integer.toString(divisor));

        return firstString;
    }

    private ArrayList<String> composeSecondString(int subtrahend, int dividend, int answer) {
        ArrayList<String> secondString = new ArrayList<>();
        StringBuilder stringPrepare = new StringBuilder(" " + subtrahend);
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();
        int lengthOfDashes = Integer.toString(answer).length();

        for (int i = 0; i < lengthOfSpaces; i++) {
            stringPrepare.append(" ");
        }

        stringPrepare.append("|");

        for (int i = 0; i < lengthOfDashes; i++) {
            stringPrepare.append("-");
        }

        secondString.add(stringPrepare.toString());

        return secondString;
    }

    private ArrayList<String> composeThirdString(int subtrahend, int dividend, int answer) {
        ArrayList<String> thirdString = new ArrayList<>();
        StringBuilder stringPrepare = new StringBuilder(" ");
        int lengthOfDashes = Integer.toString(subtrahend).length();
        int lengthOfSpaces = Integer.toString(dividend).length() - Integer.toString(subtrahend).length();

        for (int i = 0; i < lengthOfDashes; i++) {
            stringPrepare.append("-");
        }

        for (int i = 0; i < lengthOfSpaces; i++) {
            stringPrepare.append(" ");
        }

        thirdString.add(stringPrepare.append("|" + answer).toString());

        return thirdString;
    }

    private ArrayList<String> delimeterWithIndentation(String indentation, int delimeterLenght) {
        ArrayList<String> delimeterWithIndentation = new ArrayList<>();
        String delimiter = indentation;

        for (int i = 0; i < delimeterLenght; i++) {
            delimiter += "-";
        }

        delimeterWithIndentation.add(delimiter);

        return delimeterWithIndentation;
    }

    private String lenghtOfFirstIndent(ArrayList<String> calculatedValues) {
        String indentation;
        int lengthOfFirstNumber = calculatedValues.get(0).length();
        int difference = Integer.parseInt(calculatedValues.get(0)) - Integer.parseInt(calculatedValues.get(1));

        if (lengthOfFirstNumber > Integer.toString(difference).length()) {
            indentation = " ";
        } else {
            indentation = "";
        }

        return indentation;

    }

    private ArrayList<String> composeRemainingStrings(ArrayList<String> input) {
        ArrayList<String> remainingStrings = new ArrayList<>();
        int inputIterator = 2;
        int numberOfLines = input.size();
        String indentation = lenghtOfFirstIndent(input) + " ";
        String minusSign = lenghtOfFirstIndent(input) + "_";

        for (int i = 0; i <= ((numberOfLines - 3) / 2); i++) {

            if (inputIterator == (numberOfLines - 1)) {
                remainingStrings.add(indentation + input.get(inputIterator));
                break;
            } else {
                remainingStrings.add(minusSign + input.get(inputIterator));
                inputIterator++;
            }

            remainingStrings.add(indentation + input.get(inputIterator));
            remainingStrings.addAll(delimeterWithIndentation(indentation, input.get(inputIterator).length()));

            indentation += " ";
            minusSign = " " + minusSign;
            inputIterator++;
        }

        return remainingStrings;
    }

    public ArrayList<String> composeStrings(ArrayList<String> input, int dividend, int divisor) {

        int answer = dividend / divisor;
        ArrayList<String> output = new ArrayList<>();

        output.addAll(composeFirstString(dividend, divisor));
        output.addAll(composeSecondString(Integer.parseInt(input.get(1)), dividend, answer));
        output.addAll(composeThirdString(Integer.parseInt(input.get(1)), dividend, answer));
        output.addAll(composeRemainingStrings(input));

        return output;
    }
}
