class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;
        int[][] dist = new int[r][c];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
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
            int[] pos = q.poll();
            int ny = pos[0], nx = pos[1];

            for (int[] dir : dirs) {
                int dy = ny + dir[0];
                int dx = nx + dir[1];

                if (dy < 0 || dy >= r || dx < 0 || dx >= c) continue;

                if (dist[ny][nx] + 1 < dist[dy][dx]) {
                    dist[dy][dx] = dist[ny][nx] + 1;
                    q.offer(new int[]{dy,dx});
                }
            }
        }

        return dist;

    }
}