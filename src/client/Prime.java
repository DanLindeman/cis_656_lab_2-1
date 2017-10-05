package client;

import java.util.ArrayList;

public class Prime {

    private Integer min;
    private Integer max;

    public Prime (Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public ArrayList<Integer> execute() {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = this.min; i < max; i++) {
            boolean isPrimeNumber = true;

            // check to see if the number is prime
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }

            // print the number if prime
            if (isPrimeNumber) {
                primes.add(i);
            }
        }
        return primes;
    }
}
