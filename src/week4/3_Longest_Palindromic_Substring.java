public class Solution {
    
    // 중심에서 양쪽으로 확장하는 헬퍼 함수
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 팰린드롬의 길이 반환
        return right - left - 1;
    }
    
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0;
        int maxLength = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // 홀수 길이 팰린드롬 (중심이 한 글자)
            int len1 = expandAroundCenter(s, i, i);
            
            // 짝수 길이 팰린드롬 (중심이 두 글자)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // 둘 중 더 긴 것 선택
            int len = Math.max(len1, len2);
            
            // 최대 길이 업데이트
            if (len > maxLength) {
                maxLength = len;
                start = i - (len - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLength);
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        System.out.println(solution.longestPalindrome("babad"));   // "bab" or "aba"
        System.out.println(solution.longestPalindrome("cbbd"));    // "bb"
        System.out.println(solution.longestPalindrome("abbba"));   // "abbba"
        System.out.println(solution.longestPalindrome("aabbaa"));  // "aabbaa"
    }
}