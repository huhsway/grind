class Solution {

    private static class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int leftmost = queue.peek().index;
            int rightmost = leftmost;

            for (int i = 0; i < levelSize; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int index = current.index - leftmost;  // 오버플로우 방지

                rightmost = index;

                if (node.left != null) {
                    queue.offer(new Pair(node.left, 2 * index));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, 2 * index + 1));
                }
            }

            maxWidth = Math.max(maxWidth, rightmost + 1);
        }

        return maxWidth;
    }
}