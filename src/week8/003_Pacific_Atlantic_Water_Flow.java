import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 상하좌우
    private int R, C;

    /**
     * 주어진 격자에서 Pacific과 Atlantic 모두로 물이 흐를 수 있는 셀을 찾습니다.
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        R = heights.length;
        C = heights[0].length;

        // 두 개의 boolean 배열을 사용하여 각 셀이 Pacific과 Atlantic에서 도달 가능한지 기록
        boolean[][] pacific = new boolean[R][C];
        boolean[][] atlantic = new boolean[R][C];

        // 1. 테두리에서 DFS 시작 (태평양: 0행/0열, 대서양: R-1행/C-1열)

        // 0행과 R-1행을 따라 DFS 시작
        for (int j = 0; j < C; j++) {
            // 태평양 (0행)
            dfs(heights, pacific, 0, j);
            // 대서양 (R-1행)
            dfs(heights, atlantic, R - 1, j);
        }

        // 0열과 C-1열을 따라 DFS 시작
        for (int i = 0; i < R; i++) {
            // 태평양 (0열)
            dfs(heights, pacific, i, 0);
            // 대서양 (C-1열)
            dfs(heights, atlantic, i, C - 1);
        }

        // 2. 결과 합산
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 두 대양 모두에서 도달 가능한 경우
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    /**
     * DFS를 수행하여 대양에서 물이 역방향으로 도달할 수 있는 모든 셀을 표시합니다.
     * @param heights 지형 높이
     * @param visited 현재 대양에서 도달 가능한지 기록하는 배열
     * @param r 현재 행
     * @param c 현재 열
     */
    private void dfs(int[][] heights, boolean[][] visited, int r, int c) {
        // 이미 방문했으면 중복 탐색 방지
        if (visited[r][c]) {
            return;
        }

        visited[r][c] = true;

        // 상하좌우 탐색
        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // 경계 조건 확인
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }

            // 역방향 흐름 규칙: 현재 셀(r, c)에서 다음 셀(nr, nc)로 가기 위해서는
            // 다음 셀의 높이가 현재 셀의 높이보다 같거나 높아야 합니다. (heights[nr][nc] >= heights[r][c])
            // 즉, 물이 (nr, nc)에서 (r, c)로 흐를 수 있어야 합니다.
            if (heights[nr][nc] >= heights[r][c]) {
                dfs(heights, visited, nr, nc);
            }
        }
    }
}