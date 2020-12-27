package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * @author mcw 2020\5\3 0003-15:50
 * swap node in pairs(成对交换)
 * 例：1->2->3->4  变换成 2->1->4->3
 */
public class leetCode24 {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            swap2(current);
            current = current.next.next;
        }
        return dummy.next;
    }

    private static void swap2(ListNode pre) {
        //if(pre.next==null || pre.next.next==null) return;
        ListNode dummy = pre.next;
        pre.next = dummy.next;//0->2
        dummy.next = dummy.next.next;//1->3
        pre.next.next = dummy;//2->1
    }

    public static void main(String[] args) {
        ListNode list1=new ListNode(1);
        ListNode list2=new ListNode(2);
        ListNode list3=new ListNode(3);
        ListNode list4=new ListNode(4);
        ListNode list5=new ListNode(5);
        list1.next=list2; list2.next=list3;
        list3.next=list4; list4.next=list5;

        System.out.println(swapPairs(list1));
    }

}
