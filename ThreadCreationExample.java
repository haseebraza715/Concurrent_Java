public class ThreadCreationExample {
    public static void runExample() {
        // Using a lambda expression
        Thread lambdaThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Lambda Thread - " + i);
            }
        });

        // Using an anonymous class
        Thread anonymousThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Anonymous Runnable - " + i);
                }
            }
        });

        lambdaThread.start();
        anonymousThread.start();

        try {
            lambdaThread.join();
            anonymousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished ThreadCreationExample.");
    }
}
