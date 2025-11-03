class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;
        int numIslands = 0;

        // 그리드의 모든 셀을 순회합니다.
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                // '1' (육지)을 발견하면, 새로운 섬을 찾은 것입니다.
                if (grid[r][c] == '1') {
                    numIslands++;
                    // 해당 섬과 연결된 모든 육지를 '0'으로 변경하여 재탐색을 방지합니다.
                    dfs(grid, r, c);
                }
            }
        }
        return numIslands;
    }

    private void dfs(char[][] grid, int r, int c) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        // 경계 조건을 벗어나거나, '0'(물)인 경우 종료합니다.
        if (r < 0 || r >= numRows || c < 0 || c >= numCols || grid[r][c] == '0') {
            return;
        }

        // 현재 위치를 '0'으로 바꿔 방문했음을 표시합니다.
        grid[r][c] = '0';

        // 상하좌우 인접한 셀을 재귀적으로 탐색합니다.
        dfs(grid, r - 1, c); // 상
        dfs(grid, r + 1, c); // 하
        dfs(grid, r, c - 1); // 좌
        dfs(grid, r, c + 1); // 우
    }
}