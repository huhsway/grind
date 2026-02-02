/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // 1칸씩 이동
            fast = fast.next.next;    // 2칸씩 이동
            if (slow == fast) return true; // 만난다면 cycle 존재
        }

        return false; // fast가 null에 도달하면 cycle 없음
    }
}