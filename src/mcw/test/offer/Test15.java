package mcw.test.offer;

import mcw.test.common.ListNode;

/**
 * @author mcw
 * @date 2019\11\25 0025-21:04
 *
 * 输入一个链表，反转链表后，输出新链表的表头
 */
public class Test15 {

    //非递归
    public static ListNode ReverseList01(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode node=head.next;
        head.next=null;
        while (node!=null){
            ListNode cur=node.next;
            node.next=head;
            head=node;
            node=cur;
        }
        return head;
    }

    //递归
    public static ListNode ReverseList02(ListNode head){
        if(head==null||head.next==null){
            return head;
        }

        ListNode preNode=ReverseList02(head.next);
        head.next.next=head;
        head.next=null;
        return preNode;

    }

    public static void main(String[] args) {
//        ListNode list1=new ListNode(1);
//        ListNode list2=new ListNode(3);
//        ListNode list3=new ListNode(5);
//        list1.next=list2;
//        list2.next=list3;

//        ListNode node = ReverseList01(list1);
//        System.out.println(node);

        ListNode list4=new ListNode(2);
        ListNode list5=new ListNode(4);
        ListNode list6=new ListNode(6);
        list4.next=list5;
        list5.next=list6;
        ListNode node1 = ReverseList02(list4);
        System.out.println(node1);


    }
}
