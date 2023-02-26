import java.util.*;

public class Question5a
{
    public static int[][] getBorder(int[][] height) {
        int n = height.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = height[i][0], x2 = height[i][1], h = height[i][2];
            map.put(x1, Math.max(map.getOrDefault(x1, 0), h));
            map.put(x2, Math.max(map.getOrDefault(x2, 0), 0));
        }
        List<int[]> res = new ArrayList<>();
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = entry.getKey(), h = entry.getValue();
            if (max != h) {
                res.add(new int[] {x, h});
                max = h;
            }
        }
        int[][] result = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int arr [][]={{1, 0, 10}, {2, 5, 15}, {5, 8, 12}, {9, 11, 1}, {11, 13, 15}};
        System.out.println(Arrays.deepToString(getBorder(arr)));
    }
}