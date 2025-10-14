import java.util.Stack;

class Solution {
    /**
     * 기본 계산기 (덧셈, 뺄셈, 괄호) 수식을 평가합니다.
     * @param s 수식 문자열
     * @return 계산 결과
     */
    public int calculate(String s) {
        int result = 0; // 현재 계산 레벨의 최종 결과
        int sign = 1;   // 현재 숫자에 적용할 부호 (1: +, -1: -)
        
        // 괄호 밖의 (누적된 결과, 괄호 바로 앞의 부호) 쌍을 저장하는 스택
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // 1. 숫자 처리 (여러 자릿수일 수 있음)
                long num = 0; // 숫자가 클 수 있으므로 long으로 시작
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--; // while 루프에서 한 칸 더 갔으므로 되돌림

                // 현재 숫자를 현재 부호와 곱하여 결과에 더함
                result += sign * num;

            } else if (c == '+') {
                // 2. 덧셈 부호: 다음 숫자는 양수(+)
                sign = 1;

            } else if (c == '-') {
                // 3. 뺄셈 부호: 다음 숫자는 음수(-)
                sign = -1;

            } else if (c == '(') {
                // 4. 여는 괄호: 현재까지의 상태(result, sign)를 스택에 저장
                // 괄호 안의 연산은 독립적인 새로운 계산으로 시작합니다.
                
                // (1) 현재까지 계산된 누적 결과 저장
                stack.push(result);
                // (2) 괄호 바로 앞에 있던 부호 저장
                stack.push(sign); 
                
                // 괄호 안의 새로운 계산 시작을 위해 초기화
                result = 0;
                sign = 1;

            } else if (c == ')') {
                // 5. 닫는 괄호: 괄호 안의 계산(result)을 괄호 밖의 상태와 결합
                
                // (1) 괄호 바로 앞의 부호 복원 및 적용 (예: -(4+5+2))
                int prevSign = stack.pop(); 
                // (2) 괄호 밖에서 계산된 누적 결과 복원
                int prevResult = stack.pop();
                
                // 괄호 안의 결과(result)에 괄호 앞의 부호(prevSign)를 적용한 후,
                // 괄호 밖의 이전 결과(prevResult)에 더합니다.
                result = prevResult + prevSign * result;

            }
            // 6. 공백은 무시
        }

        return result;
    }
}