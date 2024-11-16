public class ThreadExample {

    public static void main(String[] args) {
        System.out.println("===== 1. Using a Child Class of Thread =====");
        // Using a child class of Thread
        Thread thread1a = new CustomThread("hello");
        Thread thread1b = new CustomThread("world");
        thread1a.start();
        thread1b.start();

        // Wait for previous threads to finish before starting new ones
        try {
            thread1a.join();
            thread1b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n===== 2. Using a Class that Implements the Runnable Interface =====");
        // Using a class that implements Runnable
        Thread thread2a = new Thread(new PrintingTask("hello"));
        Thread thread2b = new Thread(new PrintingTask("world"));
        thread2a.start();
        thread2b.start();

        try {
            thread2a.join();
            thread2b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n===== 3. Using an Anonymous Class Derived from Thread =====");
        // Using an anonymous class derived from Thread
        Thread thread3a = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.print("hello");
                }
            }
        };
        Thread thread3b = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.print("world");
                }
            }
        };
        thread3a.start();
        thread3b.start();

        try {
            thread3a.join();
            thread3b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n===== 4. Using an Anonymous Class Derived from Runnable =====");
        // Using an anonymous class derived from Runnable
        Runnable task4a = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.print("hello");
                }
            }
        };
        Runnable task4b = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.print("world");
                }
            }
        };
        Thread thread4a = new Thread(task4a);
        Thread thread4b = new Thread(task4b);
        thread4a.start();
        thread4b.start();

        try {
            thread4a.join();
            thread4b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n===== 5. By Passing a Lambda to the Thread Constructor =====");
        // Using a lambda expression to define the thread task
        Thread thread5a = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.print("hello");
            }
        });
        Thread thread5b = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.print("world");
            }
        });
        thread5a.start();
        thread5b.start();
    }
}

// ===== 1. Using a Child Class of Thread =====
class CustomThread extends Thread {
    private String message;

    public CustomThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) { // Reduced loop size for simplicity
            System.out.print(message);
        }
    }
}

// ===== 2. Using a Class that Implements the Runnable Interface =====
class PrintingTask implements Runnable {
    private String message;

    public PrintingTask(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.print(message);
        }
    }
}
