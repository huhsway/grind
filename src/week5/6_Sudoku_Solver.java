class Solution {
    /**
     * 스도쿠 보드를 푸는 메인 메서드.
     * 주어진 보드는 in-place(제자리)로 변경됩니다.
     */
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    /**
     * 재귀적으로 빈 칸을 찾아 숫자를 채우는 백트래킹 함수.
     * @param board 스도쿠 보드
     * @return 유효한 해답을 찾았으면 true, 아니면 false
     */
    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 빈 칸을 찾으면 시도 시작
                if (board[i][j] == '.') {
                    
                    // '1'부터 '9'까지 모든 숫자를 시도
                    for (char digit = '1'; digit <= '9'; digit++) {
                        
                        // 1. 유효성 검사
                        if (isValid(board, i, j, digit)) {
                            
                            // 2. 시도: 숫자를 채우고 다음 칸 재귀 호출
                            board[i][j] = digit;
                            
                            if (solve(board)) {
                                // 3. 성공: 해답을 찾았으므로 true 반환
                                return true;
                            } else {
                                // 4. 실패 (백트래킹): 되돌아가기
                                board[i][j] = '.'; 
                            }
                        }
                    }
                    // '1'~'9'를 다 시도했는데도 해답을 못 찾으면, 이전 선택이 잘못된 것.
                    return false;
                }
            }
        }
        // 모든 칸을 채웠다면 (빈 칸이 없다면), 해답을 찾은 것.
        return true;
    }

    /**
     * 현재 위치 (row, col)에 digit을 넣는 것이 유효한지 검사하는 헬퍼 함수.
     */
    private boolean isValid(char[][] board, int row, int col, char digit) {
        for (int i = 0; i < 9; i++) {
            // 1. 행 검사 (같은 행의 다른 열)
            if (board[row][i] == digit) {
                return false;
            }

            // 2. 열 검사 (같은 열의 다른 행)
            if (board[i][col] == digit) {
                return false;
            }

            // 3. 3x3 박스 검사
            // 박스 내의 셀 인덱스 계산
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            
            if (board[boxRow][boxCol] == digit) {
                return false;
            }
        }
        return true;
    }
}