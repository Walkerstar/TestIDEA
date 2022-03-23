package mcw.test.leetcode.bzhan;

/**
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 *
 * @author mcw 2022/3/23 18:36
 */
public class leetCode440 {

    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            int steps = getCount(cur, n);
            if (steps <= k) {
                k -= steps;
                //第 K 个数不在当前前缀下，扩大前缀
                cur++;
            } else {
                //第 K 个数在当前前缀下
                cur = cur * 10;
                k--;
            }
        }
        return cur;
    }

    /**
     * 以字典树的方式计算个数，返回在 [1,n] 内，以 cur 为前缀的数字的个数
     * @param cur 当前数字前缀 （1-9）
     * @param n 给定的最大整数
     * @return 数字个数
     */
    public int getCount(int cur, long n) {
        int count = 0;
        long first = cur;
        long last = cur;
        while (first <= n) {
            // 此处不加 1 ,会出现少算节点的情况
            // example: n=12 ,cur =1 ,则 count =4,(1,10,11,12)
            count += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new leetCode440().findKthNumber(12, 3));
    }
}
