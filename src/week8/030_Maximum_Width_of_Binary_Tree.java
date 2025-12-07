class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        int maxWidth = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(root, 0));
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int leftmost = queue.peek().index;  // 이 레벨의 가장 왼쪽 인덱스
            int rightmost = leftmost;
            
            for (int i = 0; i < levelSize; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int index = current.index;
                
                rightmost = index;  // 계속 업데이트
                
                // 왼쪽 자식이 있으면 큐에 추가
                if (node.left != null) {
                    queue.offer(new Pair(node.left, 2 * index));
                }
                
                // 오른쪽 자식이 있으면 큐에 추가
                if (node.right != null) {
                    queue.offer(new Pair(node.right, 2 * index + 1));
                }
            }
            
            // 현재 레벨의 너비 계산
            maxWidth = Math.max(maxWidth, rightmost - leftmost + 1);
        }
        
        return maxWidth;
    }
    
    // 노드와 인덱스를 함께 저장하는 클래스
    private static class Pair {
        TreeNode node;
        int index;
        
        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}