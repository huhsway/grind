import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Solution {

    /**
     * 문자열 s에서 문자열 p의 모든 애너그램의 시작 인덱스를 찾습니다.
     *
     * @param s 메인 문자열
     * @param p 찾고자 하는 애너그램의 기준 문자열
     * @return 애너그램의 시작 인덱스 리스트
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();

        // 1. 예외 처리: s의 길이가 p의 길이보다 짧으면 애너그램은 존재할 수 없습니다.
        if (sLen < pLen) {
            return result;
        }

        // 2. 빈도 배열 초기화 (알파벳 소문자 'a'~'z' 26개)
        int[] pFreq = new int[26]; // p의 문자 빈도
        int[] sFreq = new int[26]; // 현재 슬라이딩 윈도우의 문자 빈도

        // 3. 첫 번째 윈도우와 p의 빈도 계산
        for (int i = 0; i < pLen; i++) {
            // p의 빈도 증가
            pFreq[p.charAt(i) - 'a']++;
            // s의 첫 pLen 길이 윈도우의 빈도 증가
            sFreq[s.charAt(i) - 'a']++;
        }

        // 4. 첫 번째 윈도우 확인
        if (Arrays.equals(pFreq, sFreq)) {
            result.add(0);
        }

        // 5. 슬라이딩 윈도우 이동 시작 (i는 윈도우의 오른쪽 끝 인덱스가 될 것입니다)
        // 윈도우는 [i - pLen + 1, i] 범위입니다.
        for (int i = pLen; i < sLen; i++) {
            // 새 문자 추가 (윈도우 오른쪽 이동)
            // 현재 윈도우의 끝 (i)에 있는 문자의 빈도를 증가시킵니다.
            sFreq[s.charAt(i) - 'a']++;

            // 이전 문자 제거 (윈도우 왼쪽 이동)
            // 윈도우에서 벗어나는 문자 (i - pLen)의 빈도를 감소시킵니다.
            sFreq[s.charAt(i - pLen) - 'a']--;

            // 6. 애너그램 확인
            // 두 빈도 배열이 같으면 애너그램입니다.
            if (Arrays.equals(pFreq, sFreq)) {
                // 애너그램이 발견되면 현재 윈도우의 시작 인덱스 (i - pLen + 1)를 추가합니다.
                result.add(i - pLen + 1);
            }
        }

        return result;
    }
}