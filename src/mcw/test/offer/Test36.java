package mcw.test.offer;

import mcw.test.common.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mcw 2020\1\20 0020-20:42
 *
 * 输入两个链表，找出他们的第一个公共节点
 */
public class Test36 {

    public static ListNode FindFirstCommonNode(ListNode head1,ListNode head2){
        ListNode p1=head1;
        ListNode p2=head2;
        Map<ListNode,Integer> map=new HashMap<>();

        while(p1!=null){
            map.put(p1,p1.val);
            p1= p1.next;
        }

        while(p2!=null){
            if(map.containsValue(p2.val))
                return p2;
            p2=p2.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head1=new ListNode(1);
        head1.next=new ListNode(2);
        head1.next.next=new ListNode(3);
        System.out.println(head1);

        ListNode head2=new ListNode(3);
        head2.next=new ListNode(4);
        System.out.println(head2);

        ListNode node = FindFirstCommonNode(head1, head2);
        System.out.println(node);

    }
}
