class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }
    
    private void backtrack(List<String> result, StringBuilder current, int open, int close, int n) {
        // 기저 조건: 길이가 2n이면 완성
        if (current.length() == n * 2) {
            result.add(current.toString());
            return;
        }
        
        // 열린 괄호 추가 (n개 미만일 때)
        if (open < n) {
            current.append('(');
            backtrack(result, current, open + 1, close, n);
            current.deleteCharAt(current.length() - 1);
        }
        
        // 닫힌 괄호 추가 (열린 괄호보다 적을 때)
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, n);
            current.deleteCharAt(current.length() - 1);
        }
    }
}