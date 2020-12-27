package mcw.test.offer;



import mcw.test.common.TreeLinkedNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mcw 2020\1\26 0026-17:38
 *
 * 实现两个函数，分别用来保存序列化和反序列化二叉树
 *
 * #:空节点 !:表示一个节点值的结束
 */
public class Test61 {

    public static String Serialize(TreeLinkedNode root){
        //先序序列
        if(root==null)
            return "#!";
        String res=root.val+"!";
        res+=Serialize(root.left);
        res+=Serialize(root.right);
        return res;
    }

    public static TreeLinkedNode Deserialize(String str){
        String[] values=str.split("!");
        Queue<String> queue=new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    private static TreeLinkedNode reconPreOrder(Queue<String> queue) {

        String value=queue.poll();
        if(value.equals("#")){
            return null;
        }
        TreeLinkedNode head=new TreeLinkedNode(Integer.valueOf(value));
        head.left=reconPreOrder(queue);
        head.right=reconPreOrder(queue);
        return head;
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

        String serialize = Serialize(a);
        System.out.println(serialize);

        System.out.println(Deserialize(serialize));
    }
}
