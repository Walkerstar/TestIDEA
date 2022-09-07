package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 * <p>
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 * <p>
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
 * <p>
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 *
 * @author mcw 2022/9/6 14:54
 */
public class leetCode828 {

    /**
     * 方法一：分别计算每个字符的贡献
     * 思路
     * 对于下标为 i 的字符 c_i ，当它在某个子字符串中仅出现一次时，它会对这个子字符串统计唯一字符时有贡献。
     * 只需对每个字符，计算有多少子字符串仅包含该字符一次即可。
     * 对于 c_i， 记同字符上一次出现的位置为 c_j，下一次出现的位置为 c_k ，那么这样的子字符串就一共有 (c_i - c_j) * (c_k - c_i) 种，
     * 即子字符串的起始位置有 c_j（不含）到 c_i（含）之间这 (c_i - c_j) 种可能，到结束位置有 (c_k - c_i) 种可能。
     * 可以预处理 s，将相同字符的下标放入数组中，方便计算。最后对所有字符进行这种计算即可。
     */
    public int uniqueLetterString(String s) {
        Map<Character, List<Integer>> index = new HashMap<>();
        //计算每个字符出现的位置
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!index.containsKey(c)) {
                index.put(c, new ArrayList<>());
                index.get(c).add(-1);
            }
            index.get(c).add(i);
        }
        int res = 0;
        for (Map.Entry<Character, List<Integer>> entry : index.entrySet()) {
            List<Integer> arr = entry.getValue();
            arr.add(s.length());
            for (int i = 1; i < arr.size() - 1; i++) {
                res += (arr.get(i) - arr.get(i - 1)) * (arr.get(i + 1) - arr.get(i));
            }
        }
        return res;
    }
}
