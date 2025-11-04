class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length;      // 행의 개수
        int n = matrix[0].length;   // 열의 개수
        
        // 전체 행렬을 1차원 배열로 간주하고 이진 탐색을 수행합니다.
        int low = 0;
        int high = m * n - 1; // 1차원 인덱스의 끝

        while (low <= high) {
            // 중앙 인덱스 계산
            int mid = low + (high - low) / 2;
            
            // 1차원 인덱스 'mid'를 2차원 좌표 (row, col)로 변환
            int r = mid / n; // 행 (Row): mid를 열 개수(n)로 나눈 몫
            int c = mid % n; // 열 (Column): mid를 열 개수(n)로 나눈 나머지
            
            int midValue = matrix[r][c];

            if (midValue == target) {
                // 타겟을 찾은 경우
                return true;
            } else if (midValue < target) {
                // 중앙값이 타겟보다 작으면 오른쪽 절반을 탐색
                low = mid + 1;
            } else {
                // 중앙값이 타겟보다 크면 왼쪽 절반을 탐색
                high = mid - 1;
            }
        }

        // 반복문이 끝날 때까지 타겟을 찾지 못한 경우
        return false;
    }
}