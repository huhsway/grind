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

        // 3. LCA 판단 및 반환
        // 두 노드(p와 q)가 각각 다른 서브트리에 있는 경우
        if (left != null && right != null) {
            return root; // 현재 노드가 LCA입니다.
        } 
        // 왼쪽 서브트리에서만 찾은 경우
        else if (left != null) {
            return left; // LCA는 왼쪽 서브트리에 있습니다.
        } 
        // 오른쪽 서브트리에서만 찾은 경우
        else {
            return right; // LCA는 오른쪽 서브트리에 있습니다. (right가 null인 경우도 포함)
        }
    }
}