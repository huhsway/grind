class Solution {
    public boolean exist(char[][] board, String word) {

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (search(y, x, board, word, 0))
                    return true;
            }
        }

        return false;

    }

    public boolean search(int y, int x, char[][] board, String word, int depth) {
        if (depth == word.length()) return true;

        if (y < 0 || x < 0 || y >= board.length || x >= board[0].length) return false;
        if (board[y][x] != word.charAt(depth)) return false;

        char temp = board[y][x]; // 현재 위치의 문자를 임시로 저장
        board[y][x] = '\0'; // 현재 위치를 방문 표시

        boolean found = search(y + 1, x, board, word, depth + 1)
                || search(y - 1, x, board, word, depth + 1)
                || search(y, x + 1, board, word, depth + 1)
                || search(y, x - 1, board, word, depth + 1);

        board[y][x] = temp; // 임시로 저장한 문자로 원래 상태로 복원
        return found;
    }
}