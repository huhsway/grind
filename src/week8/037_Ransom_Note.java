class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] charCounts = new int[26];

        for (char c : magazine.toCharArray()) {
            charCounts[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            charCounts[c - 'a']--;
        
            if (charCounts[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}