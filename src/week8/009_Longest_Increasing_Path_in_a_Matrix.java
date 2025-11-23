class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int r = matrix.length;
        int c = matrix[0].length;

        int[][] memo = new int[r][c];

        int maxLength = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, memo, i, j));
            }
        }

        return maxLength;
    }

    private int dfs(int[][] matrix, int[][] memo, int nr, int nc) {
        if (memo[nr][nc] != 0) {
            return memo[nr][nc];
        }

        int r = matrix.length;
        int c = matrix[0].length;

        int[][] directions = {{0,-1}, {0,1}, {-1,0}, {1,0}};

        int maxPath = 1;

        for (int[] dir : directions) {
            int dr = nr + dir[0];
            int dc = nc + dir[1];

            if (dr < 0 || dr >= r || dc < 0 || dc >= c) {
                continue;
            }

            if (matrix[dr][dc] > matrix[nr][nc]) {
                maxPath = Math.max(maxPath, 1 + dfs(matrix, memo, dr, dc));
            }
        }

        memo[nr][nc] = maxPath;
        return maxPath;
    }
}