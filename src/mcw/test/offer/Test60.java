package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mcw 2020\1\26 0026-15:50
 *
 * 参考Test22，从上到下，从左到右，打印二叉树节点，每一层输出一行
 */
public class Test60 {

    public static ArrayList<Integer> PrintNode(TreeLinkedNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<TreeLinkedNode> queue=new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            if(queue.peek().left!=null) queue.offer(queue.peek().left);
            if(queue.peek().right!=null) queue.offer(queue.peek().right);
            result.add(queue.peek().val);
            queue.poll();
        }
        return result;
    }

    //递归
    public static ArrayList<ArrayList<Integer>> print(TreeLinkedNode root){
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        depth(root,1,list);
        return list;
    }

    private static void depth(TreeLinkedNode root, int depth, ArrayList<ArrayList<Integer>> list) {
        if(root==null) return;
        if(depth>list.size())
            list.add(new ArrayList<>());
        list.get(depth-1).add(root.val);
        depth(root.left,depth+1,list);
        depth(root.right,depth+1,list);
    }

    //层次遍历
    public static ArrayList<ArrayList<Integer>> Print(TreeLinkedNode root){
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        if(root==null)
            return result;
        Queue<TreeLinkedNode> layer=new LinkedList<>();
        ArrayList<Integer> layerList=new ArrayList<>();
        layer.add(root);
        int start=0,end=1;
        while(!layer.isEmpty()){
            TreeLinkedNode cur=layer.remove();
            layerList.add(cur.val);
            start++;
            if(cur.left!=null){
                layer.add(cur.left);
            }
            if(cur.right!=null){
                layer.add(cur.right);
            }
            if(start==end){
                end=layer.size();
                start=0;
                result.add(layerList);
                layerList=new ArrayList<>();
            }
        }
        return result;
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
        ArrayList<ArrayList<Integer>> list = print(a);
        for (ArrayList<Integer> i : list) {
            System.out.println(i.toString());
        }

    }
}
