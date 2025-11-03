import java.util.Arrays;

// 네이버 면접에서도 나왔음
public class Solution {

    // public int[] rotate(int[] nums, int k) {

    //     int n = nums.length - 1;
    //     for (int i = 0; i < k; i++) {

    //         int last = nums[n];

    //         for (int j = n; j > 0; j--) {
    //             nums[j] = nums[j-1];
    //         }

    //         nums[0] = last;

    //     }
    //     return nums;
    // }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        // 보조 배열 생성
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            // 원본 배열의 i번째 요소가 이동할 새로운 위치를 계산
            int newIndex = (i + k) % n;
            result[newIndex] = nums[i];
        }

        // 결과를 원본 배열로 복사
        for (int i = 0; i < n; i++) {
            nums[i] = result[i];
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int t = 3;
        RotateArray solution = new RotateArray();
        System.out.println(Arrays.toString(solution.rotateArray(arr, t)));
    }

}
