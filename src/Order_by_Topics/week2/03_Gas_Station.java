class Solution {
    /**
     * LeetCode 134. Gas Station 문제의 O(n) 그리디 풀이
     * * @param gas 각 주유소의 주유량
     * @param cost 각 주유소에서 다음 주유소로 이동하는 비용
     * @return 순환 가능한 출발 주유소의 인덱스, 불가능하면 -1
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        
        // 1. 전체 순이익(total_gain)을 계산하여 순환 가능 여부 확인
        int totalGain = 0;
        for (int i = 0; i < n; i++) {
            totalGain += gas[i] - cost[i];
        }

        // 총 주유량이 총 비용보다 적으면 순환 불가능
        if (totalGain < 0) {
            return -1;
        }

        // 2. 순환 가능한 경우, 유일한 출발점(start)을 찾습니다.
        int currentTank = 0; // 현재 탱크에 남은 기름양 (순이익)
        int startStation = 0; // 유효한 출발점 후보 인덱스

        for (int i = 0; i < n; i++) {
            int gain = gas[i] - cost[i];
            currentTank += gain;

            // 현재 주유소까지 이동했을 때 탱크가 바닥나면 (순이익이 음수)
            if (currentTank < 0) {
                // 이 지점까지의 경로는 유효하지 않으므로,
                // 다음 주유소(i + 1)를 새로운 출발점 후보로 설정하고
                // 탱크를 다시 0으로 리셋합니다.
                startStation = i + 1;
                currentTank = 0;
            }
        }

        // 전체 순이익이 0 이상임을 이미 확인했으므로, 
        // 최종적으로 설정된 startStation이 유일한 정답입니다.
        return startStation;
    }
}

// 아래 로직에서 시게방향 순회하는 방식으로 풀어봄 (start = i) % n 근데 이중 for문이라 시간복잡도 터짐
// class Solution {
//     public int canCompleteCircuit(int[] gas, int[] cost) {
//         int n = gas.length;

//         // 0. (최적화) 전체 gas와 cost의 총합을 먼저 비교하여 불가능한 경우를 빠르게 제외
//         int totalGas = 0;
//         int totalCost = 0;
//         for (int i = 0; i < n; i++) {
//             totalGas += gas[i];
//             totalCost += cost[i];
//         }
//         if (totalGas < totalCost) {
//             return -1; // 한 바퀴를 돌기에 전체 연료가 부족함
//         }

//         // 1. 모든 주유소를 출발점(start)으로 시도 (O(n))
//         for (int start = 0; start < n; start++) {
//             int currentTank = 0; // 현재 탱크에 있는 연료량
//             boolean canComplete = true;

//             // 2. 현재 출발점(start)에서 시작하여 시계 방향으로 순회 시도 (O(n))
//             for (int i = 0; i < n; i++) {
//                 // 현재 순회할 주유소의 인덱스 (원형 배열 처리: start에서 i만큼 떨어진 곳)
//                 int currentStationIndex = (start + i) % n;
                
//                 // 연료 주유
//                 currentTank += gas[currentStationIndex];

//                 // 다음 주유소로 이동하는 비용
//                 int travelCost = cost[currentStationIndex];

//                 // 다음 주유소로 이동
//                 currentTank -= travelCost;

//                 // 이동 후 연료가 음수가 되면 실패
//                 if (currentTank < 0) {
//                     canComplete = false;
//                     break; // 이 출발점은 실패했으므로, 더 이상 순회할 필요 없음
//                 }
//             }

//             // 3. 순회를 성공적으로 완료했다면 정답 반환
//             if (canComplete) {
//                 return start;
//             }
//         }

//         // (선택 사항: 0 단계에서 이미 처리됨) 모든 시도가 실패하면 -1 반환
//         return -1;
//     }
// }
