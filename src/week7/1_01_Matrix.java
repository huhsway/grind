class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dist = new int[m][n];
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i,j});
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while(!q.isEmpty()) {
            int[] cell = q.poll();
            int i = cell[0], j = cell[1];

            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];

                if (ni < 0 || ni >= m || nj < 0 || nj >= n) continue;

                if (dist[i][j] + 1 < dist[ni][nj]) {
                    dist[ni][nj] = dist[i][j] + 1;
                    q.offer(new int[]{ni,nj});
                }
            }
        }

        return dist;

    }
}