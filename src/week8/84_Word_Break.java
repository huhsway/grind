import java.util.List;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 사전의 단어를 빠르게 찾기 위해 HashSet으로 변환합니다.
        Set<String> wordSet = new HashSet<>(wordDict);
        
        // dp[i]는 s의 첫 i개 문자가 분할될 수 있는지 여부입니다.
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // 빈 문자열은 항상 분할 가능하다고 간주합니다.

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // s.substring(j, i)는 s의 j번째부터 i-1번째까지의 부분 문자열입니다.
                // dp[j]가 true이고, 남은 부분 문자열이 사전에 있는 단어라면
                // dp[i]도 true가 됩니다.
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 다음 i로 넘어갑니다.
                }
            }
        }

        // 최종 결과는 s 전체 문자열이 분할될 수 있는지 여부입니다.
        return dp[s.length()];
    }
}