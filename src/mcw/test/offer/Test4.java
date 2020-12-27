package mcw.test.offer;


import mcw.test.common.TreeLinkedNode;


/**
 * @author mcw 2020\1\13 0013-13:20
 * 输入二叉树的前序和中序遍历，重建该二叉树
 * 前序：2 1 7 5 4 3 6
 * 中序：7 1 5 2 4 3 6
 * 后序：7 5 1 6 3 4 2
 */
public class Test4 {

    public static TreeLinkedNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeLinkedNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    private static TreeLinkedNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;
        TreeLinkedNode root = new TreeLinkedNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, startPre + i - startIn + 1, endPre, in, i + 1, endIn);
                break;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] pre={2,1,7,5,4,3,6};
        int[] in={7,1,5,2,4,3,6};
        System.out.println(reConstructBinaryTree(pre,in));
    }
}
