package mcw.test.leetcode.niuke;


import mcw.test.common.TreeLinkedNode;

/**
 * @author mcw 2020\3\25 0025-12:10
 * 
 * 给定一个二叉树 ，填充所有节点的 next 指针，指向它右兄弟节点。 如果没有右兄弟节点，则将 next 指针设置为 null
 *   初始时，所有 next 指针都为 null
 */
public class Test34 {

    public static void connect(TreeLinkedNode root){
        TreeLinkedNode begin = root;
        while (root != null && root.left != null) {
            if (root.next != null) {
                root.left.next = root.right;
                root.right.next = root.next.left;
                root = root.next;
            } else {
                root.left.next = root.right;
                root = begin;
                begin = root.left;
            }
        }
    }

    public static void main(String[] args) {
        TreeLinkedNode node = new TreeLinkedNode(1);
        TreeLinkedNode left = new TreeLinkedNode(2);
        TreeLinkedNode right = new TreeLinkedNode(3);
        left.left=new TreeLinkedNode(4);
        left.right=new TreeLinkedNode(5);
        right.left=new TreeLinkedNode(6);
        right.right=new TreeLinkedNode(7);
        node.left=left;
        node.right=right;

        connect(node);
        //Test35.connect(node);
        System.out.println(node);
    }
}