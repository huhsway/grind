import java.util.PriorityQueue;

class Solution {
    /**
     * K Closest Points to Origin 문제를 최대 힙(Max Heap)으로 해결하는 함수
     * @param points 2차원 좌표 배열
     * @param k 찾아야 할 가장 가까운 점의 개수
     * @return 원점에 가장 가까운 k개의 점 배열
     */
    public int[][] kClosest(int[][] points, int k) {
        
        // 1. 최대 힙(Max Heap) 정의
        // Comparator를 통해 거리의 제곱 (x^2 + y^2)이 큰 순서로 정렬되도록 합니다.
        // a[0]*a[0] + a[1]*a[1] - (b[0]*b[0] + b[1]*b[1])의 결과가 양수이면 a가 더 크다는 의미(최대 힙의 조건)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );

        // 2. 모든 점 순회
        for (int[] point : points) {
            maxHeap.offer(point); // 현재 점을 힙에 추가

            // 3. 힙 크기 관리
            // 힙의 크기가 K를 초과하면, 가장 먼 점(루트)을 제거하여 크기를 K로 유지
            if (maxHeap.size() > k) {
                maxHeap.poll(); 
            }
        }

        // 4. 결과 추출
        // 힙에는 이제 가장 가까운 K개의 점만 남아있습니다.
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }
}