import java.util.ArrayList;
import java.util.List;

class Solution {

    /**
     * Combination Sum 문제의 진입점 함수
     * * @param candidates 후보 숫자 배열
     * @param target 목표 합계
     * @return 합이 target이 되는 모든 고유한 조합 리스트
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        // 백트래킹을 위한 재귀 함수 호출
        // 시작 인덱스를 0으로 설정하여 모든 후보부터 탐색 시작
        dfs(candidates, target, 0, temp, answer);

        return answer;
    }


    /**
     * 백트래킹을 이용한 깊이 우선 탐색 (DFS) 함수
     * * @param candidates 후보 숫자 배열
     * @param target 남아있는 목표 합계
     * @param start 다음 탐색을 시작할 인덱스 (조합의 중복 방지)
     * @param temp 현재까지의 조합
     * @param answer 최종 결과 리스트
     */
    private void dfs(int[] candidates, int target, int start, List<Integer> temp, List<List<Integer>> answer) {
        
        // 1. 종료 조건
        
        // 남아있는 target이 0이 되면 하나의 유효한 조합을 찾은 것
        if (target == 0) {
            // 현재 조합(temp)을 복사하여 결과에 추가 (deep copy)
            answer.add(new ArrayList<>(temp));
            return;
        }

        // 남아있는 target이 0보다 작으면 이 경로는 더 이상 유효하지 않으므로 종료 (가지치기)
        if (target < 0) {
            return;
        }


        // 2. 재귀 호출 및 백트래킹
        
        // start 인덱스부터 후보 숫자를 탐색
        for (int i = start; i < candidates.length; i++) {
            
            int currentNum = candidates[i];
            
            // a. 선택 (Go Deeper)
            
            // 현재 숫자를 조합에 추가
            temp.add(currentNum);
            
            // 재귀 호출: target에서 현재 숫자를 빼고, 
            // 현재 숫자를 재사용하기 위해 **인덱스 i를 그대로 전달**
            dfs(candidates, target - currentNum, i, temp, answer);
            
            
            // b. 백트래킹 (Backtrack)
            
            // 재귀 호출에서 돌아온 후, 마지막에 추가했던 요소를 제거하여 상태를 되돌림
            temp.remove(temp.size() - 1);
        }
    }
}
