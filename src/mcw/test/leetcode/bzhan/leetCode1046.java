package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 * @author mcw 2020/12/30 19:07
 */
public class leetCode1046 {

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue=new PriorityQueue<>((a,b)->b-a);
        for (int stone : stones) {
            queue.offer(stone);
        }
        while (queue.size() > 1) {
            int a= queue.poll();
            int b= queue.poll();
            if (a>b){
                queue.offer(a-b);
            }
        }
        return queue.isEmpty()?0: queue.poll();
    }

    public int lastStone(int[] stones) {
        int index = stones.length - 1;
        for (int i = 0; i < stones.length - 1; i++) {
            Arrays.sort(stones);
            if (stones[index - 1] == 0) break;
            stones[index] -= stones[index - 1];
            stones[index - 1] = 0;
        }
        return stones[index];
    }



    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{3,7,8}));
    }
}
