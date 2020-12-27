package mcw.test.leetcode.niuke;

import mcw.test.common.ListNode;

/**
 * @author mcw 2019\11\29 0029-13:59
 *
 * 使用插入排序对链表进行排序
 */
public class Test5 {
    public static ListNode InsertionSortlist(ListNode head){
        ListNode newHead=null;
        ListNode cur=null;
        ListNode next=head;
        while (next!=null){
            cur=next;
            next=next.next;
            cur.next=null;
            newHead=InsertOne(newHead,cur);
        }
        return newHead;
    }

    private static ListNode InsertOne(ListNode head, ListNode newNode) {
        ListNode newHead=new ListNode(0);
        newHead.next=head;
        ListNode preNode=newHead;
        ListNode curNode=head;
        for (;;){
            if(curNode==null||curNode.val>newNode.val){
                preNode.next=newNode;
                newNode.next=curNode;
                return newHead.next;
            }
            preNode=curNode;
            curNode=curNode.next;
        }
    }

    public static void main(String[] args) {
        ListNode a=new ListNode(8);
        ListNode b=new ListNode(4);
        ListNode c=new ListNode(6);
        ListNode d=new ListNode(1);
       ListNode e=new ListNode(5);
        a.next=b;
        b.next=c;
        c.next=d;
        d.next=e;
        System.out.println(InsertionSortlist(a));





    }
}
