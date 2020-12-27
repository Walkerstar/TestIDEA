package mcw.test.leetcode.niuke;

import mcw.test.common.ListNode;

/**
 * @author mcw 2020\2\16 0016-17:17
 *
 * 将给定的单链表重新排序,要求使用原地算法 ，并且不改变节点的值
 * 给定：L0->L1->L2->L3->...L(n-1)->Ln
 * 重排：L0->Ln->L2->L(n-1)->L3...
 */
public class Test8 {
    public static void reorderList(ListNode head){
        if(head==null || head.next==null)
            return;

        //快慢指针找到中间节点
        ListNode fast=head;
        ListNode slow=head;
        while(fast.next != null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }

        //拆分链表，并反转中间节点之后的链表
        ListNode after=slow.next;
        slow.next=null;
        ListNode pre=null;
        while(after!=null){
            ListNode temp=after.next;
            after.next=pre;
            pre=after; //pre 就是原链表的末尾节点Ln
            after=temp;
        }

        //合并两个链表
        ListNode first=head;
        after=pre;
        while(first!=null && after!=null){
            ListNode ftemp=first.next;
            ListNode aftemp=after.next;
            first.next=after;
            first=ftemp;
            after.next=first;
            after=aftemp;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next=new ListNode(2);
        a.next.next=new ListNode(3);
        ListNode b = new ListNode(4);
        a.next.next.next=b;
        b.next=new ListNode(5);
        b.next.next=new ListNode(6);
        reorderList(a);
        System.out.println(a);
    }
}
