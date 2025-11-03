class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        
        // 결과를 저장할 배열을 생성합니다.
        int[] result = new int[n];
        
        // 1. 투 포인터 설정: 
        // left: 배열의 시작 (가장 작은 값)
        // right: 배열의 끝 (가장 큰 값)
        int left = 0;
        int right = n - 1;
        
        // 2. 결과 배열을 뒤에서부터 채우기 위한 포인터 설정
        // 이 포인터는 가장 큰 제곱 값부터 저장하기 위해 배열의 맨 끝(n-1)에서 시작합니다.
        int writePtr = n - 1;
        
        // left 포인터가 right 포인터를 지나치기 전까지 반복합니다.
        while (left <= right) {
            
            // 왼쪽 요소의 절대값 제곱과 오른쪽 요소의 절대값 제곱을 비교합니다.
            // Math.abs()를 사용하거나, 단순히 제곱 값을 직접 비교해도 됩니다.
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            if (leftSquare > rightSquare) {
                // 왼쪽 제곱 값이 더 크다면, 그 값을 결과 배열의 맨 뒤에 저장하고
                result[writePtr] = leftSquare;
                // 왼쪽 포인터를 오른쪽으로 이동시킵니다.
                left++;
            } else {
                // 오른쪽 제곱 값이 더 크거나 같다면, 그 값을 결과 배열의 맨 뒤에 저장하고
                result[writePtr] = rightSquare;
                // 오른쪽 포인터를 왼쪽으로 이동시킵니다.
                right--;
            }
            
            // 다음으로 작은 제곱 값을 저장하기 위해 쓰기 포인터를 앞으로 이동시킵니다.
            writePtr--;
        }
        
        return result;
    }
}
