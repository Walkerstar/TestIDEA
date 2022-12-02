package mcw.test.leetcode.bzhan;

/**
 * 有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
 * <p>
 * 在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。
 * 注意，操作执行后，某些盒子中可能会存在不止一个小球。
 * <p>
 * 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
 * <p>
 * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
 *
 * @author mcw 2022/12/2 15:59
 */
public class leetCode1769 {

    /**
     * 双重循环模拟
     */
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int sm = 0;
            for (int j = 0; j < n; j++) {
                if (boxes.charAt(j) == '1') {
                    sm += Math.abs(j - i);
                }
            }
            res[i] = sm;
        }
        return res;
    }

    /**
     * 根据前一个盒子的操作数得到下一个盒子的操作数
     *
     * 记把所有球转移到当前下标为 i 的盒子的操作数为 operation_i，初始情况下当前盒子及其左侧有 left_i 个球，右侧有 right_i个球。
     * 那么，已知这三者的情况下，把所有球转移到当前下标为 i+1 的盒子的操作数 operation_i 就可以由 operation_i + left_i − right_i 快速得出，
     * 因为原来左侧的 left_i个球各需要多操作一步，原来右侧的 right_i 个球可以各少操作一步。
     * 计算完 operation_(i+1) 后，需要更新 left_(i+1) 和 right_(i+1) 。
     * 而初始的 operation_0 ，left_0 和 right_0 可以通过模拟计算。
     */
    public int[] minOperations2(String boxes) {
        int left = boxes.charAt(0) - '0';
        int right = 0;
        int operations = 0;
        int n = boxes.length();
        for (int i = 1; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                right++;
                operations += i;
            }
        }
        int[] res = new int[n];
        res[0] = operations;
        for (int i = 1; i < n; i++) {
            operations += left - right;
            if (boxes.charAt(i) == '1') {
                left++;
                right--;
            }
            res[i] = operations;
        }
        return res;
    }
}
