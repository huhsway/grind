class Solution {
    public boolean isValid(String s) {
        if (s.length() == 1) return false;

        Map <Character, Character> pairs = new HashMap<>();
        
        pairs.put('(', ')');
        pairs.put('{','}');  
        pairs.put('[', ']');

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (pairs.containsKey(current)) {
                stack.push(current);
                continue;
            }

            if (!stack.isEmpty() && pairs.get(stack.peek()) == current) {
                stack.pop();
                continue;
            }
            return false;
        }

        return stack.isEmpty();
    }
}