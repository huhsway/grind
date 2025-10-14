class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        
        // 보이어-무어 과반수 투표 알고리즘
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        return candidate;
    }
}