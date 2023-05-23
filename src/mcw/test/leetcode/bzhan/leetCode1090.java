package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。
 * 还会给出两个整数 numWanted 和 useLimit 。
 * <p>
 * 从 n 个元素中选择一个子集 s :
 * <p>
 * 子集 s 的大小 小于或等于 numWanted 。
 * s 中 最多 有相同标签的 useLimit 项。
 * 一个子集的 分数 是该子集的值之和。
 * <p>
 * 返回子集 s 的最大 分数 。
 * <p>
 * 示例 1：
 * 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
 * 输出：9
 * 解释：选出的子集是第一项，第三项和第五项。
 * <p>
 * 示例 2：
 * 输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
 * 输出：12
 * 解释：选出的子集是第一项，第二项和第三项。
 * <p>
 * 示例 3：
 * 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
 * 输出：16
 * 解释：选出的子集是第一项和第四项。
 * <p>
 * 提示：
 * <p>
 * n == values.length == labels.length
 * 1 <= n <= 2 * 10^4
 * 0 <= values[i], labels[i] <= 2 * 10^4
 * 1 <= numWanted, useLimit <= n
 *
 * @author MCW 2023/5/23
 */
public class leetCode1090 {

    /**
     * 方法一：排序 + 哈希表
     * 思路与算法
     * <p>
     * 我们首先将元素按照  values 的值进行降序排序。待排序完成后，我们依次遍历每个元素并判断是否选择。
     * 我们可以使用一个变量  choose 记录已经选择的元素个数，以及一个哈希表记录每一种标签已经选择的元素个数（键表示标签，值表示该标签已经选择的元素个数）：
     * <p>
     * 如果  choose = numWanted，我们可以直接退出遍历；
     * 如果当前元素的标签在哈希表中对应的值等于  useLimit，我们忽略这个元素，否则我们选择这个元素，并更新  choose、哈希表以及答案。
     * <p>
     * 细节
     * <p>
     * 由于题目中的  values 和  labels 是分成两个数组给出的，直接排序会比较困难。
     * 我们可以额外开辟一个同样长度为  n 的数组，存储下标，并直接在该数组上进行排序即可。
     */
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        Arrays.sort(id, (a, b) -> values[b] - values[a]);

        int ans = 0, choose = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n && choose < numWanted; i++) {
            int label = labels[id[i]];
            if (cnt.getOrDefault(label, 0) == useLimit) {
                continue;
            }
            ++choose;
            ans += values[id[i]];
            cnt.put(label, cnt.getOrDefault(label, 0) + 1);
        }
        return ans;
    }
}
