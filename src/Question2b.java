    class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
public class Question2b {
    public static int minServiceCenters(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int numCenters = 0;

        if (root.left != null) {
            numCenters += minServiceCenters(root.left.left) + minServiceCenters(root.left.right) + 1;
        }

        if (root.right != null) {
            numCenters += minServiceCenters(root.right.left) + minServiceCenters(root.right.right) + 1;
        }

        return Math.min(numCenters, 1 + minServiceCenters(root.left) + minServiceCenters(root.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.left = null;
        root.left.right = new TreeNode(0);
        root.right.left = null;
        root.right.right = null;
        root.left.right.left = new TreeNode(0);

        Question2b Qn = new Question2b();

        int minCenters = Question2b.minServiceCenters(root);

        System.out.println("Minimum number of service centers required: " + minCenters);
    }
}
