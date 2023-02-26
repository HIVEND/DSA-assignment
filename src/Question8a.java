import java.util.Stack;
class Question8a {
    public static int maxSquareArea(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n + 1];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j <= n; j++) {
                if (j < n) {
                    if (matrix[i][j] == 0) {
                        heights[j]++;
                    } else {
                        heights[j] = 0;
                    }
                }
                while (!stack.isEmpty() && heights[stack.peek()] > heights[j]) {
                    int height = heights[stack.pop()];
                    int width = stack.isEmpty() ? j : j - stack.peek() - 1;
                    maxArea = Math.max(maxArea, Math.min(height, width) * Math.min(height, width));
                }
                stack.push(j);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        System.out.println(maxSquareArea(matrix));
        // Output: 4

    }
}