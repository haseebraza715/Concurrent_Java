public class ThreadOperationsDemo {

    public static void main(String[] args) {
        // Create a thread
        Thread thread1 = new Thread(new Task("Task-1", 5));
        Thread thread2 = new Thread(new Task("Task-2", 5));

        // Start the threads
        System.out.println("Starting threads...");
        thread1.start();
        thread2.start();

        // Use join to wait for thread1 to finish
        try {
            System.out.println("Waiting for thread1 to complete...");
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt thread2
        System.out.println("Interrupting thread2...");
        thread2.interrupt();

        System.out.println("Main thread finished.");
    }

    // Task class implementing Runnable
    static class Task implements Runnable {
        private String name;
        private int iterations;

        public Task(String name, int iterations) {
            this.name = name;
            this.iterations = iterations;
        }

        @Override
        public void run() {
            for (int i = 1; i <= iterations; i++) {
                try {
                    System.out.println(name + " - Iteration " + i);
                    Thread.sleep(1000); // Pause for 1 second
                } catch (InterruptedException e) {
                    System.out.println(name + " was interrupted!");
                    return; // Exit gracefully
                }
            }
            System.out.println(name + " has finished.");
        }
    }
}
