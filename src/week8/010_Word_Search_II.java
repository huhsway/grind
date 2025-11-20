class Solution {
    // Trie 노드 정의
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // 단어의 끝을 표시
    }
    
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        
        // 1. Trie 구축
        TrieNode root = buildTrie(words);
        
        // 2. 보드의 모든 셀에서 DFS 시작
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    // Trie 구축
    private TrieNode buildTrie(String[] words) {
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
            node.word = word; // 단어의 끝 표시
        }
        
        return root;
    }
    
    // DFS + Backtracking
    private void dfs(char[][] board, int row, int col, TrieNode node, List<String> result) {
        // 범위 체크
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        
        char c = board[row][col];
        
        // 이미 방문했거나 Trie에 없는 문자
        if (c == '#' || node.children[c - 'a'] == null) {
            return;
        }
        
        // 다음 노드로 이동
        node = node.children[c - 'a'];
        
        // 단어를 찾았을 때
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // 중복 방지
        }
        
        // 현재 셀 방문 표시
        board[row][col] = '#';
        
        // 4방향 탐색
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            dfs(board, newRow, newCol, node, result);
        }
        
        // 백트래킹: 원래 문자로 복원
        board[row][col] = c;
    }
}