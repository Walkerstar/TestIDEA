package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * @author mcw 2020\6\30 0030-15:11
 * Remove Duplicates form Sorted List I / II
 */
public class leetCode83 {
    /**
     * 不删除重复的节点
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode iter = head;
        while (iter != null && iter.next != null) {
            if (iter.val == iter.next.val) {
                iter.next = iter.next.next;
            } else {
                iter = iter.next;
            }
        }
        return head;
    }

    /**
     * 删除重复的节点
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.val == head.next.val) {
            ListNode p = head.next;
            while (p != null && p.val == head.val) {
                p = p.next;
            }
            //head.next=deleteDuplicates1(p);
            //return head;
            return deleteDuplicates1(p);
        } else {
            head.next = deleteDuplicates1(head.next);
            return head;
        }
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        //dummy.next=head;
        ListNode preNode = dummy;
        ListNode curNode = head;
        ListNode realNode = dummy;

        while (curNode != null) {
            if ((preNode == dummy || preNode.val != curNode.val) &&
                    (curNode.next == null || curNode.val != curNode.next.val)) {
                realNode.next = curNode;
                realNode = curNode;
            }
            preNode = curNode;
            curNode = curNode.next;
            preNode.next = null;
        }
        return dummy.next;
    }
    
}
