package mcw.test.leetcode.bzhan;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。
 * stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 * <p>
 * 示例 1：
 * 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：jewels = "z", stones = "ZZ"
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 1 <= jewels.length, stones.length <= 50
 * jewels 和 stones 仅由英文字母组成
 * jewels 中的所有字符都是 唯一的
 *
 * @author MCW 2023/7/24
 */
public class leetCode771 {

    /**
     * 方法一：暴力
     * 思路与算法
     * <p>
     * 暴力法的思路很直观，遍历字符串 stones，对于 stones 中的每个字符，遍历一次字符串 jewels，如果其和 jewels 中的某一个字符相同，则是宝石。
     */
    public int numJewelsInStones(String jewels, String stones) {
        int jewelsCount = 0;
        int jewelsLength = jewels.length();
        int stonesLength = stones.length();
        for (int i = 0; i < stonesLength; i++) {
            char stone = stones.charAt(i);
            for (int j = 0; j < jewelsLength; j++) {
                char jewel = jewels.charAt(j);
                if (stone == jewel) {
                    jewelsCount++;
                    break;
                }
            }
        }
        return jewelsCount;
    }


    /**
     * 方法二：哈希集合
     * 思路与算法
     * <p>
     * 方法一中，对于字符串 stones 中的每个字符，都需要遍历一次字符串 jewels，导致时间复杂度较高。
     * 如果使用哈希集合存储字符串 jewels 中的宝石，则可以降低判断的时间复杂度。
     * <p>
     * 遍历字符串 jewels，使用哈希集合存储其中的字符，然后遍历字符串 stones，对于其中的每个字符，如果其在哈希集合中，则是宝石。
     */
    public int numJewelsInStones2(String jewels, String stones) {
        int jewelCount = 0;
        Set<Character> jewelsSet = new HashSet<>();
        int jewelsLength = jewels.length();
        int stonesLength = stones.length();
        for (int i = 0; i < jewelsLength; i++) {
            char c = jewels.charAt(i);
            jewelsSet.add(c);
        }
        for (int i = 0; i < stonesLength; i++) {
            char stone = stones.charAt(i);
            if (jewelsSet.contains(stone)) {
                jewelCount++;
            }
        }
        return jewelCount;
    }
}
