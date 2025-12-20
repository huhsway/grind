import java.util.*;

class Solution {

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        Set<List<Integer>> resultSet = new HashSet<>();
        Map<String, Integer> reversedWordsMap = new HashMap<>();

        // 1. 모든 단어의 '역순'을 키로, 원본 인덱스를 값으로 맵에 저장
        for (int i = 0; i < words.length; i++) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            reversedWordsMap.put(reversed, i);
        }

        // 2. 모든 단어를 순회하며 분할 및 검색
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int n = word.length();
            
            for (int k = 0; k <= n; k++) {
                String p1 = word.substring(0, k);
                String p2 = word.substring(k);

                // Case 1: p2가 팰린드롬이면, p1의 역순을 뒤에 붙여서 팰린드롬 완성
                // word[i] + word[j] 형태 → [i, j]
                if (isPalindrome(p2)) {
                    if (reversedWordsMap.containsKey(p1)) {
                        int j = reversedWordsMap.get(p1);
                        if (i != j) { 
                            resultSet.add(Arrays.asList(i, j));
                        }
                    }
                }

                // Case 2: p1이 팰린드롬이면, p2의 역순을 앞에 붙여서 팰린드롬 완성
                // word[j] + word[i] 형태 → [j, i]
                // k != 0 조건: k=0이면 Case 1과 중복
                if (k != 0 && isPalindrome(p1)) {
                    if (reversedWordsMap.containsKey(p2)) {
                        int j = reversedWordsMap.get(p2);
                        if (i != j) {
                            resultSet.add(Arrays.asList(j, i));
                        }
                    }
                }
            }
        }
        
        return new ArrayList<>(resultSet);
    }
}