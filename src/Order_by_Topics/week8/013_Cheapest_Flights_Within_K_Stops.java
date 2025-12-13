class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
     
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[] {flight[1], flight[2]});
        }

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{src, 0}); // {city, cost}

        int stops = 0;

        while(!queue.isEmpty() && stops <= k) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int currentCity = current[0];
                int currentCost = current[1];

                for (int[] nextFlight : adj.get(currentCity)) {
                    int nextCity = nextFlight[0];
                    int price = nextFlight[1];
                    int newTotalCost = currentCost + price;

                    if (newTotalCost < minCost[nextCity]) {
                        minCost[nextCity] = newTotalCost;
                        queue.offer(new int[] {nextCity, newTotalCost});
                    }
                }
            }
            stops++;
        }

        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];

    }
}