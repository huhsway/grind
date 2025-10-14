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
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.endTime));
        
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