import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int r = grid.length;
        int c = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        int freshOranges = 0;

        // 1. 초기 상태 설정: 썩은 오렌지(2)를 큐에 넣고 신선한 오렌지(1)의 개수를 셉니다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        // 2. BFS 시작: 시간이 0분부터 시작합니다.
        int minutes = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty() && freshOranges > 0) {
            // 현재 분에 썩은 모든 오렌지들을 처리합니다.
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] orange = queue.poll();
                int ny = orange[0];
                int nx = orange[1];

                // 인접한 오렌지들을 탐색합니다.
                for (int[] dir : dirs) {
                    int dy = ny + dir[0];
                    int dx = nx + dir[1];

                    // 유효성 검사: 경계 내에 있고, 신선한 오렌지인 경우
                    if (dy >= 0 && dy < r && dx >= 0 && dx < c && grid[dy][dx] == 1) {
                        grid[dy][dx] = 2; // 신선한 오렌지를 썩게 만듭니다.
                        queue.offer(new int[]{dy, dx});
                        freshOranges--; // 신선한 오렌지 개수를 감소시킵니다.
                    }
                }
            }
            minutes++; // 한 분이 경과했습니다.
        }

        // 3. 결과 반환
        return freshOranges == 0 ? minutes : -1;
    }
}