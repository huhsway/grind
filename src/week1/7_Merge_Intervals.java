class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) return intervals;
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        int[] previous = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (previous[1] >= intervals[i][0]) {
                previous[1] = Math.max(previous[1], intervals[i][1]);
            } else {
                result.add(previous);
                previous = intervals[i];
            }
        }

        result.add(previous);

        return result.toArray(new int[result.size()][]);
    }
}