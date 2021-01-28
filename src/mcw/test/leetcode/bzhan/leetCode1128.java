package mcw.test.leetcode.bzhan;

/**
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 *<p></p>
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d]等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 * 提示 : 1 <= dominoes.length <= 40000,  1 <= dominoes[i][j] <= 9
 * @author mcw 2021/1/26 19:40
 */
public class leetCode1128 {

    /**
     * 注意到二元对中的元素均不大于 9，因此我们可以将每一个二元对拼接成一个两位的正整数，即 (x,y)→10x+y。
     * 这样就无需使用哈希表统计元素数量，而直接使用长度为 100 的数组即可。
     * @param dominoes 数组
     * @return 等价牌的数量
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        int[] num = new int[100];
        for (int[] i : dominoes) {
            int val = i[0] < i[1] ? i[0] * 10 + i[1] : i[0] + i[1] * 10;
            ans += num[val];
            num[val]++;
        }
        return ans;
    }
}
