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

        // 3. 숫자 처리
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
            
            // 오버플로우 체크 - 부호 적용한 값으로 체크
            long signedResult = result * sign;
            // 음수가 1 더 커서 두번 체크
            // Integer.MAX_VALUE =  2147483647  (+21억)
            // Integer.MIN_VALUE = -2147483648  (-21억)
            if (signedResult > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (signedResult < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (result * sign);
    }
}