import java.util.PriorityQueue;

// ListNode class is usually provided in LeetCode problems
// public class ListNode {
//     int val;
//     ListNode next;
//     ListNode() {}
//     ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// }

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Handle edge cases
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Create a min-heap (PriorityQueue)
        // The comparator ensures the heap sorts based on the node's value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // Add the head of each list to the min-heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }
        
        // Dummy head to simplify list construction
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Process nodes from the min-heap until it's empty
        while (!minHeap.isEmpty()) {
            // Get the node with the smallest value
            ListNode smallest = minHeap.poll();
            
            // Append it to our merged list
            current.next = smallest;
            current = current.next;
            
            // If the extracted node has a next node, add it to the heap
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }
        
        return dummy.next;
    }
}