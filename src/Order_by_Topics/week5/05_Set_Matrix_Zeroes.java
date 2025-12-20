class Solution {
   public void setZeroes(int[][] matrix) {
        // 행과 열의 길이를 저장
        int m = matrix.length;
        int n = matrix[0].length;

        // 각 행과 열에 0이 포함되어 있는지 여부를 저장하기 위한 배열
        boolean[] rowZero = new boolean[m];
        boolean[] colZero = new boolean[n];

        // 0이 있는 위치를 찾아서 해당 행과 열에 대한 정보를 저장
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }

        // 행에 0이 있으면 해당 행 전체를 0으로 설정
        for (int i = 0; i < m; i++) {
            if (rowZero[i]) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 열에 0이 있으면 해당 열 전체를 0으로 설정
        for (int j = 0; j < n; j++) {
            if (colZero[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}