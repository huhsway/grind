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
    public boolean isValidBST(TreeNode root) {
        // 처음에는 Long.MIN_VALUE와 Long.MAX_VALUE로 초기 범위를 설정
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValid(TreeNode node, long min, long max) {
        // 1. 기본 케이스: 노드가 null이면 유효한 것으로 간주
        if (node == null) {
            return true;
        }
        
        // 2. 현재 노드 값의 유효성 검사
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        // 3. 재귀 호출: 왼쪽과 오른쪽 서브트리 검사
        // 왼쪽 서브트리: 현재 노드 값(node.val)이 새로운 max가 됨
        boolean leftIsValid = isValid(node.left, min, node.val);
        
        // 오른쪽 서브트리: 현재 노드 값(node.val)이 새로운 min이 됨
        boolean rightIsValid = isValid(node.right, node.val, max);
        
        return leftIsValid && rightIsValid;
    }
}