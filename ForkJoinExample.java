import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start, end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 10) { // Small enough to compute directly
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);
            leftTask.fork(); // Start leftTask asynchronously
            int rightResult = rightTask.compute(); // Compute rightTask synchronously
            int leftResult = leftTask.join(); // Wait for leftTask and get result
            return leftResult + rightResult;
        }
    }
}

public class ForkJoinExample {
    public static void main(String[] args) {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) array[i] = i;
        ForkJoinPool pool = new ForkJoinPool();
        int sum = pool.invoke(new SumTask(array, 0, array.length));
        System.out.println("Sum: " + sum);
    }
}
