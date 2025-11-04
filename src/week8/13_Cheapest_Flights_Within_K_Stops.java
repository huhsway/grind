import java.util.*;

class Solution {
    /**
     * @param n 도시의 수
     * @param flights 항공편 정보 [출발, 도착, 비용]
     * @param src 출발 도시
     * @param dst 목적 도시
     * @param k 최대 경유 횟수
     * @return 최저 비용, 도달 불가능 시 -1
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1. 그래프 구축 (인접 리스트: [도착 도시, 비용])
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // 최소 비용 배열 (초기값은 무한대)
        // Integer.MAX_VALUE 대신 10001 * 10000과 같이 안전한 큰 값 사용
        final int INF = 1_000_000_000; 
        int[] minCost = new int[n];
        Arrays.fill(minCost, INF);
        minCost[src] = 0;

        // 2. BFS 큐: (도시 인덱스, 현재까지의 총 비용)
        // 큐에는 현재 도시와 해당 도시까지의 비용을 저장합니다.
        // ArrayDeque 대신 PriorityQueue를 사용하면 다익스트라가 되지만,
        // 여기서는 경유 횟수(stops)를 기준으로 탐색하기 위해 일반 큐를 사용합니다.
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{src, 0}); // {city, cost}

        int stops = 0;

        // 3. BFS 시작 (stops는 경유 횟수를 의미)
        while (!queue.isEmpty() && stops <= k) {
            int size = queue.size();
            
            // 현재 경유 횟수(stops) 레벨에 있는 모든 도시를 처리
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int currentCity = current[0];
                int currentCost = current[1];

                // 현재 도시에서 가능한 모든 항공편 탐색
                for (int[] nextFlight : adj.get(currentCity)) {
                    int nextCity = nextFlight[0];
                    int price = nextFlight[1];
                    int newTotalCost = currentCost + price;

                    // 비용 최적화 체크: 
                    // 1. 새로운 경로의 총 비용이 현재까지의 minCost보다 저렴해야 함
                    // 2. 이 경로는 stops+1 번째 비행(최대 k 경유)을 통해 nextCity에 도착
                    if (newTotalCost < minCost[nextCity]) {
                        minCost[nextCity] = newTotalCost;
                        // 다음 경유지(nextCity)를 큐에 추가
                        // stops < k일 때만 다음 비행을 탐색해야 하지만,
                        // minCost 체크로 이미 최적화되므로 stops==k인 경우에도 일단 추가
                        // (단, 큐가 비어있지 않은 동안 stops <= k 조건으로 제어)
                        queue.offer(new int[]{nextCity, newTotalCost});
                    }
                }
            }
            stops++; // 경유 횟수 증가
        }

        // 4. 결과 반환
        return minCost[dst] == INF ? -1 : minCost[dst];
    }
}