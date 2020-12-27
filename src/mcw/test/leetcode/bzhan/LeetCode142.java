package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

import java.util.HashSet;

/**
 * 环形链表II (同 offer中 Test55)
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * @author mcw 2020/10/10 21:43
 */
public class LeetCode142 {
    /**
     * 方法一：哈希表
     * 时间复杂度：O(N) 空间复杂度：O(N)
     * 我们遍历链表中的每个节点，并将它记录下来；一旦遇到了此前遍历过的节点，就可以判定链表中存在环。借助哈希表可以很方便地实现。
     */
    public ListNode detectCycle1(ListNode head){
        ListNode pos=head;
        HashSet<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            }else {
                visited.add(pos);
            }
            pos= pos.next;
        }
        return null;
    }

    /**
     * 方法二：快慢指针
     * 我们使用两个指针，fast 与 slow。它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，而 fast 指针向后移动两个位置。
     * 如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。当发现 slow 与 fast 相遇时，我们再额外使用一个指针 ptr。起始，
     * 它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。
     */
    public ListNode detectCycle(ListNode head){
        if (head==null) {
            return null;
        }
        ListNode slow=head,fast=head;
        while (fast != null) {
            slow= slow.next;
            if (fast.next!=null) {
                fast= fast.next.next;
            }else {
                return null;
            }
            if (fast==slow) {
                ListNode ptr=head;
                while (ptr != slow) {
                    ptr= ptr.next;
                    slow= slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
