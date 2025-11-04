class Solution {
    /**
     * 문자열 배열에서 가장 긴 공통 접두사를 찾습니다.
     * * @param strs 문자열 배열
     * @return 가장 긴 공통 접두사 문자열
     */
    public String longestCommonPrefix(String[] strs) {
        // 1. 입력 유효성 검사 (배열이 null이거나 비어있을 경우)
        if (strs == null || strs.length == 0) {
            return "";
        }

        // strs[0]을 기준 문자열로 사용
        String firstStr = strs[0];

        // 2. 기준 문자열의 각 문자를 순회 (i는 접두사의 길이)
        for (int i = 0; i < firstStr.length(); i++) {
            char currentChar = firstStr.charAt(i); // 기준 문자

            // 3. 나머지 모든 문자열과 현재 문자를 비교
            for (int j = 1; j < strs.length; j++) {
                String currentStr = strs[j];

                // 4. 중단 조건:
                // a) 현재 문자열이 기준 문자열보다 짧거나 (인덱스 i에 문자가 없거나),
                // b) 현재 문자열의 i번째 문자가 기준 문자와 다르면 (불일치)
                if (i == currentStr.length() || currentStr.charAt(i) != currentChar) {
                    // 현재까지 확인한 접두사를 반환
                    // 인덱스 i까지 (i는 포함하지 않음)의 부분 문자열
                    return firstStr.substring(0, i);
                }
            }
        }

        // 5. 모든 문자열이 strs[0]를 접두사로 가진다면, strs[0] 전체가 접두사
        return firstStr;
    }
}