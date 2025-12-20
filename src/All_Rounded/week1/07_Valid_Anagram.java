class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        // 만약 출력 하려면
        // sMap.forEach((key, value) -> System.out.println(key + ": " + value));

        // return Arrays.equals(sMap.entrySet().toArray(), tMap.entrySet().toArray());

    // 두 맵을 비교하는 올바른 방법입니다.
    // 맵의 크기가 같고, 모든 키-값 쌍이 동일한지 확인하며,
    // 요소의 삽입 순서와는 상관없이 올바르게 비교합니다.
         return sMap.equals(tMap);

    }
}

// class Solution {
//     public boolean isAnagram(String s, String t) {
        
//         if (s.length() != t.length()) return false;

//         int[] arr = new int[26];

//         for (int i = 0; i < s.length(); i++) {
//             arr[s.charAt(i) - 'a']++;
//             arr[t.charAt(i) - 'a']--;
//         }

//         for (int i = 0; i < 26; i++) {
//             if (arr[i] != 0) return false;
//         }

//         return true;
        
//     }
// }