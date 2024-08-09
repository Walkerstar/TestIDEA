package mcw.test.leetcode.bzhan;

/**
 * 2129. 将标题首字母大写
 * <p>
 * 给你一个字符串 title ，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。请你按以下规则将每个单词的首字母 大写 ：
 * <p>
 * 如果单词的长度为 1 或者 2 ，所有字母变成小写。
 * 否则，将单词首字母大写，剩余字母变成小写。
 * 请你返回 大写后 的 title 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：title = "capiTalIze tHe titLe"
 * 输出："Capitalize The Title"
 * 解释：
 * 由于所有单词的长度都至少为 3 ，将每个单词首字母大写，剩余字母变为小写。
 * <p>
 * 示例 2：
 * <p>
 * 输入：title = "First leTTeR of EACH Word"
 * 输出："First Letter of Each Word"
 * 解释：
 * 单词 "of" 长度为 2 ，所以它保持完全小写。
 * 其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
 * <p>
 * 示例 3：
 * <p>
 * 输入：title = "i lOve leetcode"
 * 输出："i Love Leetcode"
 * 解释：
 * 单词 "i" 长度为 1 ，所以它保留小写。
 * 其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= title.length <= 100
 * title 由单个空格隔开的单词组成，且不含有任何前导或后缀空格。
 * 每个单词由大写和小写英文字母组成，且都是 非空 的。
 *
 * @author MCW 2024/3/11
 */
public class leetCode2129 {

    /**
     * 方法一：按要求遍历
     * 思路与算法
     * <p>
     * 我们顺序遍历 title 字符串，对于其中每个以空格为分界的单词，我们首先找出它的起始与末尾下标，判断它的长度以进行相应操作：
     * <p>
     * 如果长度小于等于 2，则我们将该单词全部转化为小写；
     * <p>
     * 如果长度大于 2，则我们将该单词首字母转化为大写，其余字母转化为小写。
     * <p>
     * 最终，我们将转化后的字符串返回作为答案。
     * <p>
     * 另外，对于 Python 等无法直接对字符串特定字符进行修改的语言，我们可以先将字符串分割为单词，并用数组按顺序储存这些单词。
     * 随后，我们逐单词进行上述操作生成新的单词并替换。最后，我们将替换后的单词数组拼接为空格连接的字符串并返回作为答案。
     */
    public String capitalizeTitle(String title) {
        StringBuilder sb = new StringBuilder(title);

        int n = title.length();
        int l = 0, r = 0; // 单词左右边界 （左闭右开）
        while (r < n) {
            while (r < n && sb.charAt(r) != ' ') {
                ++r;
            }
            // 对于每个单词按要求处理
            if (r - l > 2) {
                sb.setCharAt(l, Character.toUpperCase(sb.charAt(l)));
                ++l;
            }
            while (l < r) {
                sb.setCharAt(l, Character.toLowerCase(sb.charAt(l)));
                ++l;
            }
            l = r + 1;
            ++r;
        }
        return sb.toString();
    }
}
