import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        List<Integer> temp = new ArrayList<>();
        
        // 백트래킹을 위한 재귀 함수 호출
        getPermutations(nums, visited, temp, answer);
        
        return answer;
    }

    private void getPermutations(int[] nums, boolean[] visited, List<Integer> temp, List<List<Integer>> answer) {
        // 종료 조건: temp 리스트의 길이가 nums 배열의 길이와 같아지면 하나의 순열 완성
        if (temp.size() == nums.length) {
            answer.add(new ArrayList<>(temp));
            return;
        }

        // 재귀 호출 및 백트래킹
        for (int i = 0; i < nums.length; i++) {
            // 이미 사용된 숫자는 건너뜁니다.
            if (!visited[i]) {
                visited[i] = true;
                temp.add(nums[i]);

                getPermutations(nums, visited, temp, answer);

                // 백트래킹: 마지막에 추가한 요소와 방문 상태를 되돌립니다.
                temp.remove(temp.size() - 1);
                visited[i] = false;
            }
        }
    }
}