class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 둘 다 null이면 같은 트리
        if (p == null && q == null) {
            return true;
        }
        
        // 하나만 null이면 다른 트리
        if (p == null || q == null) {
            return false;
        }
        
        // 현재 노드의 값이 다르면 다른 트리
        if (p.val != q.val) {
            return false;
        }
        
        // 왼쪽 서브트리와 오른쪽 서브트리가 모두 같아야 함
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}