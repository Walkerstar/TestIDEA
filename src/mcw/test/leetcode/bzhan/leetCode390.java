package mcw.test.leetcode.bzhan;

/**
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 *  .从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 *  .重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 *  .不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数 n ，返回 arr 最后剩下的数字。(1<= n <= 10^9)
 *
 * @author MCW 2022/1/2
 */
public class leetCode390 {

    public static int lastRemaining(int n) {
        int a1 = 1;
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) {
                //正向
                a1 = a1 + step;
            } else {
                //反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }

    //参考约瑟夫环
    public static int lastRemaining1(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining1(n / 2));
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining1(9));
    }
}
