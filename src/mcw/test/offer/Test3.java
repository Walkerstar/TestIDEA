package mcw.test.offer;

import mcw.test.common.ListNode;

import java.util.ArrayList;

/**
 * @author mcw 2020\1\12 0012-14:27
 * 输入一个链表，按链表从尾到头的顺序返回一个arrayList。
 */
public class Test3 {

    static ArrayList<Integer> arr = new ArrayList<>();

    public static ArrayList<Integer> TailToHead(ListNode listNode) {

        if(listNode!=null){
            TailToHead(listNode.next);
            arr.add(listNode.val);
        }
        return arr;
    }


    public static void main(String[] args) {
        ListNode list4=new ListNode(2);
        ListNode list5=new ListNode(4);
        ListNode list6=new ListNode(6);
        list4.next=list5;
        list5.next=list6;

        ArrayList<Integer> integers = TailToHead(list4);
        System.out.println(integers);
    }
}
