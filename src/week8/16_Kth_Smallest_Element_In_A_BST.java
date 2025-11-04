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
    private int count = 0;
    private int result = -1; // 결과를 저장할 변수

    public int kthSmallest(TreeNode root, int k) {
        count = 0; // 카운터를 초기화
        inorderTraversal(root, k);
        return result;
    }

    /**
     * 중위 순회 (Inorder Traversal)를 수행하는 재귀 함수
     * @param node 현재 탐색 중인 노드
     * @param k 찾고자 하는 순서
     */
    private void inorderTraversal(TreeNode node, int k) {
        // 기저 조건: 노드가 null이거나 이미 결과를 찾았을 경우
        if (node == null || result != -1) {
            return;
        }

        // 1. 왼쪽 자식 탐색
        inorderTraversal(node.left, k);

        // 결과가 이미 설정되었다면 추가적인 연산 없이 반환
        if (result != -1) {
            return;
        }

        // 2. 현재 노드 방문 (중위 순회 핵심)
        count++;
        if (count == k) {
            result = node.val; // k번째 원소 찾음
            return;
        }

        // 3. 오른쪽 자식 탐색
        inorderTraversal(node.right, k);
    }
}