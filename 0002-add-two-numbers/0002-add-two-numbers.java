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
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryValue = 0;
        ListNode newHead = new ListNode(0);
        ListNode current = newHead;
        while (l1 != null || l2 != null || carryValue != 0) {
            int value1 = (l1 != null) ? l1.val : 0;
            int value2 = (l2 != null) ? l2.val : 0;
            int nodeSum = value1 + value2 + carryValue;
            carryValue = nodeSum / 10;
            int digit = nodeSum % 10;
            current.next = new ListNode(digit);
            current = current.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return newHead.next;
    }
}