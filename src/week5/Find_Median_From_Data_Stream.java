import java.util.PriorityQueue;
import java.util.Collections;

class MedianFinder {
    // 작은 절반을 저장하는 최대 힙
    private PriorityQueue<Integer> maxHeap;
    // 큰 절반을 저장하는 최소 힙
    private PriorityQueue<Integer> minHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        // 자바의 PriorityQueue는 기본적으로 최소 힙이므로,
        // Collections.reverseOrder()를 사용하여 최대 힙을 구현합니다.
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // 1. 숫자를 힙에 추가
        // maxHeap의 크기가 minHeap보다 크거나 같을 때
        if (maxHeap.size() <= minHeap.size()) {
            // 새 숫자를 먼저 minHeap에 넣은 후, 가장 작은 값을 maxHeap으로 옮깁니다.
            // 이렇게 하면 minHeap의 모든 요소가 maxHeap의 요소보다 크게 유지됩니다.
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        } else {
            // minHeap의 크기가 maxHeap보다 작을 때
            // 새 숫자를 먼저 maxHeap에 넣은 후, 가장 큰 값을 minHeap으로 옮깁니다.
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        // 2. 중간값 계산
        // 두 힙의 크기가 같으면 짝수 개
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } 
        // maxHeap의 크기가 더 크면 홀수 개
        else {
            return maxHeap.peek();
        }
    }
}