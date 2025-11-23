class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        
        // 그래프 구축
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // Step 1: 노드 0에서 가장 먼 노드 찾기
        int farthest1 = findFarthest(graph, 0, n);
        
        // Step 2: 그 노드에서 다시 가장 먼 노드 찾기
        int farthest2 = findFarthest(graph, farthest1, n);
        
        // Step 3: 두 끝점 사이의 경로 찾기
        List<Integer> path = findPath(graph, farthest1, farthest2, n);
        
        // Step 4: 중간 노드 반환
        int len = path.size();
        if (len % 2 == 1) {
            return Collections.singletonList(path.get(len / 2));
        } else {
            return Arrays.asList(path.get(len / 2 - 1), path.get(len / 2));
        }
    }
    
    // 가장 먼 노드 찾기
    private int findFarthest(List<List<Integer>> graph, int start, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(start);
        visited[start] = true;
        
        int farthest = start;
        while (!queue.isEmpty()) {
            farthest = queue.poll();
            for (int neighbor : graph.get(farthest)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        return farthest;
    }
    
    // 두 노드 사이의 경로 찾기
    private List<Integer> findPath(List<List<Integer>> graph, int start, int end, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];
        
        Arrays.fill(parent, -1);
        queue.offer(start);
        visited[start] = true;
        
        // BFS로 end 찾기
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == end) break;
            
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = node;
                    queue.offer(neighbor);
                }
            }
        }
        
        // 경로 역추적
        List<Integer> path = new ArrayList<>();
        int current = end;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        
        Collections.reverse(path);
        return path;
    }
}