class Solution {
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {

        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.word = word;
        }

        List<String> result = new ArrayList<>();
        int r = board.length;
        int c = board[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;   
    }

    private void dfs(char[][] board, int y, int x, TrieNode node, List<String> result) {

        if (y < 0 || y >= board.length || x < 0 || x >= board[0].length) {
            return;
        }

        char c = board[y][x];

        if (c == '#' || node.children[c - 'a'] == null) {
            return;
        }

        node = node.children[c - 'a'];

        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        board[y][x] = '#';

        dfs(board, y + 1, x, node, result);
        dfs(board, y - 1, x, node, result);
        dfs(board, y, x + 1, node, result);
        dfs(board, y, x - 1, node, result);

        board[y][x] = c;
    }


}