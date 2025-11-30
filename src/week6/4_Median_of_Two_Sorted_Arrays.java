class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // nums1이 더 짧은 배열이 되도록 swap (이진탐색 범위 최소화)
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        
        // nums1에서 이진탐색 (0개 ~ m개 선택 가능)
        int low = 0;
        int high = m;
        
        // 왼쪽 파티션에 들어갈 원소 개수
        // +1을 해서 홀수일 때 중앙값이 왼쪽에 포함되도록 함
        int halfLen = (m + n + 1) / 2;

        while (low <= high) {
            // i: nums1에서 왼쪽에 넣을 개수
            int i = low + (high - low) / 2;
            
            // j: nums2에서 왼쪽에 넣을 개수 (i + j = halfLen)
            int j = halfLen - i;

            // 경계 처리: 배열 범위 벗어나면 극값 사용
            int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];
            
            // 올바른 파티션 찾음: 왼쪽 max <= 오른쪽 min
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                int maxLeft = Math.max(maxLeft1, maxLeft2);
                
                // 홀수: 왼쪽 최댓값이 중앙값
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                
                // 짝수: 왼쪽 최댓값과 오른쪽 최솟값의 평균
                int minRight = Math.min(minRight1, minRight2);
                return (double)(maxLeft + minRight) / 2.0;

            } else if (maxLeft1 > minRight2) {
                // nums1에서 너무 많이 가져옴 → i 줄이기
                high = i - 1;
            } else {
                // nums1에서 너무 적게 가져옴 → i 늘리기
                low = i + 1;
            }
        }

        return 0.0;
    }
}