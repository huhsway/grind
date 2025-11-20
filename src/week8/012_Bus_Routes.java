import java.util.*;

class Solution {
    public int numBusesToReachTarget(int[][] routes, int source, int target) {
        // 엣지 케이스: 출발지와 목적지가 같으면 0회
        if (source == target) {
            return 0;
        }

        // 1. 정류장 -> 노선 인덱스 매핑 (stopToRoutes)
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        // 2. BFS 초기화
        
        // 큐: 버스 노선 인덱스를 저장 (노선 자체가 BFS의 노드)
        Queue<Integer> queue = new ArrayDeque<>();
        
        // 노선 방문 기록: 이미 탔던 노선을 다시 타지 않기 위함
        boolean[] routeVisited = new boolean[routes.length];
        
        int buses = 0; // 탑승 횟수 (결과)

        // 출발지 source를 지나는 모든 노선으로 초기화
        if (stopToRoutes.containsKey(source)) {
            for (int routeIdx : stopToRoutes.get(source)) {
                queue.offer(routeIdx);
                routeVisited[routeIdx] = true;
            }
        }
        
        // 3. BFS 시작
        while (!queue.isEmpty()) {
            int size = queue.size();
            buses++; // 버스 탑승 횟수 증가 (다음 레벨로 이동)

            for (int i = 0; i < size; i++) {
                int currentRouteIdx = queue.poll();

                // 현재 노선의 모든 정류장 순회
                for (int stop : routes[currentRouteIdx]) {
                    
                    // 목적지 도달 확인
                    if (stop == target) {
                        return buses;
                    }

                    // 현재 정류장에서 환승할 수 있는 다른 노선 탐색
                    if (stopToRoutes.containsKey(stop)) {
                        for (int nextRouteIdx : stopToRoutes.get(stop)) {
                            if (!routeVisited[nextRouteIdx]) {
                                routeVisited[nextRouteIdx] = true;
                                queue.offer(nextRouteIdx); // 다음 노선으로 환승
                            }
                        }
                    }
                }
            }
        }

        // 목적지에 도달할 수 없는 경우
        return -1;
    }
}