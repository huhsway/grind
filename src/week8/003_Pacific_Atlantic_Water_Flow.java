class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];

        for (int i = 0; i < heights[0].length; i++) {
            dfs(heights, pacific, 0, i);
            dfs(heights, atlantic, heights.length-1, i);
        }

        for (int i = 0; i < heights.length; i++) {
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, heights[0].length-1);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] visited, int nr, int nc) {

        if (visited[nr][nc]) {
            return;
        }

        visited[nr][nc] = true;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int[] dir : directions) {
            int dr = nr + dir[0];
            int dc = nc + dir[1];

            if (dr < 0 || dr >= heights.length || dc < 0 || dc >= heights[0].length) {
                continue;
            }

            if (heights[dr][dc] >= heights[nr][nc]) {
                dfs(heights, visited, dr, dc);
            }
        }
    }
}