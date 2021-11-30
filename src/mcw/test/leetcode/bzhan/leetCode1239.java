package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * <p>
 * 请返回所有可行解 s 中最长长度。
 *
 * @author mcw 2021/6/19 10:51
 */
public class leetCode1239 {

    int ans = 0;

    /**
     * 回溯 + 位运算
     */
    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i) - 'a';
                // 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                if (((mask >> ch) & 1) != 0) {
                    mask = 0;
                    break;
                }
                // 将 ch 加入 mask 中
                mask |= 1 << ch;
            }
            if (mask > 0) {
                masks.add(mask);
            }
        }
        backtrack(masks, 0, 0);
        return ans;
    }

    private void backtrack(List<Integer> masks, int pos, int mask) {
        if (pos == masks.size()) {
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }
        // mask 和 masks[pos] 无公共元素
        if ((mask & masks.get(pos)) == 0) {
            backtrack(masks, pos + 1, mask | masks.get(pos));
        }
        backtrack(masks, pos + 1, mask);
    }


    /**
     * 迭代+位运算
     */
    public int maxLength1(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        masks.add(0);
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i) - 'a';
                // 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                if (((mask >> ch) & 1) != 0) {
                    mask = 0;
                    break;
                }
                // 将 ch 加入 mask 中
                mask |= 1 << ch;
            }
            if (mask == 0) {
                continue;
            }
            int n = masks.size();
            for (int i = 0; i < n; i++) {
                int m = masks.get(i);
                // m 和 mask 无公共元素
                if ((m & mask) == 0) {
                    masks.add(m | mask);
                    ans = Math.max(ans, Integer.bitCount(m | mask));
                }
            }
        }
        return ans;
    }
}
