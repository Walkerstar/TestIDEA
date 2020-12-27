package mcw.test.offer;

/**
 * @author mcw 2020\1\17 0017-15:30
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出 Yes,否则输出 No。
 * 假设输入的数组的任意两个数字都互不相同
 * 1 3 2 6 8 9 7 5 4
 */
public class Test23 {

    public static boolean verifySequenceOfBST(int []sequence){
        if(sequence.length==0)
            return false;
        return isBST(sequence,0,sequence.length-1);
    }

    private static boolean isBST(int[] arr, int left, int right) {
        if(left>=right) //left>right 对应空树   left==right 对应叶子结点
            return true;
        int i=left;
        for(;i<right;i++){
            if(arr[i]>arr[right])
                break;
        }
        for(int j=i;j<right;j++){
            if(arr[j]<arr[right])
                return false;
        }
        return isBST(arr,left,i-1) && isBST(arr,i,right-1);
    }

    public static void main(String[] args) {
//        TreeNode root=new TreeNode(4);
//        TreeNode left=new TreeNode(2);
//        TreeNode right=new TreeNode(5);
//        TreeNode a=new TreeNode(1);
//        TreeNode b=new TreeNode(3);
//        TreeNode c=new TreeNode(6);
//        TreeNode d=new TreeNode(7);
//        TreeNode e=new TreeNode(8);
//        TreeNode f=new TreeNode(9);
//
//        root.left=left;
//        root.right=right;
//        left.right=b;
//        left.left=a;
//        right.right=d;
//        right.left=c;
//        d.right=f;
//        d.left=e;
        boolean result = verifySequenceOfBST(new int[]{1, 3, 2, 6, 8, 9, 7, 4, 5});
        System.out.println(result);
        }
}
