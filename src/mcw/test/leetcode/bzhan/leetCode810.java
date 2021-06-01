package mcw.test.leetcode.bzhan;

/**
 * 黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或运算
 * 得出的结果等于 0 的话，当前玩家游戏失败。(另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为0。）
 * <p>
 * 并且，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
 * <p>
 * 假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
 *
 * @author mcw 2021/5/22 10:39
 */
public class leetCode810 {

    /**
     * 可以先判断数组 nums 的长度是否是偶数，当长度是偶数时直接返回 true，当长度是奇数时才需要遍历数组计算全部元素的异或结果
     */
    public boolean xorGame(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }
}
