class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int r = heights.length;
        int c = heights[0].length;

        boolean[][] pacific = new boolean[r][c];
        boolean[][] atlantic = new boolean[r][c];

        for (int i = 0; i < c; i++) {
            dfs(heights, pacific, 0, i);
            dfs(heights, atlantic, r-1, i);
        }

        for (int i = 0; i < r; i++) {
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, c-1);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] visited, int nr, int nc) {
        int r = heights.length;
        int c = heights[0].length;

        if (visited[nr][nc]) {
            return;
        }

        visited[nr][nc] = true;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int[] dir : directions) {
            int dr = nr + dir[0];
            int dc = nc + dir[1];

            if (dr < 0 || dr >= r || dc < 0 || dc >= c) {
                continue;
            }

            if (heights[dr][dc] >= heights[nr][nc]) {
                dfs(heights, visited, dr, dc);
            }
        }
    }
}