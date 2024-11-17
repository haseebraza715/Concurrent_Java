public class DemoBasicThreading {
    public static void main(String[] args) {
        System.out.println("Running ClassExtendingThread...");
        ClassExtendingThread.runExample();

        System.out.println("\nRunning ClassImplementingRunnable...");
        ClassImplementingRunnable.runExample();

        System.out.println("\nRunning ThreadCreationExample...");
        ThreadCreationExample.runExample();

        System.out.println("\nRunning JoinSleepSynchronizedExample...");
        JoinSleepSynchronizedExample.runExample();

        System.out.println("\nRunning LifecycleInterruptExample...");
        LifecycleInterruptExample.runExample();
    }
}
