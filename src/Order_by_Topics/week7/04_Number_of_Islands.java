class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int r = grid.length;
        int c = grid[0].length;
        int numIslands = 0;

        // 그리드의 모든 셀을 순회합니다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // '1' (육지)을 발견하면, 새로운 섬을 찾은 것입니다.
                if (grid[i][j] == '1') {
                    numIslands++;
                    // 해당 섬과 연결된 모든 육지를 '0'으로 변경하여 재탐색을 방지합니다.
                    dfs(grid, i, j);
                }
            }
        }
        return numIslands;
    }

    private void dfs(char[][] grid, int y, int x) {
        int r = grid.length;
        int c = grid[0].length;

        // 경계 조건을 벗어나거나, '0'(물)인 경우 종료합니다.
        if (y < 0 || y >= r || x < 0 || x >= c || grid[y][x] == '0') {
            return;
        }

        // 현재 위치를 '0'으로 바꿔 방문했음을 표시합니다.
        grid[y][x] = '0';

        // 상하좌우 인접한 셀을 재귀적으로 탐색합니다.
        dfs(grid, y - 1, x); // 상
        dfs(grid, y + 1, x); // 하
        dfs(grid, y, x - 1); // 좌
        dfs(grid, y, x + 1); // 우
    }
}