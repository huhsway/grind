class Solution {
    public void rotate(int[][] matrix) {
        reverseMatrix(matrix);
        transposeMatrix(matrix);
        printMatrix(matrix); // Optional: print the result
    }

    private void reverseMatrix(int[][] matrix) {
        int start = 0, end = matrix.length - 1;

        while (start < end) {
            int[] temp = matrix[start];
            matrix[start] = matrix[end];
            matrix[end] = temp;

            start++;
            end--;
        }
    }

    private void transposeMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}