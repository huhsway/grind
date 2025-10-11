class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    int b_plus = stack.pop();
                    int a_plus = stack.pop();
                    stack.push(a_plus + b_plus);
                    break;
                case "-":
                    int b_minus = stack.pop();
                    int a_minus = stack.pop();
                    stack.push(a_minus - b_minus);
                    break;
                case "*":
                    int b_mul = stack.pop();
                    int a_mul = stack.pop();
                    stack.push(a_mul * b_mul);
                    break;
                case "/":
                    int b_div = stack.pop();
                    int a_div = stack.pop();
                    stack.push(a_div / b_div);
                    break;
                default:
                    // 토큰이 숫자인 경우, 정수로 변환하여 스택에 push
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }

        return stack.pop();
    }
}