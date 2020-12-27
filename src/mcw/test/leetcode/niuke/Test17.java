package mcw.test.leetcode.niuke;


/**
 * @author mcw 2020\2\24 0024-19:58
 *
 * 路上有 N 个加油站，第 i 个加油站的汽油量是gas[i] ,有一辆汽车可无限装油，
 * 从加油站 i 走到下一个加油站（i+1）花费的油量是 cost[i] ,从一个加油站出发，开始时油箱无油
 *
 * 求从哪个加油站出发可以在环形路上走一圈，返回加油站的坐标，，没有答案返回 -1
 */
public class Test17 {


    /*
    从start出发，如果有量足够，可以一直向后走 end++
    油量不足时，start向后退  ，最终start==end的时候
    如果有解，一定是当前start所在的位置
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while (start > end) {
            if (sum >= 0) {
                sum += gas[end] - cost[end];
                ++end;
            } else {
                --start;
                sum += gas[start] - cost[start];
            }
        }
        return sum >= 0 ? start : -1;
    }


    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int start = 0; //能够完成一次循环的加油站坐标
        int lack = 0;
        int left = 0;  //每站剩余的油量
        for (int i = 0; i < gas.length; i++) {
            left += gas[i] - cost[i];
            if (left < 0) {
                start = i + 1;
                lack += left;
                left = 0;
            }
        }
        return left + lack >= 0 ? -1 : start - 1;
    }
}
