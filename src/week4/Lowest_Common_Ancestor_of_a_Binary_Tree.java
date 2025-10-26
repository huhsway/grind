/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. 재귀 종료 조건
        // 현재 노드가 null이거나 p 또는 q와 같으면 현재 노드를 반환합니다.
        if (root == null || root == p || root == q) {
            return root;
        }

        // 2. 왼쪽과 오른쪽 서브트리 탐색
        // 왼쪽 서브트리에서 LCA를 찾습니다.
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 오른쪽 서브트리에서 LCA를 찾습니다.
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null && right == null) {
            return left;
        } else if (left == null && right != null) {
            return right;
        } else {  // left == null && right == null
            return null;
        }
    }
}