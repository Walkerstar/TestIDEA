package mcw.test.leetcode.niuke;


import mcw.test.common.ListNode;

/**
 * @author mcw 2019\11\28 0028-18:53
 * 在O(nlogn) 的时间内使用常数级空间复杂度对链表进行排序
 */
public class Test4 {

    public static ListNode sortList(ListNode head){
        if(head==null||head.next==null){
            return head;
        }

        ListNode mid=getMid(head);
        ListNode midNext = mid.next;
        mid.next=null;
        return mergeSort(sortList(head),sortList(midNext));
    }

    private static ListNode mergeSort(ListNode n1,ListNode n2) {
        ListNode preHead=new ListNode(0);
        ListNode cur1=n1,cur2=n2;
        ListNode cur=preHead;
        while (cur1!=null&&cur2!=null){
            if(cur1.val<cur2.val){
                cur.next=cur1;
                cur1=cur1.next;
            }else{
                cur.next=cur2;
                cur2=cur2.next;
            }
            cur=cur.next;
        }
        cur.next=cur1==null?cur2:cur1;
        return preHead.next;
    }

    public static ListNode getMid(ListNode head){
        if(head==null||head.next==null){
            return head;
        }
        ListNode slow=head;
        ListNode quick=head;
        while(quick.next!=null && quick.next.next!=null){
            slow=slow.next;
            quick=quick.next.next;
        }
        return  slow;
    }
}
