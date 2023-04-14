package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（
 * 我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
 * <p>
 * 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时，
 * answer[i] 才为 true，否则为 false。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * 输出：[true,false,true,true,false]
 * 示例：
 * "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
 * "FootBall" 可以这样生成："F" + "oot" + "B" + "all".
 * "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
 * <p>
 * 提示：
 * <p>
 * 1 <= queries.length <= 100
 * 1 <= queries[i].length <= 100
 * 1 <= pattern.length <= 100
 * 所有字符串都仅由大写和小写英文字母组成。
 *
 * @author mcw 2023/4/14 14:25
 */
public class leetCode1023 {


    /**
     * 方法一：双指针
     * 思路与算法
     * <p>
     * 设待检验字符串是 queries[i]，如果 pattern 是 queries[i] 的子序列，并且去掉 pattern 之后 queries[i] 的剩余部分都由小写字母构成，
     * 那么 queries[i] 就与 pattern 匹配。
     * <p>
     * 具体来说，我们维护一个下标 p，用来遍历 pattern，然后遍历 queries[i] 中的每个字符 c：
     * <p>
     * 如果 p < pattern.length，并且 pattern[p] = c，那么令 p 加 1。
     * 否则，考虑 c 是否是一个大写字母。如果是，则匹配失败；如果不是，则该小写字母可以插入 pattern 来与 queries[i] 匹配，
     * 因此，我们可以继续遍历下一个字符。
     * queries[i] 遍历结束后，如果 p < pattern.length，则表示 pattern 中还有字符未被匹配，queries[i] 与 pattern 匹配失败。
     * 其余情况 pattern 匹配完毕，匹配成功。
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int n = queries.length;
        List<Boolean> res = new ArrayList<>();
        for (String query : queries) {
            boolean flag = true;
            int p = 0;
            for (int j = 0; j < query.length(); j++) {
                char c = query.charAt(j);
                if (p < pattern.length() && pattern.charAt(p) == c) {
                    p++;
                } else if (Character.isUpperCase(c)) {
                    flag = false;
                    break;
                }
            }
            // p 小于 模式串的长度意味着 当前的字符串长度比 模式串还小，即该模式串不可能由模式串添加小写字母 构成
            if (p < pattern.length()) {
                flag = false;
            }
            res.add(flag);
        }
        return res;
    }
}
