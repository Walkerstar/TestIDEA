package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

/**
 * @author mcw 2020\1\25 0025-20:40
 *
 * 给定一个二叉树和其中一个节点，请找出中序遍历顺序的下一个节点并且返回。
 * 注意：树中的节点不仅包含左右子节点，同时包含指向父节点的指针。
 */
public class Test57 {

    public static TreeLinkedNode GetNext(TreeLinkedNode node){
        if(node==null) return null;
        if(node.right!=null){
            //如果有右子树，则找右子树的最左节点
            node=node.right;
            while (node.left!=null)
                node=node.left;
            return node;
        }

        while (node.next!=null){
            //没右子树，则找第一个当前节点是父节点左孩子的节点
            if(node.next.left==node)
                return node.next;
            node=node.next;
        }
        return null;//退到了根节点仍没找到，则返回null
    }
}
