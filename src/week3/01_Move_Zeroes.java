class Solution {
    /**
     * 배열의 모든 0을 끝으로 이동시키고, 0이 아닌 요소의 상대적 순서를 유지합니다.
     * 이 작업은 제자리에서(in-place) 수행됩니다.
     *
     * @param nums 0을 이동시킬 정수 배열
     */
    public void moveZeroes(int[] nums) {
        // nonZeroIndex는 다음 0이 아닌 요소가 위치해야 할 인덱스를 추적합니다.
        int nonZeroIndex = 0;
        
        // 1. 배열을 순회하며 0이 아닌 요소를 앞으로 이동시킵니다.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 현재 요소가 0이 아니면, nonZeroIndex 위치에 저장합니다.
                nums[nonZeroIndex] = nums[i];
                // 다음 0이 아닌 요소가 위치할 자리를 위해 인덱스를 증가시킵니다.
                nonZeroIndex++;
            }
        }
        
        // 2. nonZeroIndex부터 배열 끝까지를 모두 0으로 채웁니다.
        // 이 위치들은 원래 0이었거나 0이 아닌 요소가 이미 앞으로 이동한 자리입니다.
        for (int i = nonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}