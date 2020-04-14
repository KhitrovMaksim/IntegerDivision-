package ua.com.foxminded.integerdivision;

public class Runner {

    public static void main(String[] args) {
        int dividend = 1001;
        int divisor = 1;
        IntegerDivision integerDivision = new IntegerDivision();
        
        System.out.println(integerDivision.divideInColumn(dividend, divisor));
    }
}
