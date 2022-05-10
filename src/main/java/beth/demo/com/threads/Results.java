package beth.demo.com.threads;

import java.math.BigInteger;
import java.util.SortedSet;
import java.util.TreeSet;

public class Results {
    private SortedSet<BigInteger> primes;

    public Results() {
        primes = new TreeSet<>();
    }

    public SortedSet<BigInteger> getPrimes() {
        return primes;
    }

    public void setPrimes(SortedSet<BigInteger> primes) {
        this.primes = primes;
    }

    public int getSize() {
        synchronized (this) {
            return primes.size();
        }
    }

    public void print() {
        synchronized (this) {
            primes.forEach(System.out::println);
        }
    }

    public void addPrime(BigInteger prime) {
        synchronized (this) {
            prime.add(prime);
        }
    }
}
