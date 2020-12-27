package mcw.test.common;

/**
 * @author mcw 2020\1\13 0013-13:21
 */
public class TreeLinkedNode {
    public int val;
    public TreeLinkedNode left;
    public TreeLinkedNode right;
    public TreeLinkedNode next;

    public TreeLinkedNode(int x) {
        val = x;
    }

    public TreeLinkedNode(int val, TreeLinkedNode left, TreeLinkedNode right, TreeLinkedNode next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }

    @Override
    public String toString() {
        return "TreeLinkedNode{" +
                "val=" + val +
                ", left-->" + left +
                ", right--->" + right +
                ", next->" + next +
                '}';
    }
}
