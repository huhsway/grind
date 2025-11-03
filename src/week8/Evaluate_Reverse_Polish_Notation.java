import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    /**
     * 후위 표기법(RPN) 배열을 계산합니다.
     * @param tokens RPN으로 표현된 토큰(숫자 또는 연산자) 배열
     * @return 계산 결과
     */
    public int evalRPN(String[] tokens) {
        // 계산을 위해 스택을 사용합니다.
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            // 토큰이 연산자인지 확인하고 if-else if 구조로 처리
            if (token.equals("+")) {
                int b_plus = stack.pop();
                int a_plus = stack.pop();
                stack.push(a_plus + b_plus);
            } else if (token.equals("-")) {
                int b_minus = stack.pop();
                int a_minus = stack.pop();
                stack.push(a_minus - b_minus);
            } else if (token.equals("*")) {
                int b_mul = stack.pop();
                int a_mul = stack.pop();
                stack.push(a_mul * b_mul);
            } else if (token.equals("/")) {
                int b_div = stack.pop();
                int a_div = stack.pop();
                stack.push(a_div / b_div);
            } else {
                // 토큰이 연산자가 아닌 경우, 즉 숫자인 경우:
                // 정수로 변환하여 스택에 push합니다.
                stack.push(Integer.parseInt(token));
            }
        }

        // 모든 토큰을 처리한 후 스택에 남은 최종 결과값을 반환합니다.
        return stack.pop();
    }
}
