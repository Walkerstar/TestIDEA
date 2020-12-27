package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * @author mcw 2020\5\3 0003-14:29
 * 合并两个排序链表
 */
public class leetCode21 {
    public static ListNode mergeList(ListNode list1,ListNode list2){
        if(list1==null) return list2;
        if(list2==null) return list1;

        if (list1.val>list2.val){
            list2.next=mergeList(list1,list2.next);
            return list2;
        }else {
            list1.next= mergeList(list1.next,list2);
            return list1;
        }
    }
}
