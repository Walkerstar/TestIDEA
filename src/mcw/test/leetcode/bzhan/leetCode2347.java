package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个整数数组 ranks 和一个字符数组 suit 。你有 5 张扑克牌，第 i 张牌大小为 ranks[i] ，花色为 suits[i] 。
 * <p>
 * 下述是从好到坏你可能持有的 手牌类型 ：
 * <p>
 * "Flush"：同花，五张相同花色的扑克牌。
 * "Three of a Kind"：三条，有 3 张大小相同的扑克牌。
 * "Pair"：对子，两张大小一样的扑克牌。
 * "High Card"：高牌，五张大小互不相同的扑克牌。
 * 请你返回一个字符串，表示给定的 5 张牌中，你能组成的 最好手牌类型 。
 * <p>
 * 注意：返回的字符串 大小写 需与题目描述相同。
 * <p>
 * 提示：
 *
 * <li>ranks.length == suits.length == 5</li>
 * <li>1 <= ranks[i] <= 13</li>
 * <li>'a' <= suits[i] <= 'd'</li>
 * <li>任意两张扑克牌不会同时有相同的大小和花色。</li>
 *
 * @author mcw 2023/2/20 11:21
 */
public class leetCode2347 {
    public String bestHand(int[] ranks, char[] suits) {
        Set<Character> suitSet = new HashSet<>();
        for (char suit : suits) {
            suitSet.add(suit);
        }
        if (suitSet.size() == 1) {
            return "Flush";
        }
        Map<Integer, Integer> h = new HashMap<>();
        for (int rank : ranks) {
            h.put(rank, h.getOrDefault(rank, 0) + 1);
        }
        if (h.size() == 5) {
            return "High Card";
        }
        for (Map.Entry<Integer, Integer> entry : h.entrySet()) {
            if (entry.getValue() > 2) {
                return "There of a Kind";
            }
        }
        return "Pair";
    }
}
