class Solution {
    public String largestNumber(int[] nums) {
        // String 배열로 변환
        String[] sNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sNums[i] = String.valueOf(nums[i]);
        }

        // 람다식으로 정렬
        Arrays.sort(sNums, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        // 엣지 케이스
        if (sNums[0].equals("0")) {
            return "0";
        }

        // 결과
        StringBuilder sb = new StringBuilder();
        for (String s : sNums) {
            sb.append(s);
        }

        return sb.toString();
    }
}
