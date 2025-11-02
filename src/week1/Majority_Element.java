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

// HashMap 풀이는 O(1) 공간 복잡도 조건을 만족하지 못함
// class Solution {
//     public int majorityElement(int[] nums) {
//         Map<Integer, Integer> counts = new HashMap<>();

//         for (int num : nums) {
//             counts.put(num, counts.getOrDefault(num, 0) +1);
//         }

//         int majorityThreshold = nums.length / 2;

//         for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
//             if (entry.getValue() > majorityThreshold) {
//                 return entry.getKey();
//             }
//         }

//         return -1;
//     }
// }