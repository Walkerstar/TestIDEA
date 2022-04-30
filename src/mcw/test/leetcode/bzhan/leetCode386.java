package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 *
 * @author MCW 2022/4/18
 */
public class leetCode386 {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ret.add(number);

            //如果 number*10 ≤ n，那么说明 number * 10 是下一个字典序整数；
            if (number * 10 <= n) {
                number *= 10;
            } else {
                //如果 number mod 10 = 9 或 number + 1 > n，
                // 那么说明末尾的数位已经搜索完成，退回上一位，即 number=⌊ number /10 ⌋，
                // 然后继续判断直到 number mod 10 =9 且 number+1 ≤ n 为止，那么 number + 1 是下一个字典序整数。
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return ret;
    }
}
