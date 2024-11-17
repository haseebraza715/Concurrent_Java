public class ClassExtendingThread extends Thread {
    private String message;

    public ClassExtendingThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message + " - " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        }
    }

    public static void runExample() {
        ClassExtendingThread thread1 = new ClassExtendingThread("Thread 1");
        ClassExtendingThread thread2 = new ClassExtendingThread("Thread 2");
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished ClassExtendingThread example.");
    }
}
