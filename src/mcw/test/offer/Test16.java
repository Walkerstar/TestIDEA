package mcw.test.offer;

import mcw.test.common.ListNode;

/**
 * @author mcw
 * @date 2019\11\25 0025-19:53
 *
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不递减的规则
 */
public class Test16 {

    //递归版本
    public static ListNode Merge01(ListNode list1, ListNode list2){
        //两个链表为空
        if(list1==null && list2==null){
            return null;
        }
        //有一个链表为空的情况
        if(list1==null){
            return list2;
        }
        if (list2==null){
            return list1;
        }

        //递归合并
        if(list1.val<=list2.val){
            list1.next=Merge01(list1.next,list2);
//            System.out.println(list1);
            return list1;
        }else{
            list2.next=Merge01(list1,list2.next);
//            System.out.println(list2);
            return list2;
        }
    }

    //非递归
    public static ListNode Merge02(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(5277);
        ListNode temp = head;

        //两个链表为空的情况
        if (list1 == null && list2 == null) {
            return null;
        }
        //有一个链表为空的情况
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        temp.next = (list1 == null) ? list2 : list1;
        return head.next;
    }

    public static void main(String[] args) {
        ListNode list1=new ListNode(1);
        ListNode list2=new ListNode(3);
        ListNode list3=new ListNode(5);
        list1.next=list2;
        list2.next=list3;

        ListNode list4=new ListNode(2);
        ListNode list5=new ListNode(4);
        ListNode list6=new ListNode(6);
        list4.next=list5;
        list5.next=list6;
//        ListNode merge = Merge01(list1, list4);
        ListNode merge = Merge02(list1, list4);
        System.out.println(merge);
    }
}
