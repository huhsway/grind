_/**
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
    // 전체 트리의 최대 경로 합을 추적하는 전역 변수
    // 최소 정수 값으로 초기화하여 어떤 경로 합이든 초기에 업데이트될 수 있도록 함
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // 재귀 탐색 시작
        dfs(root);
        return maxSum;
    }

    /**
     * DFS를 수행하여 다음 두 가지를 계산합니다:
     * 1. 현재 노드를 정점으로 하는 경로의 최대 합을 전역 변수 maxSum에 업데이트.
     * 2. 현재 노드에서 시작하여 한쪽 자식으로만 내려가는 경로의 최대 합을 반환.
     * * @param node 현재 탐색 중인 노드
     * @return 현재 노드에서 한쪽 방향으로 내려가는 최대 경로 합 (음수 제외)
     */
    private int dfs(TreeNode node) {
        // 1. 기저 조건: 노드가 null이면 경로 합은 0
        if (node == null) {
            return 0;
        }

        // 2. 자식 경로의 최대 합 재귀적으로 계산
        // 음수 경로는 합을 감소시키므로, max(0, ...)를 통해 해당 경로를 포함하지 않음
        int leftPath = Math.max(0, dfs(node.left));
        int rightPath = Math.max(0, dfs(node.right));

        // 3. **전체 최대 합 업데이트 (Split Path)**
        // 현재 노드를 경유점으로 하여 왼쪽, 오른쪽을 모두 연결하는 경로 합
        int currentPathSum = node.val + leftPath + rightPath;
        maxSum = Math.max(maxSum, currentPathSum);

        // 4. **부모에게 반환할 값 (Straight Path)**
        // 현재 노드에서 시작하여 부모로 이어질 수 있는 최대 경로 합 (한쪽 방향만)
        return node.val + Math.max(leftPath, rightPath);
    }
}