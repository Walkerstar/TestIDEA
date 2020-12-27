package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * Add Two Sum
 * input: {2->4->3} + {5->6->4} output: {7->0->8}
 * 思路：
 * 遍历两个链表，将当前的两个节点值和初始进位相加，对 10 取余 并保存在新的节点中，
 * 如果和大于10，计算出新的进位， 所有指针后移
 * @author mcw 2020\5\16 0016-12:05
 */
public class leetCode02 {

    public static ListNode addTwoSum(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;

            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
        }
        return head.next;
    }
}
