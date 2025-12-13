class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // endWord가 wordList에 없으면 불가능
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        // BFS를 위한 큐
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        
        // 방문 체크를 위해 wordSet에서 제거
        wordSet.remove(beginWord);
        
        int level = 1; // beginWord도 카운트에 포함
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // 현재 레벨의 모든 단어 처리
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // endWord에 도달하면 반환
                if (currentWord.equals(endWord)) {
                    return level;
                }
                
                // 모든 가능한 변환 시도
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    // 각 위치에서 a~z로 변경 시도
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        
                        // wordList에 있는 단어면 큐에 추가
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord); // 방문 처리
                        }
                    }
                    
                    // 원래 문자로 복원
                    wordChars[j] = originalChar;
                }
            }
            
            level++; // 다음 레벨로
        }
        
        return 0; // endWord에 도달할 수 없음
    }
}