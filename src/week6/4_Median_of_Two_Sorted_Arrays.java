class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 성능 최적화를 위해 항상 nums1을 더 짧거나 같은 길이의 배열로 만듭니다.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // 배열에서 자를 갯수가 필요하기 때문에 lengt-1이 아닌 length를 사용합니다.
        int m = nums1.length;
        int n = nums2.length;
        
        // 이진 탐색 범위 설정: nums1의 파티션 인덱스 i
        int low = 0;
        int high = m;
        
        // 전체 원소의 절반 개수 (중앙값 위치)
        // (m + n + 1) / 2를 사용하여 N이 홀수일 때 (N+1)/2, 짝수일 때 N/2가 되도록 합니다.
        int halfLen = (m + n + 1) / 2;

        while (low <= high) {
            // nums1의 파티션 인덱스 i
            int i = low + (high - low) / 2;
            
            // nums2의 파티션 인덱스 j (i + j = halfLen을 만족하도록 자동 계산)
            int j = halfLen - i;

            // --- 경계값 처리 및 유효성 검사 ---
            
            // nums1의 왼쪽 최댓값 (i=0이면 -INF, 아니면 nums1[i-1])
            int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            // nums1의 오른쪽 최솟값 (i=m이면 +INF, 아니면 nums1[i])
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            
            
            // nums2의 왼쪽 최댓값 (j=0이면 -INF, 아니면 nums2[j-1])
            int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            // nums2의 오른쪽 최솟값 (j=n이면 +INF, 아니면 nums2[j])
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];
            
            // --- 파티션 조건 검사 ---
            
            // 최적의 파티션을 찾은 경우: max(왼쪽) <= min(오른쪽)
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // 왼쪽 부분의 최댓값 (L)
                int maxLeft = Math.max(maxLeft1, maxLeft2);
                
                // 전체 길이가 홀수인 경우 (maxLeft가 중앙값)
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                
                // 전체 길이가 짝수인 경우 (maxLeft와 minRight의 평균이 중앙값)
                int minRight = Math.min(minRight1, minRight2);
                return (double)(maxLeft + minRight) / 2.0;

            } else if (maxLeft1 > minRight2) {
                // nums1의 왼쪽 부분이 너무 크므로 i를 줄여야 함
                high = i - 1;
            } else { // maxLeft2 > minRight1
                // nums1의 왼쪽 부분이 너무 작으므로 i를 늘려야 함
                low = i + 1;
            }
        }

        // 이 코드는 항상 유효한 입력을 가정하므로 도달하지 않아야 합니다.
        return 0.0;
    }
}