package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 共有 n 名小伙伴一起做游戏。小伙伴们围成一圈，按 顺时针顺序 从 1 到 n 编号。确切地说，从第 i 名小伙伴顺时针移动一位会到达第 (i+1) 名小伙伴的位置，其中 1 <= i < n ，从第 n 名小伙伴顺时针移动一位会回到第 1 名小伙伴的位置。
 * <p>
 * 游戏遵循如下规则：
 * <p>
 * 从第 1 名小伙伴所在位置 开始 。
 * 沿着顺时针方向数 k 名小伙伴，计数时需要 包含 起始时的那位小伙伴。逐个绕圈进行计数，一些小伙伴可能会被数过不止一次。
 * 你数到的最后一名小伙伴需要离开圈子，并视作输掉游戏。
 * 如果圈子中仍然有不止一名小伙伴，从刚刚输掉的小伙伴的 顺时针下一位 小伙伴 开始，回到步骤 2 继续执行。
 * 否则，圈子中最后一名小伙伴赢得游戏。
 * 给你参与游戏的小伙伴总数 n ，和一个整数 k ，返回游戏的获胜者。
 *
 * @author MCW 2022/5/4
 */
public class leetCode1823 {
    /**
     * 模拟 + 对列
     */
    public int findTheWinner(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        while (queue.size() > 1) {
            //将队首元素取出并将该元素在队尾处重新加入队列，重复该操作 k - 1 次，则在 k - 1 次操作之后，
            //队首元素即为这一轮中数到的第 k 名小伙伴的编号，将队首元素取出，即为数到的第 k 名小伙伴离开圈子
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    /**
     * 数学 + 递归
     * <p>
     * 约瑟夫环，公式: f(n,k) = ( k + f(n-1,k) - 1 ) mod n + 1
     */
    public int findTheWinner2(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (k + findTheWinner2(n - 1, k) - 1) % n + 1;
    }

    /**
     * 数学 + 迭代
     */
    public int findTheWinner3(int n, int k) {
        int winner = 1;
        for (int i = 2; i <= n; i++) {
            winner = (k + winner - 1) % i + 1;
        }
        return winner;
    }
}
