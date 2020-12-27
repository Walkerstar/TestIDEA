package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author mcw 2020\5\3 0003-15:08
 * 合并 K 个链表
 */
public class leetCode23 {

    public static ListNode mergeLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        if (lists == null || lists.length == 0) {
            return dummy.next;
        }
        ListNode current = dummy;

        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }

        while (queue.size() != 0) {
            ListNode node = queue.poll();
            current.next = node;
            current = current.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode list1=new ListNode(1);
        /*ListNode list2=new ListNode(3);
        ListNode list3=new ListNode(5);
        list1.next=list2;
        list2.next=list3;*/

        ListNode list4=new ListNode(2);
        /*ListNode list5=new ListNode(4);
        ListNode list6=new ListNode(6);
        list4.next=list5;
        list5.next=list6;*/

        ListNode list7=new ListNode(2);
        /*ListNode list8=new ListNode(3);
        ListNode list9=new ListNode(7);
        list7.next=list8;
        list8.next=list9;*/

        ListNode[] listNodes = new ListNode[3];
        listNodes[0]=list1;
        listNodes[1]=list4;
        listNodes[2]=list7;

        System.out.println(mergeLists(listNodes));
    }
}
