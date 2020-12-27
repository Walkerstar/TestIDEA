package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 请判断一个链表是否为回文链表。
 *
 * @author mcw 2020/10/23 10:36
 */
public class leetCode234 {

    /**
     * 方法一：
     * 1.找到前半部分链表的尾节点。
     * 2.反转后半部分链表。
     * 3.判断是否回文。
     * 4.恢复链表。
     * 5.返回结果。
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        //找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        //判断是否是回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    /**
     * 反转链表
     */
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    /**
     * 将链表分为两半
     */
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 方法二：
     * 1.复制链表值到数组列表中。
     * 2.使用双指针法判断是否为回文。
     */
    public boolean isPalindrome1(ListNode head) {
        List<Integer> vals=new ArrayList<>();

        //将链表的值复制到数组中
        ListNode currentNode=head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode= currentNode.next;
        }

        //使用双指针判断是否回文
        int front=0;
        int back= vals.size()-1;
        while (front<back){
            if (!vals.get(front).equals(vals.get(back))){
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}
