import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        // 결과 배열의 크기는 총 윈도우 개수 (n - k + 1)
        int[] result = new int[n - k + 1]; 
        int resultIndex = 0;
        
        // Deque (double-ended queue)를 사용하여 인덱스를 저장.
        // 덱에는 '인덱스'를 저장하며, nums[인덱스] 값은 단조 감소(Monotonic Decreasing) 상태를 유지함.
        Deque<Integer> maxDeque = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            
            // 1. 윈도우를 벗어난 인덱스 제거
            // 덱의 맨 앞 인덱스가 현재 윈도우의 시작 인덱스(i - k + 1)보다 작으면 제거.
            if (!maxDeque.isEmpty() && maxDeque.peekFirst() == i - k) {
                maxDeque.pollFirst();
            }
            
            // 2. 단조 감소 덱 유지 (현재 값보다 작은 값들은 덱의 꼬리에서 제거)
            // 새로운 값(nums[i])보다 작은 값들은 미래에 절대 최댓값이 될 수 없으므로 제거.
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[i]) {
                maxDeque.pollLast();
            }
            
            // 3. 현재 인덱스 추가
            maxDeque.offerLast(i);
            
            // 4. 윈도우 최댓값 기록
            // 윈도우가 최소 크기 k에 도달했을 때 (i >= k - 1)
            if (i >= k - 1) {
                // 덱의 맨 앞(peekFirst())에 있는 인덱스의 값이 현재 윈도우의 최댓값임.
                result[resultIndex++] = nums[maxDeque.peekFirst()];
            }
        }
        
        return result;
    }
}
