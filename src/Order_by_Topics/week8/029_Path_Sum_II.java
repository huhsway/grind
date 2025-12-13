import java.util.ArrayList;
import java.util.List;

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
    
    // 최종 결과를 저장할 리스트
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return result;
        }
        
        // 현재 경로를 기록할 리스트
        List<Integer> currentPath = new ArrayList<>();
        
        // DFS 시작
        findPaths(root, targetSum, currentPath);
        
        return result;
    }

    /**
     * 재귀적으로 경로를 탐색하는 DFS 함수
     * @param node 현재 탐색 중인 노드
     * @param remainingSum 목표 합에서 현재까지의 합을 뺀 남은 값
     * @param currentPath 현재 루트부터 노드까지의 경로
     */
    private void findPaths(TreeNode node, int remainingSum, List<Integer> currentPath) {
        if (node == null) {
            return;
        }

        // 1. 현재 노드를 경로에 추가
        currentPath.add(node.val);
        
        // 2. 남은 목표 합 업데이트
        int newRemainingSum = remainingSum - node.val;

        // 3. 리프 노드 확인 및 성공 조건
        if (node.left == null && node.right == null) {
            // 현재 노드가 리프 노드이고, 남은 합이 0이면 성공
            if (newRemainingSum == 0) {
                // 주의: currentPath 자체를 추가하면 안 되고, 복사본을 추가해야 합니다.
                // currentPath는 백트래킹 시 변경되기 때문입니다.
                result.add(new ArrayList<>(currentPath));
            }
        } else {
            // 4. 왼쪽 및 오른쪽 자식 노드 탐색
            if (node.left != null) {
                findPaths(node.left, newRemainingSum, currentPath);
            }
            if (node.right != null) {
                findPaths(node.right, newRemainingSum, currentPath);
            }
        }

        // 5. 백트래킹
        // 재귀 호출에서 돌아오면, 현재 노드를 경로에서 제거하여 다음 탐색을 준비합니다.
        currentPath.remove(currentPath.size() - 1);
    }
}