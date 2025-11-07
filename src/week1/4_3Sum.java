import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 배열 정렬
        Arrays.sort(nums);
        int n = nums.length;
        
        // 초기 최솟값 설정
        int closestSum = nums[0] + nums[1] + nums[2];
        
        for (int i = 0; i < n - 2; i++) {
            // 🔴 중복 제거 1: i 중복 스킵
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                // 최솟값 갱신 확인
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
                
                // 합 조정 및 포인터 이동
                if (sum < target) {
                    left++;
                    // 🔴 중복 제거 2: left 중복 스킵
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else if (sum > target) {
                    right--;
                    // 🔴 중복 제거 3: right 중복 스킵
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    // sum == target인 경우 즉시 반환
                    return sum;
                }
            }
        }
        
        return closestSum;
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