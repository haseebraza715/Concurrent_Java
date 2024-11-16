public class ThreadHW {

    // Static variable to store the total sum
    private static long totalSum = 0;

    public static void main(String[] args) {
        // Single-threaded calculation
        long singleThreadStart = System.nanoTime();
        long singleThreadSum = calculateSum(1, 1_000_000_000);
        long singleThreadElapsed = System.nanoTime() - singleThreadStart;

        System.out.println("Single-threaded Sum: " + singleThreadSum);
        System.out.println("Single-threaded Time: " + (singleThreadElapsed / 1_000_000) + " ms");

        // Multi-threaded calculation
        long multiThreadStart = System.nanoTime();

        // Create 10 threads for different intervals
        Thread t1 = new Thread(new Tasks(1, 100_000_000));
        Thread t2 = new Thread(new Tasks(100_000_001, 200_000_000));
        Thread t3 = new Thread(new Tasks(200_000_001, 300_000_000));
        Thread t4 = new Thread(new Tasks(300_000_001, 400_000_000));
        Thread t5 = new Thread(new Tasks(400_000_001, 500_000_000));
        Thread t6 = new Thread(new Tasks(500_000_001, 600_000_000));
        Thread t7 = new Thread(new Tasks(600_000_001, 700_000_000));
        Thread t8 = new Thread(new Tasks(700_000_001, 800_000_000));
        Thread t9 = new Thread(new Tasks(800_000_001, 900_000_000));
        Thread t10 = new Thread(new Tasks(900_000_001, 1_000_000_000));

        // Start all threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long multiThreadElapsed = System.nanoTime() - multiThreadStart;

        // Print the multi-threaded results
        System.out.println("Multi-threaded Sum: " + totalSum);
        System.out.println("Multi-threaded Time: " + (multiThreadElapsed / 1_000_000) + " ms");
    }

    // Method for single-threaded calculation
    private static long calculateSum(long start, long end) {
        long sum = 0;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }

    // Task class for multi-threaded calculation
    static class Tasks implements Runnable {
        long startNumber;
        long endNumber;

        public Tasks(long startNumber, long endNumber) {
            this.startNumber = startNumber;
            this.endNumber = endNumber;
        }

        @Override
        public void run() {
            long sum = 0;
            for (long i = startNumber; i <= endNumber; i++) {
                sum += i;
            }
            totalSum += sum;
        }
    }
}
