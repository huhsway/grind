import java.util.Arrays;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);

        for (int standard = 0; standard < n - 2; standard++) {

            int left = standard + 1;
            int right = n - 1;

            if (standard > 0 && nums[standard] == nums[standard - 1]) {
                continue;
            }

            while (left < right) {

                int total = nums[standard] + nums[left] + nums[right];

                if (total == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.addAll(Arrays.asList(nums[standard], nums[left], nums[right]));
                    answer.add(temp);

                    while (left < right && nums[left] == nums[left +1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (total > 0) {
                    right--;
                } else {
                    left++;
                }

            }

            return answer;

        }
        
    }
}

// 번외 리스트에서 중복 제거

// 1. 순서 유지 안됨
// import java.util.*;

// public class Solution {
//     public static void main(String[] args) {
//         List<List<Integer>> list = new ArrayList<>();
//         list.add(Arrays.asList(-1, 0, 1));
//         list.add(Arrays.asList(-1, 0, 1));  // 중복
//         list.add(Arrays.asList(-1, -1, 2));
        
//         System.out.println("원본: " + list);
//         // 원본: [[-1, 0, 1], [-1, 0, 1], [-1, -1, 2]]
        
//         // HashSet 사용
//         Set<List<Integer>> set = new HashSet<>(list);
//         List<List<Integer>> result = new ArrayList<>(set);
        
//         System.out.println("중복 제거: " + result);
//         // 중복 제거: [[-1, 0, 1], [-1, -1, 2]]
//     }
// }

// 2. 순서를 유지해야 한다면
// import java.util.*;

// public class Solution {
//     public static void main(String[] args) {
//         List<List<Integer>> list = new ArrayList<>();
//         list.add(Arrays.asList(-1, 0, 1));
//         list.add(Arrays.asList(-1, 0, 1));  // 중복
//         list.add(Arrays.asList(-1, -1, 2));
        
//         System.out.println("원본: " + list);
        
//         // LinkedHashSet 사용 (순서 유지!)
//         Set<List<Integer>> set = new LinkedHashSet<>(list);
//         List<List<Integer>> result = new ArrayList<>(set);
        
//         System.out.println("중복 제거: " + result);
//         // 중복 제거: [[-1, 0, 1], [-1, -1, 2]]
//     }
// }

// 3. stream 사용 + 순서 유지 됨
// import java.util.*;
// import java.util.stream.Collectors;

// public class Solution {
//     public static void main(String[] args) {
//         List<List<Integer>> list = new ArrayList<>();
//         list.add(Arrays.asList(-1, 0, 1));
//         list.add(Arrays.asList(-1, 0, 1));  // 중복
//         list.add(Arrays.asList(-1, -1, 2));
        
//         System.out.println("원본: " + list);
        
//         // Stream 사용
//         List<List<Integer>> result = list.stream()
//                                           .distinct()  // 중복 제거
//                                           .collect(Collectors.toList());
        
//         System.out.println("중복 제거: " + result);
//         // 중복 제거: [[-1, 0, 1], [-1, -1, 2]]
//     }
// }