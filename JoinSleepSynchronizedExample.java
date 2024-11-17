public class JoinSleepSynchronizedExample {
    private static int sharedCounter = 0;

    public static synchronized void incrementCounter() {
        sharedCounter++;
        System.out.println("Counter: " + sharedCounter);
    }

    public static void runExample() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                incrementCounter();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 interrupted!");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                incrementCounter();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Thread 2 interrupted!");
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished JoinSleepSynchronizedExample.");
    }
}
