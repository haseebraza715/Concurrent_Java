public class LifecycleInterruptExample {
    public static void runExample() {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Working... " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted during sleep!");
            }
        });

        thread.start();

        try {
            Thread.sleep(3000); // Main thread waits for 3 seconds
            thread.interrupt(); // Interrupt the worker thread
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished LifecycleInterruptExample.");
    }
}
