package week1;

public class ValidPalindrome {
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
