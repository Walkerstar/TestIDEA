package mcw.test.leetcode.niuke;

import mcw.test.common.TreeLinkedNode;

/**
 * @author mcw 2020\4\9 0009-16:27
 * 继续上一个题目，输入条件改为 任意二叉树
 */
public class Test35 {

    public static void connect(TreeLinkedNode root) {
        while (root != null) {
            TreeLinkedNode node = new TreeLinkedNode(0);
            TreeLinkedNode cur = node;

            while (root != null) {
                if (root.left != null) {
                    cur.next = root.left;
                    cur = cur.next;
                }
                if (root.right != null) {
                    cur.next = root.right;
                    cur = cur.next;
                }
                root = root.next;
            }
            root = cur.next;
        }
    }

    public static void main(String[] args) {
        TreeLinkedNode node = new TreeLinkedNode(1);
        TreeLinkedNode left = new TreeLinkedNode(2);
        TreeLinkedNode right = new TreeLinkedNode(3);
        left.left=new TreeLinkedNode(4);
        left.right=new TreeLinkedNode(5);
        right.left=new TreeLinkedNode(7);
        node.left=left;
        node.right=right;

        connect(node);
        System.out.println(node);
    }
}
