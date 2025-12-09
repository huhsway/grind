import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // 최종 결과를 저장할 리스트
        List<List<Integer>> result = new ArrayList<>();
        
        // 현재 부분집합을 구성할 리스트 (경로)
        List<Integer> currentSubset = new ArrayList<>();
        
        // 백트래킹 시작: 시작 인덱스는 0
        getCombinations(nums, 0, currentSubset, result);
        
        return result;
    }

    /**
     * 백트래킹을 사용하여 모든 부분집합을 생성하는 재귀 함수
     * @param nums 원본 배열
     * @param start 현재 탐색을 시작할 인덱스
     * @param currentSubset 현재까지 구성된 부분집합
     * @param result 모든 부분집합을 저장할 최종 리스트
     */
    private void getCombinations(int[] nums, int start, List<Integer> currentSubset, List<List<Integer>> result) {
        
        // 1. **결과 추가**: 현재까지 구성된 currentSubset을 결과 리스트에 추가합니다.
        //    (빈 배열 []을 포함하여 모든 중간 부분집합이 여기서 추가됩니다.)
        result.add(new ArrayList<>(currentSubset));
        
        // 2. **탐색**: start 인덱스부터 배열 끝까지 반복하며 다음 원소를 선택합니다.
        for (int i = start; i < nums.length; i++) {
            // (a) **선택**: 현재 원소 nums[i]를 부분집합에 포함
            currentSubset.add(nums[i]);
            
            // (b) **재귀**: 다음 원소(i+1)부터 탐색을 계속합니다.
            //     이는 현재 원소를 포함한 상태에서 다음 부분집합을 생성합니다.
            getCombinations(nums, i + 1, currentSubset, result);
            
            // (c) **되돌리기(Backtrack)**: 재귀 호출에서 돌아온 후, 
            //     부분집합에서 nums[i]를 제거하여 다음 반복(i+1)에서 nums[i]를 포함하지 않은 
            //     경우의 수(다른 경로)를 탐색할 수 있도록 상태를 복원합니다.
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}