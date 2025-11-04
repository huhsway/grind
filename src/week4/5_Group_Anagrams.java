class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashMap = new HashMap<>();

        Arrays.stream(strs)
                .forEach(str -> {
                    char[] charArray = str.toCharArray();
                    Arrays.sort(charArray);
                    String sortedStr = new String(charArray);

                    hashMap.computeIfAbsent(sortedStr, key -> new ArrayList<>()).add(str);
                });

        return new ArrayList<>(hashMap.values());
    }
}