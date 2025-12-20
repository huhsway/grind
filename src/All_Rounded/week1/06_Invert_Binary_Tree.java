/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        
        // 왼쪽, 오른쪽 swap
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // 자식들도 재귀적으로 invert
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
}