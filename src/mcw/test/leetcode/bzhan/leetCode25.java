package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

import java.util.Stack;

/**
 * @author mcw 2020\5\4 0004-14:25
 *
 * Reverse Nodes in K-Group
 * given list: 1->2->3->4->5
 * for k=2; return : 2->1->4->3->5
 * for k=3; return : 3->2->1->4->5
 */
public class leetCode25 {

    /**
     * 不使用额外空间
     */
    public static ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev != null) {
            prev = reverse(prev, k);
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode prev, int k) {
        ListNode last = prev;
        for (int i = 0; i < k + 1; i++) {
            last = last.next;
            if (i != k && last == null) return null;
        }
        ListNode tail = prev.next;
        ListNode curr = prev.next.next;
        while (curr != last) {
            ListNode next = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            tail.next = next;
            curr = next;
        }
        return tail;
    }

    /**
     * 使用了额外空间
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        ListNode next = dummy.next;
        while (next != null) {
            for (int i = 0; i < k && next != null; i++) {
                stack.push(next);
                next = next.next;
            }
            if (stack.size() != k) return dummy.next;
            while (stack.size() != 0) {
                current.next = stack.pop();
                current = current.next;
            }
            current.next = next;
        }
        return dummy.next;
    }
}
