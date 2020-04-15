package ua.com.foxminded.integerdivision;

public class Runner {

    public static void main(String[] args) {
        int dividend = 75451;
        int divisor = 53;
        IntegerDivision integerDivision = new IntegerDivision();
        
        System.out.println(integerDivision.divideInColumn(dividend, divisor));
    }
}
