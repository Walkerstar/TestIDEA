package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * Partition List
 * 给定一个字符串和一个目标数，将 比目标数小的节点 移到 左边，比目标数大的节点 移到 右边，并保持原有顺序
 * given 1->4->3->2->5->2 and x=3 ; return  1->2->2->4->3->5
 * @author mcw 2020\6\30 0030-16:09
 */
public class leetCode86 {
    public static ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode left = dummy;
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = dummy;

        while (curr != null) {
            //找出 第一个大于目标节点 的前一个节点
            if (prev == left) {
                if (curr.val < x) {
                    left = left.next;
                }
                prev = curr;
                curr = curr.next;
            } else {
                //如果当前节点大于目标节点，prev 和 curr 指针直接后移
                if (curr.val >= x) {
                    prev = curr;
                    curr = curr.next;
                } else {
                    prev.next = curr.next;
                    curr.next = left.next;
                    left.next = curr;
                    left = left.next;
                    curr = prev.next;
                }
            }
        }
        return dummy.next;
    }

    public ListNode partition1(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
