class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        // 1. 공백 문자 처리
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. 부호 처리
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        long result = 0;

        // 3. 숫자 처리 및 오버플로우 체크
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            
            // 오버플로우 체크
            if (sign == 1 && result > (Integer.MAX_VALUE - digit) / 10) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < (Integer.MIN_VALUE + digit) / 10) {
                return Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return (int) (result * sign);
    }
}