class Solution {
    public int maxProfit(int[] prices) {
        
        int start = prices[0];
        int maxValue = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < start) {
                start = prices[i];
            } else {
                maxValue = Math.max(maxValue, prices[i] - start);
            }
        }

        return maxValue;

    }
}