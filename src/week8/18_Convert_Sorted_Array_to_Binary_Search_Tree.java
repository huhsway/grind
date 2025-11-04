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
    
    // 메인 메서드: 배열 전체를 대상으로 재귀 시작
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return convert(nums, 0, nums.length - 1);
    }
    
    /**
     * 정렬된 배열의 [left, right] 범위에 해당하는 BST를 재귀적으로 생성합니다.
     */
    private TreeNode convert(int[] nums, int left, int right) {
        // 1. 기저 조건: 유효한 범위가 없으면 null 반환
        if (left > right) {
            return null;
        }

        // 2. 중앙 인덱스 계산 (루트 선택)
        // 오버플로우 방지를 위해 mid = (left + right) / 2 대신 사용
        int mid = left + (right - left) / 2;
        
        // 3. 현재 서브트리의 루트 노드 생성
        TreeNode root = new TreeNode(nums[mid]);

        // 4. 재귀 호출: 왼쪽 부분 배열로 왼쪽 서브트리 생성
        // 범위: [left, mid - 1]
        root.left = convert(nums, left, mid - 1);

        // 5. 재귀 호출: 오른쪽 부분 배열로 오른쪽 서브트리 생성
        // 범위: [mid + 1, right]
        root.right = convert(nums, mid + 1, right);

        // 6. 현재 루트 노드 반환
        return root;
    }
}