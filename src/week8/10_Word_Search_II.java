import java.util.ArrayList;
import java.util.List;

class Solution {
    private TrieNode root;
    private int R, C;
    private List<String> result;
    private final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 1. Trie Node 클래스 정의
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    /**
     * 메인 함수
     */
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return new ArrayList<>();
        }

        R = board.length;
        C = board[0].length;
        result = new ArrayList<>();
        root = new TrieNode();

        // 2. Trie 구축
        for (String word : words) {
            insert(word);
        }

        // 3. 모든 셀에서 DFS 시작
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dfs(board, i, j, root);
            }
        }

        return result;
    }

    /**
     * Trie에 단어를 삽입하는 헬퍼 함수
     */
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.word = word;
    }

    /**
     * DFS를 통해 격자에서 단어를 찾고 백트래킹 수행
     */
    private void dfs(char[][] board, int r, int c, TrieNode parentNode) {
        // 1. 현재 셀의 문자 확인
        char currentChar = board[r][c];
        int index = currentChar - 'a';
        
        // Trie에서 다음 노드 찾기
        TrieNode currentNode = parentNode.children[index];

        // 2. Trie 매칭 실패 (단어의 접두사가 아님)
        if (currentNode == null) {
            return;
        }

        // 3. 단어 발견
        if (currentNode.word != null) {
            result.add(currentNode.word);
            // 중요: 이미 찾은 단어는 다시 찾지 않도록 Trie에서 제거
            // 이렇게 하면 중복 결과가 방지되고 효율성이 높아집니다.
            currentNode.word = null; 
        }

        // 4. 탐색 계속 및 백트래킹 준비
        // 현재 셀을 방문 표시 (다시 사용하지 않도록 임시로 마킹)
        board[r][c] = '#'; 

        // 상하좌우 탐색
        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // 경계 조건 확인
            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                // 방문하지 않은 셀이면서 유효한 문자일 때 (Trie 탐색은 이미 위에서 처리됨)
                if (board[nr][nc] != '#') {
                    dfs(board, nr, nc, currentNode);
                }
            }
        }

        // 5. 백트래킹 (원래 상태로 복원)
        board[r][c] = currentChar; 
        
        // Optional Optimization:
        // 만약 currentNode가 더 이상 자식 노드를 가지지 않으면, Trie에서 이 노드를 제거하여
        // 향후 탐색을 더 빨리 끝낼 수 있도록 최적화할 수 있습니다. (구현 생략)
    }
}