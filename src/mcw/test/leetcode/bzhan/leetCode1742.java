package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，即 n == highLimit - lowLimit + 1）。另有无限数量的盒子，编号从 1 到 infinity 。
 * <p>
 * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
 * <p>
 * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 *  1 <= lowLimit <= highLimit <= 10^5
 * @author mcw 2022/11/23 14:40
 */
public class leetCode1742 {

    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> boxs = new HashMap<>();
        int res = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int boxNo = 0;
            int num = lowLimit;
            while (num != 0) {
                boxNo += num % 10;
                num /= 10;
            }
            boxs.put(boxNo, boxs.getOrDefault(boxNo, 0) + 1);
            res = Math.max(res, boxs.get(boxNo));
        }
        return res;
    }

    public int countBalls2(int lowLimit, int highLimit) {
        //10^5 最大值 99999, 及盒子的数量是 45
        int[] resultMap = new int[46];

        int firstIndex = 0, result = 0;
        //第一个球的盒子编号
        for (int num = lowLimit; num > 0; num = num / 10) {
            firstIndex += num % 10;
        }
        // 初始化第一个数字lowLimit所在编号盒子的小球数量
        resultMap[firstIndex] = 1;

        //只针对末尾是9的小球进行特殊定位计算，而其他小球所在的位置，只需要根据前面小球位置+1即可
        for (int i = lowLimit; i < highLimit; i++) {
            //根据前一个数的末位是否为9，来重新定位下一个数的位置
            for (int prevNum = i; prevNum % 10 == 9; prevNum /= 10) {
                // 前移9位
                firstIndex -= 9;
                resultMap[++firstIndex]++;
            }
        }
        for (int i : resultMap) {
            result = Math.max(result, i);
        }
        return result;
    }

}