class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        
        // 1단계: 오른쪽부터 "감소가 깨지는 지점" 찾기
        // 즉, nums[i] < nums[i+1]인 가장 오른쪽 i 찾기
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        // 2단계: i가 유효하면 (이미 가장 큰 순열이 아니면)
        if (i >= 0) {
            // nums[i]보다 큰 수 중 가장 작은 수를 오른쪽에서 찾기
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // nums[i]와 nums[j] 교환
            swap(nums, i, j);
        }
        
        // 3단계: i+1부터 끝까지 뒤집기 (오름차순으로 만들기)
        reverse(nums, i + 1, n - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}