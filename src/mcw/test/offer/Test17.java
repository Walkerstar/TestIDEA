package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

/**
 * @author mcw 2020\1\15 0015-15:06
 *
 * 输入两颗二叉树 A,B，判断B是不是A的子结构。（约定空树不是任意一个树的子结构）
 */
public class Test17 {

    public static boolean HasSubtree(TreeLinkedNode root1, TreeLinkedNode root2){
        boolean result=false;

        //当tree1和tree2都不为零的时候，才进行比较。否则直接返回false
        if(root2!=null && root1!=null){
            //如果找到了对应tree2的根节点的点
            if(root1.val==root2.val){
                //以这个根节点为起点判断是否包含tree2
                result=doesTree1haveTree2(root1,root2);
            }
            //如果找不到，那么就再去root的左儿子当起点，去判断是否包含tree2
            if(!result){
                result=HasSubtree(root1.left,root2);
            }
            //如果找不到，那么就再去root的右儿子当起点，去判断是否包含tree2
            if(!result){
                result=HasSubtree(root1.right,root2);
            }
        }
        return result;
    }

    private static boolean doesTree1haveTree2(TreeLinkedNode node1, TreeLinkedNode node2) {

        //如果Tree2已经遍历完了都能对应上，返回true
        if(node2 == null){
            return true;
        }

        //如果tree2没有遍历完成，tree1却遍历完成了，返回false
        if(node1 ==null){
            return false;
        }

        //如果其中有一个点没有对应上，返回false
        if(node1.val !=node2.val){
            return false;
        }

        //如果根节点对应的上，那么就分别去子节点里面匹配
        return doesTree1haveTree2(node1.left,node2.left) && doesTree1haveTree2(node1.right,node2.right);
    }

}
