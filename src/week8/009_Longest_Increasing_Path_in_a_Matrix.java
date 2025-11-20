class Solution {
    private int R, C;
    // 상하좌우 네 방향
    private final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 행렬에서 가장 긴 증가 경로의 길이를 찾습니다.
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        R = matrix.length;
        C = matrix[0].length;
        
        // 메모이제이션 배열: memo[i][j]는 (i, j)에서 시작하는 LIP의 길이를 저장
        int[][] memo = new int[R][C];
        int maxPath = 0;

        // 모든 셀을 시작점으로 DFS를 시도
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, memo));
            }
        }

        return maxPath;
    }

    /**
     * (r, c)에서 시작하는 가장 긴 증가 경로의 길이를 계산합니다.
     */
    private int dfs(int[][] matrix, int r, int c, int[][] memo) {
        // 이미 계산된 값이면 바로 반환 (Memoization)
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        // 현재 셀을 포함하므로 최소 길이는 1
        int maxLen = 1;

        // 상하좌우 탐색
        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // 1. 경계 조건 확인
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }

            // 2. 증가 경로 조건 확인 (다음 셀의 값이 현재 셀보다 커야 함)
            if (matrix[nr][nc] > matrix[r][c]) {
                // 재귀적으로 다음 경로의 길이를 얻음
                int neighborPathLen = dfs(matrix, nr, nc, memo);
                
                // 경로 길이 업데이트: 1 (현재 셀) + neighborPathLen
                maxLen = Math.max(maxLen, 1 + neighborPathLen);
            }
        }

        // 계산된 최대 길이를 메모 배열에 저장
        memo[r][c] = maxLen;
        return maxLen;
    }
}