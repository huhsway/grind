class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (isValid(board, i, j, digit)) {
                            board[i][j] = digit;
                            
                            if (solve(board)) {
                                return true;
                            }
                            
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char digit) {
        // 🎯 통일된 박스 인덱스 계산
        int boxIndex = (row / 3) * 3 + (col / 3);
        
        for (int i = 0; i < 9; i++) {
            // 행 검사
            if (board[row][i] == digit) {
                return false;
            }

            // 열 검사
            if (board[i][col] == digit) {
                return false;
            }

            // 박스 검사 - boxIndex 기반으로 좌표 계산
            int boxRow = 3 * (boxIndex / 3) + (i / 3);
            int boxCol = 3 * (boxIndex % 3) + (i % 3);
            
            if (board[boxRow][boxCol] == digit) {
                return false;
            }
        }
        return true;
    }
}