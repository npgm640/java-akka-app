package beth.demo.com.threads;

import java.math.BigInteger;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/*
* create a sorted set of 20 probable prime big integers
 */
public class SingleThreadedBigPrimes {
    public static void main(String[] args) {

        Long start = System.currentTimeMillis();
        SortedSet<BigInteger> primes = new TreeSet<>();

        while (primes.size() < 20) {
            //creating a new random number between 0 to 2000-1
            BigInteger bigInteger = new BigInteger(2000, new Random());
            //System.out.println("The integer is " + bigInteger);
            primes.add(bigInteger.nextProbablePrime());
            //System.out.println("The next probable prime is " + bigInteger.nextProbablePrime());
        }
        Long end = System.currentTimeMillis();
        System.out.println(primes);
        System.out.println("The time taken was " + (end - start) + " ms. ");

    }
}
