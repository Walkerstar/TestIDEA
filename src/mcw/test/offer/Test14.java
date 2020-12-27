package mcw.test.offer;

import mcw.test.common.ListNode;

/**
 * @author mcw
 * @date 2019\11\26 0026-21:29
 * <p>
 * 输入一个链表，输出该链表中倒数第 K 个节点
 */
public class Test14 {
    public static ListNode FindKthToTail(ListNode head, int k) {
        ListNode p, q;
        p = q = head;
        int i = 0;
        for (; p != null; i++) {
            if (i >= k){
                q = q.next;
            }
            p = p.next;
        }
        return i < k ? null : q;
    }

    public static void main(String[] args) {
        ListNode a=new ListNode(1);
        ListNode b=new ListNode(2);
        ListNode c=new ListNode(3);
        ListNode d=new ListNode(4);
        ListNode e=new ListNode(5);
        a.next=b;
        b.next=c;
        c.next=d;
        d.next=e;


        ListNode node = FindKthToTail(a, 5);
        System.out.println(node.val);

    }
}
