package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

import java.util.*;

/**
 * @author mcw 2020\1\17 0017-14:41
 * <p>
 * 从上往下打印二叉树的每个节点，同层节点从左至右打印
 */
public class Test22 {

    public static ArrayList<Integer> printTree(TreeLinkedNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeLinkedNode> queue = new LinkedList<>();
        if (root == null)
            return list;
        queue.offer(root);
        while (queue.size() != 0) {
            if (queue.peek().left != null) {
                queue.offer(queue.peek().left);
            }
            if (queue.peek().right != null) {
                queue.offer(queue.peek().right);
            }
            list.add(queue.peek().val);
            queue.poll();
        }
        return list;
    }

    public static void main(String[] args) {
        TreeLinkedNode a=new TreeLinkedNode(10);
        TreeLinkedNode b=new TreeLinkedNode(5);
        TreeLinkedNode c=new TreeLinkedNode(9);
        TreeLinkedNode d=new TreeLinkedNode(7);
        TreeLinkedNode e=new TreeLinkedNode(100);
        TreeLinkedNode f=new TreeLinkedNode(15);
        a.left=b; a.right=c;
        b.left=d;
        c.right=e; c.left=f;
        d.left=new TreeLinkedNode(20);
        ArrayList<Integer> nodes = printTree(a);
        for (Integer node : nodes) {
            System.out.print(node+"\t");
        }
    }
}
