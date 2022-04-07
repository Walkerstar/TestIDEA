package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <=i < len(arr) / 2，
 * 都有 arr[2 * i + 1] = 2 * arr[2 * i]”时，返回 true；否则，返回 false。
 *
 * @author mcw 2022/4/1 19:59
 */
public class leetCode954 {

    /**
     * 设 arr 的长度为 n，题目本质上是问 arr 能否分成 (n/2) 对元素，每对元素中一个数是另一个数的两倍。
     *
     * 设 cnt[x] 表示 arr 中 x 的个数。
     *
     * 对于 arr 中的 0，它只能与 0 匹配。如果 cnt[0] 是奇数，那么必然无法满足题目要求。
     * 去掉 arr 中的 0。设 x 为 arr 中绝对值最小的元素，由于没有绝对值比 x 更小的数，因此 x 只能与 2x 匹配。
     * 如果此时 cnt[2x]<cnt[x]，那么会有部分 x 无法找到它的另一半，即无法满足题目要求；否则将所有 x 和 cnt[x] 个 2x
     * 从 arr 中去掉，继续判断剩余元素是否满足题目要求。不断重复此操作，如果某个时刻 arr 为空，则说明 arr 可以满足题目要求。
     *
     * 代码实现时，我们可以用一个哈希表来统计 cnt，并将其键值按绝对值从小到大排序，然后模拟上述操作，
     * 去掉元素的操作可以改为从 cnt 中减去对应值。
     *
     */
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<>();
        //统计每个数出现的次数
        for (int i : arr) {
            cnt.put(i, cnt.getOrDefault(i, 0) + 1);
        }
        //判断索引为0 的数是否是奇数
        if (cnt.getOrDefault(0, 0) % 2 != 0) {
            return false;
        }
        List<Integer> vals = new ArrayList<>();
        //加入集合，排序
        for (Integer x : cnt.keySet()) {
            vals.add(x);
        }
        //Collections.sort(vals, (a, b) -> Math.abs(a) - Math.abs(b));
        Collections.sort(vals, Comparator.comparingInt(Math::abs));

        for (Integer x : vals) {
            // 无法找到足够的 2x 与 x 配对
            if (cnt.getOrDefault(2 * x, 0) < cnt.get(x)) {
                return false;
            }
            //配对后，将 配对的个数减去
            cnt.put(2 * x, cnt.getOrDefault(2 * x, 0) - cnt.get(x));
        }
        return true;
    }
}
