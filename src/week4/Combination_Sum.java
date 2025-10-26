import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        
        // candidates 배열은 정렬하면 탐색 효율이 좋아집니다.
        // 하지만 문제 해결의 핵심은 아니므로 생략해도 무방합니다.
        
        // dfs 함수를 호출하여 조합을 찾습니다.
        dfs(candidates, target, new ArrayList<>(), answer, 0);
        return answer;
    }

    private void dfs(int[] candidates, int target, List<Integer> currentCombination, List<List<Integer>> answer, int startIndex) {
        // 1. 종료 조건: 현재 합이 target과 같으면 유효한 조합입니다.
        if (target == 0) {
            // 새로운 리스트로 복사하여 answer에 추가합니다.
            answer.add(new ArrayList<>(currentCombination));
            return;
        }

        // 2. 종료 조건: 현재 합이 target보다 커지면 더 이상 탐색할 필요가 없습니다.
        if (target < 0) {
            return;
        }

        // 3. 재귀 호출 및 백트래킹
        for (int i = startIndex; i < candidates.length; i++) {
            // 현재 후보 숫자를 조합에 추가합니다.
            currentCombination.add(candidates[i]);
            
            // 다음 재귀 호출:
            // - target에서 현재 숫자를 뺍니다.
            // - 다음 탐색 시작 인덱스를 i로 유지하여 중복 선택을 허용합니다.
            dfs(candidates, target - candidates[i], currentCombination, answer, i);
            
            // 백트래킹: 마지막에 추가한 숫자를 제거하여 다음 경우를 탐색합니다.
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}