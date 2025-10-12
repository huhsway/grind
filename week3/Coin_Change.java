class Solution {
    public int coinChange(int[] coins, int amount) {
        // DP 배열 초기화: 각 금액을 만들 수 없는 상태로 시작
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;  // 불가능한 큰 값으로 초기화
        }
        dp[0] = 0;  // 0원은 0개의 동전으로 가능
        
        // 각 금액에 대해 최소 동전 개수 계산
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // 결과 반환: amount를 만들 수 없으면 -1
        return dp[amount] > amount ? -1 : dp[amount];
    }
}