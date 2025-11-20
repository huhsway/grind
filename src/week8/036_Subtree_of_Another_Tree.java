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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        
        // 현재 노드에서 시작하는 트리가 subRoot와 같은지 확인
        if (isSameTree(root, subRoot)) {
            return true;
        }
        
        // 왼쪽 또는 오른쪽 서브트리에서 찾기
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    
    // 두 트리가 완전히 같은지 확인
    private boolean isSameTree(TreeNode p, TreeNode q) {
        // 둘 다 null이면 같음
        if (p == null && q == null) return true;
        
        // 하나만 null이면 다름
        if (p == null || q == null) return false;
        
        // 값이 다르면 다름
        if (p.val != q.val) return false;
        
        // 왼쪽과 오른쪽 서브트리도 모두 같아야 함
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}