class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 9x9 보드를 순회하면서 각 숫자가 유효한지 검사
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    char digit = board[i][j];
                    
                    // 임시로 비워서 자기 자신과의 비교를 피함
                    board[i][j] = '.';
                    
                    // 유효성 검사
                    if (!isValid(board, i, j, digit)) {
                        return false;
                    }
                    
                    // 원래대로 복원
                    board[i][j] = digit;
                }
            }
        }
        return true;
    }
    
    // (row, col) 위치에 digit을 놓을 수 있는지 검사
    private boolean isValid(char[][] board, int row, int col, char digit) {
        for (int i = 0; i < 9; i++) {
            // 1. 행 검사
            if (board[row][i] == digit) {
                return false;
            }
            
            // 2. 열 검사
            if (board[i][col] == digit) {
                return false;
            }
            
            // 3. 3x3 박스 검사
            int boxRow = 3 * (row / 3) + (i / 3);
            int boxCol = 3 * (col / 3) + (i % 3);
            
            if (board[boxRow][boxCol] == digit) {
                return false;
            }
        }
        return true;
    }
}