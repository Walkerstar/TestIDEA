package mcw.test.leetcode.bzhan;

import java.util.TreeSet;

/**
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 *
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * @author mcw 2021\4\22 0022-10:57
 */
public class leetCode363 {

    public int maxSumSubmatrix(int[][] martix,int k){
        int ans= Integer.MIN_VALUE;
        int m = martix.length;
        int n = martix[0].length;
        //枚举上边界
        for (int i = 0; i < m; i++) {
            int[] sum=new int[n];
            //枚举下边界
            for (int j = i; j < m; j++) {
                for (int c = 0; c < n; c++) {
                    //更新每列的元素和
                    sum[c]+=martix[j][c];
                }
                TreeSet<Integer> sumSet=new TreeSet<>();
                sumSet.add(0);
                int s=0;
                for (int v : sum) {
                    s+=v;
                    Integer ceil=sumSet.ceiling(s-k);
                    if (ceil!=null){
                        ans= Math.max(ans,s-ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }
}
