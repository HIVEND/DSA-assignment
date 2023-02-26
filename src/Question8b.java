public class Question8b {
    public static int findKthMissingEvenNumber(int[] nums, int k) {
        int n = nums.length;
        int missingCount = (nums[n-1] - nums[0] + 2) / 2 - n;
        if (k > missingCount) {
            return nums[n-1] + 2 * (k - missingCount);
        }

        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int missing = (nums[mid] - nums[0]) / 2 - mid;
            if (missing >= k) {
                right = mid;
            } else {
                k -= missing;
                left = mid + 1;
            }
        }
        return nums[left-1] + 2 * k;
    }

    public static void main(String[] args) {
        System.out.println(findKthMissingEvenNumber(new int[]{0,2,6,18,22},6));
    }
}