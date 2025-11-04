import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        // 1. 그래프 구축 및 In-degree 계산
        
        // 인접 리스트: adj.get(i)는 i를 선수 과목으로 하는 강의 목록
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // 진입 차수: inDegree[i]는 i를 듣기 위해 먼저 들어야 하는 강의 수
        int[] inDegree = new int[numCourses];
        
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0]; // 들어야 할 강의
            int prereq = prerequisite[1]; // 선수 과목
            
            // prereq -> course (prereq를 들어야 course를 들을 수 있음)
            adj.get(prereq).add(course);
            inDegree[course]++;
        }
        
        // 2. 초기 큐 설정: In-degree가 0인 강의
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // 결과 배열 및 카운터
        int[] result = new int[numCourses];
        int count = 0;
        
        // 3. 위상 정렬 (Kahn's Algorithm)
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            result[count++] = currentCourse; // 순서에 추가
            
            // 현재 강의를 선수 과목으로 하는 모든 후속 강의 처리
            for (int neighbor : adj.get(currentCourse)) {
                
                // 진입 차수 감소
                inDegree[neighbor]--;
                
                // 진입 차수가 0이 되면 큐에 추가 (이제 들을 수 있음)
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // 4. 결과 반환
        // 순서에 추가된 강의 수가 전체 강의 수와 같다면 유효한 순서 반환
        if (count == numCourses) {
            return result;
        } 
        // 아니라면 순환이 존재하여 모든 강의를 들을 수 없으므로 빈 배열 반환
        else {
            return new int[0];
        }
    }
}