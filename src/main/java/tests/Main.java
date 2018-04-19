package tests;

import java.math.BigInteger;
import java.util.Random;

public class Main {

//    public static void main(String[] args) {
//        System.out.println(fibonacci(10));
//    }
//
//    static long fibonacci(long n) {
//        if (n <= 1) {
//            return n;
//        } else {
//            return fibonacci(n - 1) + fibonacci(n - 2);
//        }
//    }

    public static void main(String[] args) {
//        final int n = 10000;
//        Fibonacci fibonacci = new Fibonacci();
//
//        Stream.generate(fibonacci::next)
//                .limit(n)
//                .forEach(System.out::println);
        Random random = new Random();
        System.out.println(random.nextInt(13) + 1);

    }


}

class Fibonacci {
    private BigInteger next = new BigInteger("1");
    private BigInteger current = new BigInteger("1");
    private int count = 1;

    FibonacciNumber next() {
        FibonacciNumber fibonacciNumber = new FibonacciNumber(count++, current);
        BigInteger previous = current;
        current = next;
        next = current.add(previous);
        return fibonacciNumber;
    }
}

class FibonacciNumber {
    private final int count;
    private final BigInteger value;

    FibonacciNumber(int count, BigInteger value) {
        this.count = count;
        this.value = value;
    }

    @Override
    public String toString() {
        return count + ": " + value.toString();
    }
}