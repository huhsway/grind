class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] memo = new int[matrix.length][matrix[0].length];

        int maxLength = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, memo, i, j));
            }
        }

        return maxLength;
    }

    private int dfs(int[][] matrix, int[][] memo, int y, int x) {
        if (memo[y][x] != 0) {
            return memo[y][x];
        }

        int[][] directions = {{0,-1}, {0,1}, {-1,0}, {1,0}};

        int maxPath = 1;

        for (int[] dir : directions) {
            int dy = y + dir[0];
            int dx = x + dir[1];

            if (dy < 0 || dy >= matrix.length || dx < 0 || dx >= matrix[0].length) {
                continue;
            }

            if (matrix[dy][dx] > matrix[y][x]) {
                maxPath = Math.max(maxPath, 1 + dfs(matrix, memo, dy, dx));
            }
        }

        memo[y][x] = maxPath;
        return maxPath;
    }
}