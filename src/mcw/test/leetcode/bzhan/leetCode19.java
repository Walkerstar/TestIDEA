package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * @author mcw 2020\5\2 0002-16:37
 * 删除倒数第 N 个节点
 */
public class leetCode19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode fast = dummy;
            ListNode slow = dummy;
        /*for (int i = 0; i < n; i++) {
            fast=fast.next;
        }
        while (fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }*/
            for (int i = 1; fast.next != null; i++) {
                if (i > n) {
                    slow = slow.next;
                }
                fast = fast.next;
            }
            assert slow.next != null;
            slow.next = slow.next.next;
            return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(4);
        ListNode d = new ListNode(5);
        node.next=a; a.next=b; b.next=c; c.next=d;
        System.out.println(removeNthFromEnd(node, 2));
    }
}
