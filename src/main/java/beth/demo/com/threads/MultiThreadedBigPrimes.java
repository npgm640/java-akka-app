package beth.demo.com.threads;

public class MultiThreadedBigPrimes {


    public static void main(String[] args) {
        Long start1 = System.currentTimeMillis();
        Results results = new Results();
        Runnable task = new PrimeGenerator(results);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(task);
            t.start();
        }

        Long end = System.currentTimeMillis();
        System.out.println("Time taken " + (end - start1) + "ms.");
    }
}
