
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        boolean result = new Solution().isSymmetric(root);
        System.out.println(result);
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> leftStack = new Stack<TreeNode>();
        leftStack.add(root);
        leftStack.add(root);


        while(!leftStack.isEmpty()) {

            TreeNode left = leftStack.pop();
            TreeNode right = leftStack.pop();

            if(left == null && right != null
                    || left != null && right == null) {
                return false;
            }
            if(left == null && right == null) {
                continue;
            }

            if(left.val != right.val) {
                return false;
            }

            leftStack.push(right.right);
            leftStack.push(left.left);
            leftStack.push(left.right);
            leftStack.push(right.left);;
        }
        int[] a = new int[]{};
        a.clone();

        return true;
    }
}