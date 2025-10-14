import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int startRow = 0, endRow = rows - 1, startColumn = 0, endColumn = cols - 1;

        while (startRow <= endRow && startColumn <= endColumn) {
            // 상단 행
            for (int i = startColumn; i <= endColumn; i++) {
                result.add(matrix[startRow][i]);
            }
            startRow++;

            // 오른쪽 열
            for (int i = startRow; i <= endRow; i++) {
                result.add(matrix[i][endColumn]);
            }
            endColumn--;

            // 하단 행 (역순)
            if (startRow <= endRow) {
                for (int i = endColumn; i >= startColumn; i--) {
                    result.add(matrix[endRow][i]);
                }
                endRow--;
            }

            // 왼쪽 열 (역순)
            if (startColumn <= endColumn) {
                for (int i = endRow; i >= startRow; i--) {
                    result.add(matrix[i][startColumn]);
                }
                startColumn++;
            }
        }

        return result;
    }
}

//public class SpiralMatrix {
//    public List<Integer> spiralOrder(int[][] matrix) {
//        int n = matrix.length;
//        int m = matrix[0].length;
//
//        int startRow = 0, endRow = n - 1, startColumn = 0, endColumn = m - 1;
//        int count = 0;
//
//        List<Integer> result = new ArrayList<>();
//
//        while (true) {
//            for (int i = startColumn; i <= endColumn; i++) {
//                result.add(matrix[startRow][i]);
//                count++;
//                if (count == n * m) return result;
//            }
//            startRow++;
//
//            for (int i = startRow; i <= endRow; i++) {
//                result.add(matrix[i][endColumn]);
//                count++;
//                if (count == n * m) return result;
//            }
//            endColumn--;
//
//            for (int i = endColumn; i >= startColumn; i--) {
//                result.add(matrix[endRow][i]);
//                count++;
//                if (count == n * m) return result;
//            }
//            endRow--;
//
//            for (int i = endRow; i >= startRow; i--) {
//                result.add(matrix[i][startColumn]);
//                count++;
//                if (count == n * m) return result;
//            }
//            startColumn++;
//        }
//    }
//}

