import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        dfs(candidates, target, 0, 0, temp, answer);  // sum=0부터 시작
        
        return answer;
    }
    
    private void dfs(int[] candidates, int target, int start, int sum, 
                     List<Integer> temp, List<List<Integer>> answer) {
        if (sum == target) {  // 선택된 숫자들의 합 == target
            answer.add(new ArrayList<>(temp));
            return;
        }
        
        if (sum > target) {   // 합 초과 시 백트래킹
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            int currentNum = candidates[i];
            
            temp.add(currentNum);
            dfs(candidates, target, i, sum + currentNum, temp, answer);  // sum 누적
            temp.remove(temp.size() - 1);
        }
    }
}
