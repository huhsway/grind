class Solution {
    public int maxProfit(int[] prices) {
        
        int start = prices[0];
        int maxProfit = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > start) {
                maxProfit = Math.max(maxProfit, prices[i] - start);
            } else {
                start = prices[i];
            }
        }

        return maxProfit == Integer.MIN_VALUE ? 0 : maxProfit;

    }
}