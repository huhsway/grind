import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 9개의 행, 9개의 열, 9개의 3x3 박스에 대한 Set 배열을 초기화합니다.
        // 각 Set은 해당 영역에 이미 나타난 숫자를 저장합니다.
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];
        
        // 배열의 각 요소를 실제 HashSet 인스턴스로 초기화
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // 9x9 보드를 순회
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char digit = board[i][j];

                // 빈 칸은 건너뜁니다.
                if (digit == '.') {
                    continue;
                }

                // 1. 행 검사
                if (rows[i].contains(digit)) {
                    return false;
                }
                rows[i].add(digit);

                // 2. 열 검사
                if (cols[j].contains(digit)) {
                    return false;
                }
                cols[j].add(digit);

                // 3. 3x3 박스 검사
                
                // 박스 인덱스 계산 공식: 
                // i/3: 0~2, j/3: 0~2
                // (i/3) * 3: 현재 행 블록의 시작 인덱스 (0, 3, 6)
                // (j/3): 현재 열 블록의 오프셋 (0, 1, 2)
                int boxIndex = (i / 3) * 3 + (j / 3);

                if (boxes[boxIndex].contains(digit)) {
                    return false;
                }
                boxes[boxIndex].add(digit);
            }
        }

        // 모든 검사를 통과했다면 유효함
        return true;
    }
}