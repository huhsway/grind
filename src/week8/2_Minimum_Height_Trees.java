import java.util.*;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 1. 엣지 케이스 처리: 노드가 1개 이하인 경우
        if (n == 1) {
            return Collections.singletonList(0);
        }

        // 2. 그래프 구축 및 Degree 계산
        // 인접 리스트 (Adjacency List)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // 각 노드의 연결 횟수 (Degree)
        int[] degree = new int[n]; 

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        // 3. 초기 잎 노드(Degree가 1인 노드) 큐에 추가
        Queue<Integer> leaves = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                leaves.offer(i);
            }
        }

        // 4. Layer-by-Layer 제거 (Topological Sort 유사)
        int remainingNodes = n;
        
        // 노드가 2개 이하가 될 때까지 반복
        while (remainingNodes > 2) {
            int leavesCount = leaves.size();
            remainingNodes -= leavesCount;

            // 현재 층의 모든 잎 노드를 제거
            for (int i = 0; i < leavesCount; i++) {
                int leaf = leaves.poll();
                
                // 잎 노드의 유일한 이웃 노드들을 처리
                for (int neighbor : adj.get(leaf)) {
                    // 이웃 노드의 Degree를 감소
                    degree[neighbor]--;
                    
                    // 이웃 노드의 Degree가 새롭게 1이 되면, 다음 층의 잎 노드로 추가
                    if (degree[neighbor] == 1) {
                        leaves.offer(neighbor);
                    }
                }
            }
        }

        // 5. 최종 결과 반환: 큐에 남아있는 노드들이 MHT의 루트
        return new ArrayList<>(leaves);
    }
}