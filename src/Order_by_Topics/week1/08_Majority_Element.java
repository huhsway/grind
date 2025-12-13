class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        
        // 보이어-무어 과반수 투표 알고리즘
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (candiate == num) {
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


// 자바 맵 정렬 예제
// List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
// entryList.sort(((o1, o2) -> map.get(o1.getKey()) - map.get(o2.getKey())));
// for(Map.Entry<String, Integer> entry : entryList){
//     System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
// }

//key : a, value : 1
//key : b, value : 2
//key : c, value : 3