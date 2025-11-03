import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestPalindrome(String s) {
        // 문자와 해당 문자의 빈도수를 저장할 HashMap을 선언합니다.
        Map<Character, Integer> charCount = new HashMap<>();

        // 문자열을 순회하며 각 문자의 빈도수를 계산합니다.
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        int length = 0;
        boolean hasOdd = false;

        // HashMap의 값(빈도수)을 순회하며 팰린드롬 길이를 계산합니다.
        for (int freq : charCount.values()) {
            // 빈도수가 짝수인 문자는 모두 팰린드롬을 구성할 수 있습니다.
            length += (freq / 2) * 2;
            
            // 빈도수가 홀수인 문자가 있다면, 팰린드롬의 중앙에 올 수 있는 문자가 있다는 것을 표시합니다.
            if (freq % 2 == 1) {
                hasOdd = true;
            }
        }

        // 홀수 빈도수를 가진 문자가 하나라도 있으면 최종 길이에 1을 더합니다.
        if (hasOdd) {
            length++;
        }

        return length;
    }
}