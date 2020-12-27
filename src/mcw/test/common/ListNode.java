package mcw.test.common;

/**
 * @author mcw
 * @date 2019\11\25 0025-19:49
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val){
        this.val=val;
    }

    public static ListNode generate(int[] arr){
        ListNode node = new ListNode(0);
        ListNode pre=node;
        for (int value : arr) {
            node.next = new ListNode(value);
            node = node.next;
        }
        return pre.next;
    }

    @Override
    public String toString() {
        /*return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';*/
        return val+"-->"+next+"  ";
    }
}
