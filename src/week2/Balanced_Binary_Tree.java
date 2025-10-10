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
    public boolean isBalanced(TreeNode root) {
        // checkHeight 가 -1 이면 불균형, 그렇지 않으면 균형 트리
        return checkHeight(root) != -1;
    }
    
    // 이 메서드는 node 의 서브트리 높이를 반환하거나,
    // 불균형이면 -1 을 반환하는 방식으로 동작
    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            // 왼쪽 서브트리에 이미 불균형이 발견되었음
            return -1;
        }
        
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            // 오른쪽 서브트리에 이미 불균형이 발견되었음
            return -1;
        }
        
        // 현재 노드 기준으로 좌우 높이 차이 확인
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;  // 불균형 신호
        }
        
        // 균형이면 높이 정보 반환
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
