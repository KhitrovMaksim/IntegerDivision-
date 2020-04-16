package ua.com.foxminded.integerdivision;

public class Runner {

    public static void main(String[] args) {
        int dividend = 100;
        int divisor = 100;
        IntegerDivision integerDivision = new IntegerDivision();
        
        System.out.println(integerDivision.divideInColumn(dividend, divisor));
    }
}
