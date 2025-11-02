import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 1. 탐욕 알고리즘을 위해 구간을 '종료 시점'을 기준으로 오름차순 정렬합니다.
        // 자바에서 2차원 배열의 정렬은 Comparator를 사용하여 정의합니다.
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int n = intervals.length;
        
        // 2. 겹치지 않는 구간의 개수 (우리가 '유지'할 구간의 최대 개수)
        int count = 1; 
        
        // 이전에 선택된 구간의 종료 시점을 기록합니다.
        int prevEnd = intervals[0][1]; 

        // 3. 정렬된 배열을 순회하며 겹치지 않는 구간을 선택합니다.
        for (int i = 1; i < n; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            // 현재 구간의 시작 시점이 이전에 선택된 구간의 종료 시점보다 크거나 같다면
            // (즉, 겹치지 않는다면) 이 구간을 선택합니다.
            // Note: [1, 2]와 [2, 3]은 겹치지 않는 것으로 간주됩니다.
            if (currentStart >= prevEnd) {
                // 이 구간을 유지할 구간으로 선택합니다.
                count++; 
                
                // 선택된 구간의 종료 시점을 갱신합니다.
                prevEnd = currentEnd;
            }
            // 겹친다면 (currentStart < prevEnd), 현재 구간은 제거(무시)하고
            // prevEnd는 갱신하지 않고 다음 구간으로 넘어갑니다.
            // (왜냐하면 종료 시점이 더 빠른 prevEnd 구간이 더 좋은 선택이기 때문입니다.)
        }

        // 4. 최소 제거 개수 반환
        // (전체 구간 개수) - (겹치지 않게 유지한 최대 구간 개수)
        return n - count;
    }
}
