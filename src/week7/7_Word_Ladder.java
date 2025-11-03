import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1. 빠른 조회를 위해 wordList를 Set으로 변환
        Set<String> wordSet = new HashSet<>(wordList);
        
        // endWord가 Set에 없으면 변환 불가능
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // 2. BFS를 위한 큐 초기화 (시작 단어와 step 수)
        // 큐에는 현재 단계에서 탐색할 단어만 저장합니다.
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        // 이미 방문한 단어를 저장하는 Set (중복 방문 및 사이클 방지)
        // beginWord는 큐에 추가했으므로 방문 처리할 필요가 없습니다.
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        int step = 1; // 변환 시퀀스의 길이 (beginWord 포함)

        // 3. BFS 시작
        while (!queue.isEmpty()) {
            step++; // 다음 레벨로 이동, 변환 길이 1 증가
            int levelSize = queue.size(); // 현재 레벨의 단어 수

            // 현재 레벨의 모든 단어를 처리
            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.poll();

                // 4. 가능한 모든 '한 글자 변환' 시도 (Neighbor 탐색)
                // String을 직접 수정하는 대신, char 배열이나 StringBuilder를 사용하는 것이 효율적입니다.
                char[] charArray = currentWord.toCharArray();
                
                for (int j = 0; j < charArray.length; j++) {
                    char originalChar = charArray[j];
                    
                    // 'a'부터 'z'까지 26가지 문자로 변경 시도
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue; // 원래 문자와 같으면 건너뜁니다.

                        charArray[j] = c;
                        String nextWord = new String(charArray);

                        // (a) endWord에 도달했는지 확인
                        if (nextWord.equals(endWord)) {
                            return step; // 최단 경로를 찾았으므로 현재 step을 반환
                        }

                        // (b) 유효한 단어이고, 아직 방문하지 않았는지 확인
                        if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                            visited.add(nextWord);
                            queue.offer(nextWord);
                        }
                    }
                    // 다음 반복을 위해 원래 문자로 되돌립니다.
                    charArray[j] = originalChar; 
                }
            }
        }

        // 큐가 비었는데 endWord에 도달하지 못한 경우 (변환 불가능)
        return 0;
    }
}