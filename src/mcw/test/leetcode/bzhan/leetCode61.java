package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * @author mcw 2020\6\4 0004-14:33
 * Rotate List
 * given a list ,rotate the list to the right by K places.where K is non-negative.
 * given 1->2->3->4->5->null and k=2   return 4->5->1->2->3->null.
 */
public class leetCode61 {

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        //获取链表的长度
        int len = 0;
        while (fast != null) {
            len++;
            fast = fast.next;
        }
        //快指针先走 (k % len) 个位置
        fast = head;
        for (int i = 0; i < k % len; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
}
