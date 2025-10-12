import java.util.Arrays;

class Solution {
    /**
     * K Closest Points to Origin 문제를 정렬 방식으로 해결하고,
     * for 루프를 사용해 k개의 점을 추출하는 함수
     * @param points 2차원 좌표 배열
     * @param k 찾아야 할 가장 가까운 점의 개수
     * @return 원점에 가장 가까운 k개의 점 배열
     */
    public int[][] kClosest(int[][] points, int k) {
        
        // 1. 모든 점을 원점으로부터의 거리에 따라 오름차순으로 정렬합니다.
        // 거리의 제곱(x^2 + y^2)을 기준으로 비교하면 제곱근 연산을 생략할 수 있어 효율적입니다.
        Arrays.sort(points, (a, b) -> {
            int distA = a[0] * a[0] + a[1] * a[1];
            int distB = b[0] * b[0] + b[1] * b[1];
            return distA - distB;
        });

        // 2. 결과를 담을 새로운 배열을 생성합니다.
        int[][] result = new int[k][2];

        // 3. 정렬된 배열의 앞에서부터 k개의 점을 for 루프를 사용해 복사합니다.
        for (int i = 0; i < k; i++) {
            result[i] = points[i];
        }

        return result;
    }
}
