class Solution {
    public boolean isPalindrome(String s) {
        String cleanString = s.replaceAll("[^a-zA-Z0-9]", "");
        cleanString = cleanString.toLowerCase();

        if (cleanString.equals("")) return true;

        int left = 0;
        int right = cleanString.length()-1;

        while(left < right) {
            if (cleanString.charAt(left) != cleanString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

// class Solution {

//     public boolean isPalindrome(String s) {

//      String filtered = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

//      return filtered.equals(new StringBuilder(filtered).reverse().toString());
//     }
// }