import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 그래프 및 진입차수 배열 초기화
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];

        // 2. 그래프와 진입차수 구축
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            adj.get(pre).add(course); // pre -> course 간선 추가
            inDegree[course]++; // course의 진입차수 증가
        }

        // 3. 진입차수가 0인 과목을 큐에 추가
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        // 4. 큐에서 과목을 꺼내며 위상 정렬 수행
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;

            // 현재 과목에 연결된 다음 과목들의 진입차수 감소
            for (int nextCourse : adj.get(course)) {
                inDegree[nextCourse]--;
                // 진입차수가 0이 되면 큐에 추가
                if (inDegree[nextCourse] == 0) {
                    queue.add(nextCourse);
                }
            }
        }

        // 5. 정렬된 과목의 수가 전체 과목 수와 같으면 사이클 없음
        return count == numCourses;
    }
}
