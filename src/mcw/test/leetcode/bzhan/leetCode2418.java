package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个字符串数组 names ，和一个由 互不相同 的正整数组成的数组 heights 。两个数组的长度均为 n 。
 * <p>
 * 对于每个下标 i，names[i] 和 heights[i] 表示第 i 个人的名字和身高。
 * <p>
 * 请按身高 降序 顺序返回对应的名字数组 names 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：names = ["Mary","John","Emma"], heights = [180,165,170]
 * 输出：["Mary","Emma","John"]
 * 解释：Mary 最高，接着是 Emma 和 John 。
 * 示例 2：
 * <p>
 * 输入：names = ["Alice","Bob","Bob"], heights = [155,185,150]
 * 输出：["Bob","Alice","Bob"]
 * 解释：第一个 Bob 最高，然后是 Alice 和第二个 Bob 。
 * <p>
 * 提示：
 * <p>
 * n == names.length == heights.length
 * 1 <= n <= 10^3
 * 1 <= names[i].length <= 20
 * 1 <= heights[i] <= 10^5
 * names[i] 由大小写英文字母组成
 * heights 中的所有值互不相同
 *
 * @author mcw 2023/4/25 11:00
 */
public class leetCode2418 {

    /**
     * 方法一：排序
     * <p>
     * 我们可以将 names[i] 和 heights[i] 绑定为一个二元组，然后对所有的二元组按照 heights 排序。最后取出其中的 names 即为答案。
     * <p>
     * 除了以上方法，我们还可以创建一个索引数组 indices，其中 indices[i]=i。排序完成后，
     * 对于所有的 i, j~(i < j) 都有 heights[indices[i]]>heights[indices[j]]。
     * 然后我们遍历 i 从 0 到 n-1，将 names[indices[i]] 追加到答案数组中。
     */
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> heights[b] - heights[a]);
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = names[indices[i]];
        }
        return res;
    }
}
