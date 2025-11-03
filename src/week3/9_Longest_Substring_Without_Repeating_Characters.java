// class Solution {
//     public int lengthOfLongestSubstring(String s) {
//         if (s == null || s.isEmpty()) return 0;

//         String check = "";
//         int result = 0;

//         for (char c : s.toCharArray()) {
//             if (check.contains(String.valueOf(c))) {
//                 check = check.substring(check.indexOf(c) + 1);
//             }

//             check += c;
//             result = Math.max(result, check.length());
//         }

//         return result;
//     }
// }

import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 문자의 마지막 인덱스를 저장할 HashMap
        HashMap<Character, Integer> charMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 1. 현재 문자가 charMap에 이미 있고, 그 인덱스가 윈도우 안에 있다면
            if (charMap.containsKey(currentChar) && charMap.get(currentChar) >= left) {
                // 윈도우의 시작점(left)을 중복된 문자의 다음 인덱스로 옮깁니다.
                left = charMap.get(currentChar) + 1;
            }

            // 2. 현재 문자의 인덱스를 charMap에 업데이트합니다.
            charMap.put(currentChar, right);

            // 3. 현재 윈도우의 길이(right - left + 1)를 계산하고,
            // 최대 길이(maxLength)를 업데이트합니다.
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}