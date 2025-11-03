class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> hashMap = new HashMap<>();

        hashMap.put('(', ')');
        hashMap.put('{', '}');
        hashMap.put('[', ']');

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (!stack.isEmpty() && hashMap.get(stack.peek()) == s.charAt(i)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}