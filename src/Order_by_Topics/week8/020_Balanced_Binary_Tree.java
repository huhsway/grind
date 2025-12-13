class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode node) {
        // 1. 재귀의 기본 조건: 노드가 null이면 높이는 0
        if (node == null) {
            return 0;
        }

        // 2. 왼쪽 서브트리 높이 계산 및 불균형 여부 확인
        int leftHeight = height(node.left);
        if (leftHeight == -1) {
            return -1; // 왼쪽 서브트리가 불균형이면 -1 반환
        }

        // 3. 오른쪽 서브트리 높이 계산 및 불균형 여부 확인
        int rightHeight = height(node.right);
        if (rightHeight == -1) {
            return -1; // 오른쪽 서브트리가 불균형이면 -1 반환
        }

        // 4. 현재 노드의 왼쪽/오른쪽 서브트리 높이 차이 확인
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // 높이 차이가 1을 초과하면 -1 반환
        }

        // 5. 균형이 맞으면 현재 노드의 높이 반환
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
