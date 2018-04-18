package tests;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i <= 10; i++) {
            System.out.println(fibonacci(i));
        }
    }

    static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
