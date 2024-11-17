public class ClassImplementingRunnable implements Runnable {
    private String message;

    public ClassImplementingRunnable(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message + " - " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Runnable interrupted!");
            }
        }
    }

    public static void runExample() {
        Thread thread1 = new Thread(new ClassImplementingRunnable("Runnable 1"));
        Thread thread2 = new Thread(new ClassImplementingRunnable("Runnable 2"));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished ClassImplementingRunnable example.");
    }
}
