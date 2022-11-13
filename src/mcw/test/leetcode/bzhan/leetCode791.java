package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定两个字符串 order 和 s 。order 的所有单词都是 唯一 的，并且以前按照一些自定义的顺序排序。
 * <p>
 * 对 s 的字符进行置换，使其与排序的 order 相匹配。更具体地说，如果在 order 中的字符 x 出现字符 y 之前，那么在排列后的字符串中， x 也应该出现在 y 之前。
 * <p>
 * 返回 满足这个性质的 s 的任意排列 。
 *
 * @author MCW 2022/11/13
 */
public class leetCode791 {

    /**
     * 方法一：自定义排序
     * 最简单的方法是直接对字符串 s 进行排序。
     * <p>
     * 我们首先遍历给定的字符串 order，将第一个出现的字符的权值赋值为 1，第二个出现的字符的权值赋值为 2，以此类推。
     * 在遍历完成之后，所有未出现字符的权值默认赋值为 0。
     * <p>
     * 随后我们根据权值表，对字符串 s 进行排序，即可得到一种满足要求的排列方案。
     */
    public String customSortString(String order, String s) {
        int[] val = new int[26];
        for (int i = 0; i < order.length(); i++) {
            val[order.charAt(i)] = i + 1;
        }
        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        Arrays.sort(arr, Comparator.comparingInt(c0 -> val[c0 - 'a']));
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            ans.append(arr[i]);
        }
        return ans.toString();
    }

    /**
     * 方法二：计数排序
     * 思路与算法
     * <p>
     * 由于字符集的大小为 26，我们也可以考虑使用计数排序代替普通的排序方法。
     * <p>
     * 我们首先遍历字符串 s，使用数组或哈希表统计每个字符出现的次数。
     * 随后遍历字符串 order 中的每个字符 c，如果其在 s 中出现了 k 次，就在答案的末尾添加 k 个 c，并将数组或哈希表中对应的次数置为 0。
     * 最后我们遍历一次哈希表，对于所有次数 k 非 0 的键值对 (c, k)，在答案的末尾添加 k 个 c 即可。
     */
    public String customSortString2(String order, String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            ++freq[ch - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            while (freq[ch - 'a'] > 0) {
                ans.append(ch);
                freq[ch - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                ans.append((char) (i + 'a'));
                freq[i]--;
            }
        }
        return ans.toString();
    }
}