package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

/**
 * @author mcw 2020\5\3 0003-14:29
 * 合并两个排序链表
 */
public class leetCode21 {
    public static ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val > list2.val) {
            list2.next = mergeList(list1, list2.next);
            return list2;
        } else {
            list1.next = mergeList(list1.next, list2);
            return list1;
        }
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
