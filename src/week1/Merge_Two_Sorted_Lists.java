/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// 이런 구조라 생각하자
// class ListNode {
//     int val;
//     ListNode next;
//     ListNode(int val) { this.val = val; }
// }

// public class Main {
//     public static void main(String[] args) {
//         // 노드 생성
//         ListNode node1 = new ListNode(1);
//         ListNode node2 = new ListNode(2);
//         ListNode node3 = new ListNode(4);

//         // 노드 연결
//         node1.next = node2;
//         node2.next = node3;

//         // list1은 첫 번째 노드 (값 1)를 가리킴
//         ListNode list1 = node1;

//         // 값을 하나씩 출력해보면서 확인
//         ListNode current = list1;
//         while (current != null) {
//             System.out.print(current.val + " ");
//             current = current.next;
//         }
//         // 출력 결과: 1 2 4
//     }
// }

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        ListNode dHead = new ListNode(0);
        ListNode curr = dHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
        } else if (list2 != null) {
            curr.next = list2;
        }

        return dHead.next;

    }
}