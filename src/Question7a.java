import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Question7a {

    public static int[][] multiply(int[][] a, int[][] b, int numThreads) throws InterruptedException {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }
        int n = a.length;
        int m = b[0].length;
        int[][] c = new int[n][m];
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                executor.submit(new Task(i, j, a, b, c));
            }
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        return c;
    }

    private static class Task implements Runnable {
        private int i;
        private int j;
        private int[][] a;
        private int[][] b;
        private int[][] c;

        public Task(int i, int j, int[][] a, int[][] b, int[][] c) {
            this.i = i;
            this.j = j;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public void run() {
            int sum = 0;
            for (int k = 0; k < a[0].length; k++) {
                sum += a[i][k] * b[k][j];
            }
            c[i][j] = sum;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] b = {
                {10, 11, 12},
                {13, 14, 15},
                {16, 17, 18}
        };
        int[][] c = multiply(a, b, 4);
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }
}
