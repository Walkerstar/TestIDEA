package mcw.test.leetcode.bzhan;

/**
 * 花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数  n。
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 *
 * @author mcw 2021/1/1 19:10
 */
public class leetCode605 {
    public static boolean canPlaceFlows(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        int i = 0;
        while (i <= flowerbed.length - 1) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (flowerbed[i] == 0 && (i <= flowerbed.length - 2 && flowerbed[i + 1] == 1)) {
                i += 3;
            } else {
                i += 2;
                if (--n == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int flag = 0;
        int[] temp = new int[flowerbed.length + 2];
        System.arraycopy(flowerbed, 0, temp, 1, flowerbed.length);
        for (int i = 0; i < temp.length - 2; i++) {
            if (temp[i] == 0 && temp[i + 1] == 0 && temp[i + 2] == 0) {
                temp[i + 1] = 1;
                flag++;
            }
        }
        return (n <= flag);
    }
}
