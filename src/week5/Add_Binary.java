class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry == 1) {
            int sum = carry;
            
            // Add bit from string 'a' if it exists
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            
            // Add bit from string 'b' if it exists
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            // Append the current bit to the result
            sb.append(sum % 2);
            
            // Update the carry for the next iteration
            carry = sum / 2;
        }

        // The result is built in reverse, so reverse it back
        return sb.reverse().toString();
    }
}