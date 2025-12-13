class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int startRow = 0, endRow = n - 1, startColumn = 0, endColumn = m - 1;
        int count = 0;

        List<Integer> result = new ArrayList<>();

        while(true) {
            for (int i = startColumn; i <= endColumn; i++) {
                result.add(matrix[startRow][i]);
                count++;
                if (count == n * m) return result;
            }
            startRow++;

            for (int i = startRow; i <= endRow; i++) {
                result.add(matrix[i][endColumn]);
                count++;
                if (count == n * m) return result;
            }
            endColumn--;

            for (int i = endColumn; i >= startColumn; i--) {
                result.add(matrix[endRow][i]);
                count++;
                if (count == m*n) return result;
            }
            endRow--;

            for (int i = endRow; i >= startRow; i--) {
                result.add(matrix[i][startColumn]);
                count++;
                if (count == n*m) return result;
            }
            startColumn++;
            
        }
    }
}