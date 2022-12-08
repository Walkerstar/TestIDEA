package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 你有一辆货运卡车，你需要用这一辆车把一些箱子从仓库运送到码头。这辆卡车每次运输有 箱子数目的限制 和 总重量的限制 。
 * <p>
 * 给你一个箱子数组 boxes 和三个整数 portsCount, maxBoxes 和 maxWeight ，其中 boxes[i] = [ports_i, weight_i] 。
 * <p>
 * ports_i 表示第 i 个箱子需要送达的码头， weights_i 是第 i 个箱子的重量。
 * portsCount 是码头的数目。
 * maxBoxes 和 maxWeight 分别是卡车每趟运输箱子数目和重量的限制。
 * 箱子需要按照 数组顺序 运输，同时每次运输需要遵循以下步骤：
 * <p>
 * 卡车从 boxes 队列中按顺序取出若干个箱子，但不能违反 maxBoxes 和 maxWeight 限制。
 * 对于在卡车上的箱子，我们需要 按顺序 处理它们，卡车会通过 一趟行程 将最前面的箱子送到目的地码头并卸货。
 * 如果卡车已经在对应的码头，那么不需要 额外行程 ，箱子也会立马被卸货。
 * 卡车上所有箱子都被卸货后，卡车需要 一趟行程 回到仓库，从箱子队列里再取出一些箱子。
 * 卡车在将所有箱子运输并卸货后，最后必须回到仓库。
 * <p>
 * 请你返回将所有箱子送到相应码头的 最少行程 次数。
 *
 * @author mcw 2022/12/5 15:19
 */
public class leetCode1687 {

    public static int boxDelivering(int[][] boxes, int portCount, int maxBoxes, int maxWeight) {
        //箱子的数量
        int n = boxes.length;

        //箱子对应的码头
        int[] p = new int[n + 1];

        //箱子的重量
        int[] w = new int[n + 1];

        //表示 p_i,⋯,p_j相邻两项不等的次数
        int[] neg = new int[n + 1];

        //箱子重量的前缀和
        long[] W = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = boxes[i - 1][0];
            w[i] = boxes[i - 1][1];
            if (i > 1) {
                //记 neg_i=neg(1,i)表示前缀和，那么 neg(i,j) = neg_j − neg_i 可以在 O(1) 的时间求出
                neg[i] = neg[i - 1] + (p[i - 1] != p[i] ? 1 : 0);
            }
            W[i] = W[i - 1] + w[i];
        }

        Deque<Integer> opt = new ArrayDeque<>();
        opt.offerLast(0);

        //记 f_i表示运送前 i 个箱子需要的最少行程次数，这里的「前 i 个箱子」指的是目的地为 p1,⋯,pi 的 i 个箱子
        int[] f = new int[n + 1];

        int[] g = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            while (i - opt.peekFirst() > maxBoxes || w[i] - w[opt.peekFirst()] > maxWeight) {
                opt.pollFirst();
            }
            // neg(i,j)+2 方便地求出一次性运送第 i 个到第 j 个箱子需要的行程次数，这里的 +2 表示来回需要的 2 次
            f[i] = g[opt.peekFirst()] + neg[i] + 2;
            if (i != n) {
                g[i] = f[i] - neg[i + 1];
                while (!opt.isEmpty() && g[i] <= g[opt.peekLast()]) {
                    opt.pollLast();
                }
                opt.offerLast(i);
            }
        }
        return f[n];
    }


    public static void main(String[] args) {
        int[][] boxes = {{1, 2},{3, 3},{3, 1},{3, 1},{2, 4}};
        int portsCount = 3;
        int maxBoxes = 3;
        int maxWeight = 6;
        System.out.println(boxDelivering(boxes, portsCount, maxBoxes, maxWeight));
    }
}
