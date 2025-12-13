import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        
        // 종료 시간(endTime)을 기준으로 작업 정렬
        Arrays.sort(jobs, (a, b) -> a.endTime - b.endTime);
        
        // Key: 종료 시간, Value: 해당 시간까지 얻은 최대 이익
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0); // 기본값: 시간 0에 이익 0
        
        for (Job job : jobs) {
            // 현재 작업 시작 시간 이전에 얻을 수 있는 최대 이익 찾기
            // floorEntry는 주어진 키보다 작거나 같은 키 중 가장 큰 항목을 반환
            int prevProfit = dp.floorEntry(job.startTime).getValue();
            
            // 현재 작업을 스케줄링했을 때의 이익 계산
            int currentProfit = prevProfit + job.profit;
            
            // TreeMap에 저장된 마지막 이익값
            int latestKnownProfit = dp.lastEntry().getValue();
            
            // 현재 작업의 종료 시간에 더 큰 이익을 얻을 수 있다면 맵 업데이트
            if (currentProfit > latestKnownProfit) {
                dp.put(job.endTime, currentProfit);
            }
        }
        
        return dp.lastEntry().getValue();
    }

    private static class Job {
        int startTime;
        int endTime;
        int profit;

        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }
}

// import java.util.Arrays;

// class Solution {
//     public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
//         int n = startTime.length;
//         Job[] jobs = new Job[n];
//         for (int i = 0; i < n; i++) {
//             jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
//         }
        
//         // 종료 시간 기준 정렬
//         Arrays.sort(jobs, (a, b) -> a.endTime - b.endTime);
        
//         // dp[i] = i번째 작업까지 고려했을 때 최대 이익
//         int[] dp = new int[n];
//         dp[0] = jobs[0].profit;
        
//         for (int i = 1; i < n; i++) {
//             // 현재 작업을 선택하지 않는 경우
//             int excludeProfit = dp[i - 1];
            
//             // 현재 작업을 선택하는 경우
//             // 현재 작업의 시작 시간 이전에 끝나는 작업 찾기 (이진 탐색)
//             int prevJobIdx = binarySearch(jobs, i, jobs[i].startTime);
//             int includeProfit = jobs[i].profit;
//             if (prevJobIdx != -1) {
//                 includeProfit += dp[prevJobIdx];
//             }
            
//             dp[i] = Math.max(excludeProfit, includeProfit);
//         }
        
//         return dp[n - 1];
//     }
    
//     // jobs[0..index-1] 중에서 endTime <= targetTime인 가장 오른쪽 인덱스 찾기
//     private int binarySearch(Job[] jobs, int index, int targetTime) {
//         int left = 0, right = index - 1;
//         int result = -1;
        
//         while (left <= right) {
//             int mid = left + (right - left) / 2;
            
//             if (jobs[mid].endTime <= targetTime) {
//                 result = mid;  // 답 후보 저장
//                 left = mid + 1;  // 더 오른쪽에 있는지 확인
//             } else {
//                 right = mid - 1;
//             }
//         }
        
//         return result;
//     }

//     private static class Job {
//         int startTime;
//         int endTime;
//         int profit;

//         Job(int startTime, int endTime, int profit) {
//             this.startTime = startTime;
//             this.endTime = endTime;
//             this.profit = profit;
//         }
//     }
// }