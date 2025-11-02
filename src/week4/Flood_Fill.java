class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        
        // 새로운 색상이 원래 색상과 같으면 아무것도 할 필요가 없으므로 바로 반환
        if (originalColor == color) {
            return image;
        }
        
        dfs(image, sr, sc, color, originalColor);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int color, int originalColor) {
        // 1. 재귀 호출의 종료 조건 확인 (기저 사례)
        // - 배열의 경계를 벗어나는 경우
        // - 현재 픽셀의 색상이 원래 색상과 다른 경우
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != originalColor) {
            return;
        }

        // 2. 현재 픽셀을 새로운 색상으로 변경
        image[r][c] = color;
        
        // 3. 상하좌우 픽셀에 대해 재귀 호출
        dfs(image, r + 1, c, color, originalColor); // 아래
        dfs(image, r - 1, c, color, originalColor); // 위
        dfs(image, r, c + 1, color, originalColor); // 오른쪽
        dfs(image, r, c - 1, color, originalColor); // 왼쪽
    }
}

// BFS
// import java.util.LinkedList;
// import java.util.Queue;

// class Solution {
//     public int[][] floodFill(int[][] image, int sr, int sc, int color) {
//         int originalColor = image[sr][sc];

//         // 새로운 색상이 기존 색상과 같으면 변경할 필요가 없으므로 즉시 반환
//         if (originalColor == color) {
//             return image;
//         }

//         int rows = image.length;
//         int cols = image[0].length;

//         // BFS를 위한 큐 생성
//         Queue<int[]> queue = new LinkedList<>();
        
//         // 시작점을 큐에 추가하고 색상 변경
//         queue.add(new int[]{sr, sc});
//         image[sr][sc] = color;

//         // 4방향 탐색을 위한 배열
//         int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

//         while (!queue.isEmpty()) {
//             int[] current = queue.poll();
//             int r = current[0];
//             int c = current[1];

//             // 현재 픽셀의 상하좌우 탐색
//             for (int[] dir : directions) {
//                 int newR = r + dir[0];
//                 int newC = c + dir[1];

//                 // 경계 확인
//                 if (newR >= 0 && newR < rows && newC >= 0 && newC < cols) {
//                     // 인접한 픽셀이 원래 색상과 같으면
//                     if (image[newR][newC] == originalColor) {
//                         // 색상을 변경하고 큐에 추가
//                         image[newR][newC] = color;
//                         queue.add(new int[]{newR, newC});
//                     }
//                 }
//             }
//         }
//         return image;
//     }
// }
