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
    public int pathSum(TreeNode root, int targetSum) {
        // key: prefix sum, value: 그 prefix sum이 나온 횟수
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // 빈 경로를 위해
        
        return dfs(root, 0L, targetSum, prefixSumCount);
    }
    
    private int dfs(TreeNode node, long currentSum, int targetSum, 
                    Map<Long, Integer> prefixSumCount) {
        if (node == null) return 0;
        
        // 현재까지의 누적 합
        currentSum += node.val;
        
        // currentSum - targetSum이 이전에 나왔다면,
        // 그 지점부터 현재까지의 합이 targetSum이 됨
        int count = prefixSumCount.getOrDefault(currentSum - targetSum, 0);
        
        // 현재 prefix sum을 맵에 추가
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        
        // 왼쪽과 오른쪽 서브트리 탐색
        count += dfs(node.left, currentSum, targetSum, prefixSumCount);
        count += dfs(node.right, currentSum, targetSum, prefixSumCount);
        
        // 백트래킹: 현재 경로에서 벗어날 때 제거
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);
        
        return count;
    }
}