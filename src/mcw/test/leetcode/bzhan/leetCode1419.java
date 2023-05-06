package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，
 * 所以 croakOfFrogs 中会混合多个 “croak” 。
 * <p>
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 * <p>
 * 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。
 * 如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：croakOfFrogs = "croakcroak"
 * 输出：1
 * 解释：一只青蛙 “呱呱” 两次
 * 示例 2：
 * <p>
 * 输入：croakOfFrogs = "crcoakroak"
 * 输出：2
 * 解释：最少需要两只青蛙，“呱呱” 声用黑体标注
 * 第一只青蛙 "crcoakroak"
 * 第二只青蛙 "crcoakroak"
 * 示例 3：
 * <p>
 * 输入：croakOfFrogs = "croakcrook"
 * 输出：-1
 * 解释：给出的字符串不是 "croak" 的有效组合。
 * <p>
 * 提示：
 * <p>
 * 1 <= croakOfFrogs.length <= 105
 * 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
 *
 * @author MCW 2023/5/6
 */
public class leetCode1419 {

    /**
     * 方法一：计数
     * 思路与算法
     * <p>
     * 题目给出一个字符串  croakOfFrogs，它表示不同青蛙发出的蛙鸣声的组合，且字符串中只包含 ‘c’，‘r’，‘o’， ‘a’ 和  ‘k’ 五种字符。
     * 若一只青蛙想要发出蛙鸣，则该青蛙需要依序输出  ‘c’， ‘r’， ‘o’， ‘a’ 和  ‘k’ 这 5 个字符。
     * 如果没有输出全部五个字符，那么它就不会发出声音。
     * 现在我们需要求得能模拟出字符串  croakOfFrogs 中所有蛙鸣所需青蛙的最少数目，其中同一时间可以有多只青蛙发出声音。
     * 若字符串 croakOfFrogs 不能被若干有效的蛙鸣混合而成，则返回  −1。
     * <p>
     * 现在我们用  frog_num 来表示现在正在发出蛙鸣声的青蛙数目，用  cnt[c] 表示已经发出一次有效蛙鸣中的字符  c 的青蛙个数，
     * 比如当 cnt[‘c’]=2 时表示当前有  2 只青蛙已经发出了有效蛙鸣中的字符 ‘c’，下一个需要发出字符  ‘r’。
     * 那么我们遍历字符串  croakOfFrogs 来模拟青蛙蛙鸣，现在记遍历到的字符为  c，有：
     * <p>
     * 1. 若  c=‘c’，则需要一只青蛙开始发出蛙鸣，有  fog_num = fog_num + 1， cnt[‘c’] = cnt[‘c’] + 1。
     * 2.否则我们记  prec 为一次有效蛙鸣中该字符  c 的前一个字符
     * 2.1. 若当前  cnt[prec] = 0，即没有青蛙可以发出字符  c，直接返回  −1。
     * 2.2. 否则  cnt[prec] = cnt[prec] − 1，cnt[c] = cnt[c] + 1。且当  c=‘k’ 时，说明一只青蛙完成了完整的一次蛙鸣，
     * 此时正在发出蛙鸣声的青蛙数目减一，有： fog_num=fog_num−1。
     * 若遍历完还有正在发出蛙鸣的青蛙，即  fog_num > 0，说明  croakOfFrogs 不是被若干有效的蛙鸣混合而成，直接返回 −1。否则我们只需要返回在遍历的过程中正在发出蛙鸣的青蛙数目的最大值即可。
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
        if (croakOfFrogs.length() % 5 != 0) {
            return -1;
        }
        int res = 0, frogNum = 0;
        int[] cnt = new int[4];
        Map<Character, Integer> map = new HashMap<>() {{
            put('c', 0);
            put('r', 1);
            put('o', 2);
            put('a', 3);
            put('k', 4);
        }};
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char c = croakOfFrogs.charAt(i);
            int t = map.get(c);
            if (t == 0) {
                cnt[t]++;
                frogNum++;
                if (frogNum > res) {
                    res = frogNum;
                }
            } else {
                //如果当前字符不是 c ，则判断 当前字符的前一个 字符的数量是否为 0，
                if (cnt[t - 1] == 0) {
                    return -1;
                }
                cnt[t - 1]--;
                //说明这只青蛙唱完了，所以去掉
                if (t == 4) {
                    frogNum--;
                } else {
                    cnt[t]++;
                }
            }
        }
        if (frogNum > 0) {
            return -1;
        }
        return res;
    }
}
