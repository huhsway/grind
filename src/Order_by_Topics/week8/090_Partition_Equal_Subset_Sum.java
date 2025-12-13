class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // If the total sum is odd, it's impossible to partition into two equal subsets.
        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        
        // A sum of 0 can always be formed (by picking no elements).
        dp[0] = true;

        for (int num : nums) {
            // Iterate backwards to avoid using the same number multiple times in a single subset sum calculation.
            for (int j = target; j >= num; j--) {
                // If a sum of (j - num) can be formed, then a sum of j can also be formed by including the current number.
                if (dp[j - num]) {
                    dp[j] = true;
                }
            }
        }
        
        // The final answer is whether we can form a sum equal to the target.
        return dp[target];
    }
}