package mcw.test.offer;

import mcw.test.common.ListNode;



/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口节点，否则输出null
 * @author mcw 2020\1\25 0025-17:06
 */
public class Test55 {

    //找到一快一慢指针相遇处的节点，相遇到的节点一定是在环中
    public static ListNode meetingNode(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head.next;
        if (slow == null)
            return null;
        ListNode fast = slow.next;
        while (slow != null && fast != null) {
            if (slow == fast) return fast;
            slow = slow.next;
            fast = fast.next;
            if (fast != slow) {
                fast = fast.next;
            }
        }
        return null;
    }

    public static ListNode EntryNodeOfLoop1(ListNode phead) {
        ListNode meetingNode = meetingNode(phead);
        if (meetingNode == null) return null;
        //得到环中节点的个数
        int nodesInLoop = 1;
        ListNode p1 = meetingNode;
        while (p1.next != meetingNode) {
            p1 = p1.next;
            ++nodesInLoop;
        }
        //移动p1
        p1 = phead;
        for (int i = 0; i < nodesInLoop; i++) {
            p1 = p1.next;
        }

        //移动p1,p2
        ListNode p2 = phead;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }


    public static ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null)
            return null;
        ListNode p1 = pHead;
        ListNode p2 = pHead;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                p2 = pHead;
                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        ListNode h1=new ListNode(2);
        ListNode h2=new ListNode(3);
        ListNode h3=new ListNode(4);
        ListNode h4=new ListNode(5);
        ListNode h5=new ListNode(6);
        head.next=h1;
        h1.next=h2;
        h2.next=h3;
        h3.next=h4;
        h4.next=h5;
        h5.next=head;

        //System.out.println(head);
        System.out.println(EntryNodeOfLoop(head).val);
    }

}
