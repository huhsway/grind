class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    // 백트래킹으로 스도쿠 풀기
    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 빈 칸을 찾으면
                if (board[i][j] == '.') {
                    
                    // 1부터 9까지 시도
                    for (char digit = '1'; digit <= '9'; digit++) {
                        
                        // 유효한지 검사
                        if (isValid(board, i, j, digit)) {
                            
                            // 시도: 숫자를 놓고
                            board[i][j] = digit;
                            
                            // 재귀: 다음 칸으로
                            if (solve(board)) {
                                return true;  // 성공!
                            }
                            
                            // 백트래킹: 실패하면 되돌리기
                            board[i][j] = '.';
                        }
                    }
                    
                    // 1~9 모두 안 되면 이전 선택이 잘못된 것
                    return false;
                }
            }
        }
        
        // 모든 칸을 채웠으면 완성!
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