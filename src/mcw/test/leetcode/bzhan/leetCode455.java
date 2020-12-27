package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
 * 都有一个尺寸 s[j]。如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * @author mcw 2020/12/25 20:46
 */
public class leetCode455 {
    public int findContentChildren(int[] g,int[] s){
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0;
        int j=0;
        while (i<g.length && j<s.length){
            if (g[i]<=s[j]){
                i++;
            }
            j++;
        }
        return i;
    }
}
