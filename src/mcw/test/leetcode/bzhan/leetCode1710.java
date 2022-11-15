package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
 *
 * numberOfBoxesi 是类型 i 的箱子的数量。
 * numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
 * 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
 *
 * 返回卡车可以装载 单元 的 最大 总数。
 * @author mcw 2022/11/15 16:47
 */
public class leetCode1710 {

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        int ans = 0;
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        for (int i = 0; i < boxTypes.length && truckSize >= 0; i++) {
            System.out.println(Arrays.toString(boxTypes[i]));

            if (boxTypes[i][0] < truckSize) {
                ans += boxTypes[i][0] * boxTypes[i][1];
            } else {
                ans += truckSize * boxTypes[i][1];
                break;
            }
            truckSize -= boxTypes[i][0];

        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] b={{1,3},{2,2},{3,1}};
        System.out.println(maximumUnits(b, 4));
    }
}
