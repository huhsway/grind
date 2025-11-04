import java.util.*;

class Solution {

    /**
     * 문자열이 팰린드롬인지 확인하는 헬퍼 메서드
     */
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
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> reversedWordsMap = new HashMap<>();

        // 1. 모든 단어의 '역순'과 인덱스를 맵에 저장
        for (int i = 0; i < words.length; i++) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            reversedWordsMap.put(reversed, i);
        }

        // 2. 모든 단어를 순회하며 분할 및 검색
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int n = word.length();
            
            // word를 P1(prefix)과 P2(suffix)로 분할 (k는 P1의 길이)
            // k=0일 때는 P1="", P2=word
            // k=n일 때는 P1=word, P2=""
            for (int k = 0; k <= n; k++) {
                String P1 = word.substring(0, k); // 접두사
                String P2 = word.substring(k);    // 접미사

                // Case 1: P2가 팰린드롬이고, P1의 역순(맵의 키)이 존재할 때 (w_j + w_i)
                if (isPalindrome(P2)) {
                    // P1의 역순은 맵에 저장된 키 그 자체입니다.
                    if (reversedWordsMap.containsKey(P1)) {
                        int j = reversedWordsMap.get(P1);
                        // 다른 단어여야 하고 (i != j),
                        // word가 비어있지 않아야 합니다 (공백 문자열에 대한 중복 쌍 방지).
                        // k=0일 때 P1이 ""이면 맵에 존재하는 경우 (Case 3)와 겹칠 수 있습니다.
                        if (i != j) { 
                            result.add(Arrays.asList(i, j));
                        }
                    }
                }

                // Case 2: P1이 팰린드롬이고, P2의 역순(맵의 키)이 존재할 때 (w_i + w_j)
                // k=n일 때만 확인 (P2가 ""일 때)
                // 또한, k=0일 때 P1이 ""이면 isPalindrome("")이 true이므로 k=0을 건너뛰어야 중복 방지
                // 하지만 k>0으로 조건을 걸면 "lls" + "s" 같은 쌍을 놓치므로,
                // P1이 팰린드롬이면서 P2가 맵에 존재할 때, P2가 ""이 아니면 Case 1과 중복이 없습니다.
                if (k != 0 && isPalindrome(P1)) {
                    if (reversedWordsMap.containsKey(P2)) {
                        int j = reversedWordsMap.get(P2);
                        if (i != j) {
                            result.add(Arrays.asList(j, i)); // 순서가 w_j + w_i가 아닌 w_i + w_j 임
                        }
                    }
                }
            }
        }
        
        // Case 3을 처리하며 발생하는 중복 제거
        // 특히 빈 문자열 ""이 있을 경우 복잡해지므로, Set을 사용하여 최종 중복을 제거할 수 있습니다.
        // 이 코드에서는 Case 1과 Case 2에서 적절한 i != j 조건과 k != 0 (Case 2에서) 조건으로 중복을 최소화했습니다.
        
        return result;
    }
}