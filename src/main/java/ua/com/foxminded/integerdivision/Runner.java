package ua.com.foxminded.integerdivision;

public class Runner {

    public static void main(String[] args) {
        int dividend = 512;
        int divisor = 8;
        IntegerDivision integerDivision = new IntegerDivision();
        
        System.out.println(integerDivision.divideInColumn(dividend, divisor));
    }
}
