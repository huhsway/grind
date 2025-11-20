/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 왼쪽 서브트리의 최대 깊이 계산
        int leftDepth = dfs(node.left);
        // 오른쪽 서브트리의 최대 깊이 계산
        int rightDepth = dfs(node.right);

        // 현재 노드를 지나는 경로의 길이와 현재까지의 최대 지름을 비교하여 갱신
        diameter = Math.max(diameter, leftDepth + rightDepth);

        // 현재 노드를 루트로 하는 서브트리의 최대 깊이를 반환
        return Math.max(leftDepth, rightDepth) + 1;
    }
}