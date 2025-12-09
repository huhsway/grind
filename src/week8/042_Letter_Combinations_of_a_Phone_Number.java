class Solution {
    // 숫자와 문자 매핑 (전화기 키패드)
    String[] phoneLetters = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };
    
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // 빈 문자열이면 빈 리스트 반환
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        // 백트래킹 시작
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }
    
    /**
     * 백트래킹 함수
     * @param result: 최종 결과를 저장할 리스트
     * @param current: 현재까지 만든 조합
     * @param digits: 입력 숫자 문자열
     * @param index: 현재 처리 중인 digits의 인덱스
     */
    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // 기저 조건: 모든 숫자를 다 처리했으면
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        // 현재 숫자에 해당하는 문자들 가져오기
        char digit = digits.charAt(index);
        String letters = phoneLetters[digit - '0'];  // '2' - '0' = 2 (인덱스)
        
        // 각 문자에 대해 재귀 호출
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            
            // 1. 선택
            current.append(letter);
            
            // 2. 다음 단계로 진행
            backtrack(result, current, digits, index + 1);
            
            // 3. 선택 취소 (백트래킹)
            current.deleteCharAt(current.length() - 1);
        }
    }
}