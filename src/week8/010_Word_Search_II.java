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

    private void dfs(char[][] board, int nr, int nc, TrieNode node, List<String> result) {

        if (nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) {
            return;
        }

        char c = board[nr][nc];

        if (c == '#' || node.children[c - 'a'] == null) {
            return;
        }

        node = node.children[c - 'a'];

        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        board[nr][nc] = '#';

        dfs(board, nr + 1, nc, node, result);
        dfs(board, nr - 1, nc, node, result);
        dfs(board, nr, nc + 1, node, result);
        dfs(board, nr, nc - 1, node, result);

        board[nr][nc] = c;
    }


}