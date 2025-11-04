class Solution {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            // 왼쪽 포인터가 가리키는 벽이 오른쪽 포인터가 가리키는 벽보다 낮거나 같은 경우
            if (height[left] <= height[right]) {
                // 현재 왼쪽 포인터의 높이가 leftMax보다 크면 leftMax 갱신
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } 
                // 그렇지 않으면 빗물이 고일 수 있으므로 빗물의 양을 더해준다
                else {
                    totalWater += leftMax - height[left];
                }
                left++; // 왼쪽 포인터 한 칸 오른쪽으로 이동
            } 
            // 오른쪽 포인터가 가리키는 벽이 더 낮은 경우
            else {
                // 현재 오른쪽 포인터의 높이가 rightMax보다 크면 rightMax 갱신
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } 
                // 그렇지 않으면 빗물이 고일 수 있으므로 빗물의 양을 더해준다
                else {
                    totalWater += rightMax - height[right];
                }
                right--; // 오른쪽 포인터 한 칸 왼쪽으로 이동
            }
        }
        
        return totalWater;
    }
}