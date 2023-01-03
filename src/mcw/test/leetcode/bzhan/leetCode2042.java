package mcw.test.leetcode.bzhan;

/**
 * 句子是由若干 token 组成的一个列表，token 间用 单个 空格分隔，句子没有前导或尾随空格。
 * 每个 token 要么是一个由数字 0-9 组成的不含前导零的 正整数，要么是一个由小写英文字母组成的 单词 。
 * <p>
 * 示例，"a puppy has 2 eyes 4 legs" 是一个由 7 个 token 组成的句子："2" 和 "4" 是数字，其他像 "puppy" 这样的 tokens 属于单词。
 * 给你一个表示句子的字符串 s ，你需要检查 s 中的 全部 数字是否从左到右严格递增（即，除了最后一个数字，s 中的 每个 数字都严格小于它 右侧 的数字）。
 * <p>
 * 如果满足题目要求，返回 true ，否则，返回 false 。
 * <p>
 * 提示：
 * <li>3 <= s.length <= 200</li>
 * <li>s 由小写英文字母、空格和数字 0 到 9 组成（包含 0 和 9）</li>
 * <li>s 中数字 token 的数目在 2 和 100 之间（包含 2 和 100）</li>
 * <li>s 中的 token 之间由单个空格分隔</li>
 * <li>s 中至少有 两个 数字</li>
 * <li>s 中的每个数字都是一个 小于 100 的 正 数，且不含前导零</li>
 * <li>s 不含前导或尾随空格</li>
 *
 * @author mcw 2023/1/3 14:28
 */
public class leetCode2042 {


    /**
     * 方法一：直接遍历
     * 思路与算法
     * <p>
     * 题目要求检查给定的字符串 s 中 token 为数字时是否从左到右严格递增，根据题意可知相邻的 token 之间由空格分割，
     * 我们按照要求依次取出字符串中的每个 token，如果当前的 token 由数字组成，将该 token 转换为十进制数 cur，
     * 设前一个数字 token 转换后的整数 pre，检验过程如下:
     * <p>
     * 如果 cur 大于 pre，则认为当前的 token 满足递增要求，更新 pre 为 cur，并检测下一个数字 token 是否满足递增；
     * 如果 cur 小于或者等于 pre，则认为不满足递增要求，返回 false；
     * 由于题目中的每个数字 token 转换后的十进制数均为正整数且小于 100，因此我们可以初始化 pre 等于 0，
     * 我们依次检测每个为数字的 token 是否满足题目要求即可。
     */
    public static boolean areNumberAscending(String s) {
        int pre = 0, pos = 0;
        while (pos < s.length()) {
            if (Character.isDigit(s.charAt(pos))) {
                int cur = 0;
                while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
                    cur = cur * 10 + s.charAt(pos) - '0';
                    pos++;
                }
                if (cur <= pre) {
                    return false;
                }
                pre = cur;
            } else {
                pos++;
            }
        }
        return true;
    }

    public static boolean areNumberAscending2(String s) {
        int preNum = -1;
        for (String token : s.split(" ")) {
            try {
                int curNum = Integer.parseInt(token);
                if (curNum <= preNum) {
                    return false;
                }
                preNum = curNum;
            } catch (Exception e) {
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //String s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles";//true
        String s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s";//false
        System.out.println(areNumberAscending(s));
        System.out.println(areNumberAscending2(s));
    }
}
