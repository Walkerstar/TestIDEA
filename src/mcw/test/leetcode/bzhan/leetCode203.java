package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *
 * @author mcw 2021/6/5 10:04
 */
public class leetCode203 {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }


    public ListNode removeElements1(ListNode head, int val) {
        ListNode dommyHead = new ListNode(0);
        dommyHead.next = head;
        ListNode temp = dommyHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dommyHead.next;
    }
}
