/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode nextTemp = curr.next; // 다음 노드를 임시 저장
            curr.next = prev;              // 현재 노드의 다음을 이전 노드로 연결
            prev = curr;                   // 이전 노드를 현재 노드로 이동
            curr = nextTemp;               // 현재 노드를 다음 노드로 이동
        }
        
        return prev; // 최종적으로 뒤집힌 리스트의 새로운 head인 prev를 반환
    }
}