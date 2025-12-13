class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];  // queens[row] = col (해당 행에 퀸이 놓인 열)
        
        backtrack(result, queens, 0, n);
        return result;
    }
    
    private void backtrack(List<List<String>> result, int[] queens, int row, int n) {
        // 기저 조건: 모든 행에 퀸을 배치함
        if (row == n) {
            result.add(buildBoard(queens, n));
            return;
        }
        
        // 현재 행의 각 열에 퀸 배치 시도
        for (int col = 0; col < n; col++) {
            if (isValid(queens, row, col)) {
                queens[row] = col;
                backtrack(result, queens, row + 1, n);
                // 백트래킹 (다음 루프에서 덮어쓰므로 명시적 복구 불필요)
            }
        }
    }
    
    private boolean isValid(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            int qCol = queens[i];
            
            // 같은 열 체크
            if (qCol == col) return false;
            
            // 대각선 체크 (행 차이 == 열 차이면 대각선)
            if (Math.abs(row - i) == Math.abs(col - qCol)) return false;
        }
        return true;
    }
    
    private List<String> buildBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] line = new char[n];
            Arrays.fill(line, '.');
            line[queens[row]] = 'Q';
            board.add(new String(line));
        }
        return board;
    }
}