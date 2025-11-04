import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String largestNumber(int[] nums) {
        // 1. int 배열을 String 배열로 변환
        String[] sNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sNums[i] = String.valueOf(nums[i]);
        }

        // 2. 사용자 정의 Comparator를 사용하여 정렬
        // Comparator는 두 문자열 s1, s2를 비교하여 순서를 결정합니다.
        // (s2 + s1).compareTo(s1 + s2)는 's2s1'이 's1s2'보다 큰 경우(양수)
        // s2가 s1보다 앞에 오도록 (내림차순 정렬) 합니다.
        // 우리는 "더 큰 수"를 만들기 때문에 내림차순으로 정렬해야 합니다.
        // 즉, s1이 s2보다 앞에 와야 더 큰 수를 만들 때, s1s2가 s2s1보다 커야 합니다.
        // 그러나 Arrays.sort의 compare(a, b)는 양수를 반환하면 a와 b의 순서를 바꿉니다.
        // 그러므로, (s2 + s1)이 (s1 + s2)보다 커서 s2가 앞에 와야 할 때 양수를 반환하도록 합니다.
        Arrays.sort(sNums, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // 's2 + s1'과 's1 + s2'를 비교
                String order1 = s1 + s2;
                String order2 = s2 + s1;
                
                // order2 (s2s1)가 order1 (s1s2)보다 크다면 s2가 s1보다 앞에 와야 하므로 양수 반환
                // 즉, 내림차순 정렬 (Lexicographically reverse order)
                return order2.compareTo(order1);
            }
        });

        // 3. 엣지 케이스 처리: 모든 숫자가 0인 경우 ("000..."이 아닌 "0" 반환)
        if (sNums[0].equals("0")) {
            return "0";
        }

        // 4. 정렬된 문자열들을 하나로 합쳐서 반환
        StringBuilder sb = new StringBuilder();
        for (String s : sNums) {
            sb.append(s);
        }

        return sb.toString();
    }
}