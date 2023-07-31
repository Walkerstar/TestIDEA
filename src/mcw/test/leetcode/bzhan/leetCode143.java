package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 * <p>
 * 提示：
 * <p>
 * 链表的长度范围为 [1, 5 * 10^4]
 * 1 <= node.val <= 1000
 *
 * @author MCW 2023/7/31
 */
public class leetCode143 {

    /**
     * 方法一：线性表
     * 因为链表不支持下标访问，所以我们无法随机访问链表中任意位置的元素。
     * <p>
     * 因此比较容易想到的一个方法是，我们利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可。
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    /**
     * 方法二：寻找链表中点 + 链表逆序 + 合并链表
     * <p>
     * 注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。
     * <p>
     * 这样我们的任务即可划分为三步：
     * <p>
     * 1. 找到原链表的中点（参考「876. 链表的中间结点」）。
     * 我们可以使用快慢指针来 O(N) 地找到链表的中间节点。
     * <p>
     * 2. 将原链表的右半端反转（参考「206. 反转链表」）。
     * 我们可以使用迭代法实现链表的反转。
     * <p>
     * 3. 将原链表的两端合并。
     * 因为两链表长度相差不超过 1，因此直接合并即可。
     */
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_temp;
        ListNode l2_temp;
        while (l1 != null && l2 != null) {
            l1_temp = l1.next;
            l2_temp = l2.next;

            l1.next = l2;
            l1 = l1_temp;

            l2.next = l1;
            l2 = l2_temp;

        }
    }

}
