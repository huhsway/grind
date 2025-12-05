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

    private void dfs(int[][] heights, boolean[][] visited, int y, int x) {

        if (visited[y][x]) {
            return;
        }

        visited[y][x] = true;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int[] dir : directions) {
            int dy = y + dir[0];
            int dx = y + dir[1];

            if (dy < 0 || dy >= heights.length || dx < 0 || dx >= heights[0].length) {
                continue;
            }

            if (heights[dy][dx] >= heights[y][x]) {
                dfs(heights, visited, dy, dx);
            }
        }
    }
}