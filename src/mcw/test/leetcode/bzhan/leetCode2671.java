package mcw.test.leetcode.bzhan;

/**
 * 2671. 频率跟踪器
 * <p>
 * 请你设计并实现一个能够对其中的值进行跟踪的数据结构，并支持对频率相关查询进行应答。
 * <p>
 * 实现 FrequencyTracker 类：
 * <p>
 * FrequencyTracker()：使用一个空数组初始化 FrequencyTracker 对象。
 * void add(int number)：添加一个 number 到数据结构中。
 * void deleteOne(int number)：从数据结构中删除一个 number 。数据结构 可能不包含 number ，在这种情况下不删除任何内容。
 * bool hasFrequency(int frequency): 如果数据结构中存在出现 frequency 次的数字，则返回 true，否则返回 false。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入
 * ["FrequencyTracker", "add", "add", "hasFrequency"]
 * [[], [3], [3], [2]]
 * 输出
 * [null, null, null, true]
 * <p>
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.add(3); // 数据结构现在包含 [3]
 * frequencyTracker.add(3); // 数据结构现在包含 [3, 3]
 * frequencyTracker.hasFrequency(2); // 返回 true ，因为 3 出现 2 次
 * <p>
 * 示例 2：
 * <p>
 * 输入
 * ["FrequencyTracker", "add", "deleteOne", "hasFrequency"]
 * [[], [1], [1], [1]]
 * 输出
 * [null, null, null, false]
 * <p>
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.add(1); // 数据结构现在包含 [1]
 * frequencyTracker.deleteOne(1); // 数据结构现在为空 []
 * frequencyTracker.hasFrequency(1); // 返回 false ，因为数据结构为空
 * <p>
 * 示例 3：
 * <p>
 * 输入
 * ["FrequencyTracker", "hasFrequency", "add", "hasFrequency"]
 * [[], [2], [3], [1]]
 * 输出
 * [null, false, null, true]
 * <p>
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.hasFrequency(2); // 返回 false ，因为数据结构为空
 * frequencyTracker.add(3); // 数据结构现在包含 [3]
 * frequencyTracker.hasFrequency(1); // 返回 true ，因为 3 出现 1 次
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= number <= 10^5
 * 1 <= frequency <= 10^5
 * 最多调用 add、deleteOne 和 hasFrequency 共计 2 * 10^5 次
 *
 * @author MCW 2024/3/21
 */
public class leetCode2671 {

    /**
     * 方法一：哈希表
     * 思路与算法
     * <p>
     * 我们可以用一个哈希表 freq 来追踪每个 number 的出现频率，并利用另一个哈希表 freq_cnt 来记录各个频率的出现次数。
     * <p>
     * 对于元素 number 的添加或删除操作，流程如下：先调整 freq[number] 在 freq_cnt 中的计数（即先减少），
     * 随后更新 freq[number]（增加或减少其频率），最终在 freq_cnt 中更新新的频率计数（即增加）。
     * <p>
     * 在具体实现上，鉴于所有数值范围限定在 10^5 以内，数组足以作为哈希表的有效替代，既简化了代码，也提高了效率。
     */
    static class FrequencyTracker {

        private static final int N = 100001;
        private int[] freq;
        private int[] freqCnt;

        public FrequencyTracker() {
            freq = new int[N];
            freqCnt = new int[N];
        }

        public void add(int number) {
            --freqCnt[freq[number]];
            ++freq[number];
            ++freqCnt[freq[number]];
        }

        public void deleteOne(int number) {
            if (freq[number] == 0) {
                return;
            }
            --freqCnt[freq[number]];
            --freq[number];
            ++freqCnt[freq[number]];
        }

        public boolean hasFrequency(int frequency) {
            return freqCnt[frequency] > 0;
        }

    }


}
