package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。
 * <p>
 * 数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。
 * 例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 + (-2)^0 = -3。数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。
 * <p>
 * 返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。
 * <p>
 * 示例 1：
 * 输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * 输出：[1,0,0,0,0]
 * 解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
 * <p>
 * 示例 2：
 * 输入：arr1 = [0], arr2 = [0]
 * 输出：[0]
 * <p>
 * 示例 3：
 * 输入：arr1 = [0], arr2 = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * <p>
 * 1 <= arr1.length, arr2.length <= 1000
 * arr1[i] 和 arr2[i] 都是 0 或 1
 * arr1 和 arr2 都没有前导0
 *
 * @author MCW 2023/5/18
 */
public class leetCode1073 {

    /**
     * 方法一：模拟
     * 思路与算法
     * <p>
     * 为了叙述方便，记 arr_1[i] 和 arr_2[i] 分别是 arr_1  和 arr_2 从低到高的第 i 个数位。在加法运算中，我们需要将它们相加。
     * <p>
     * 对于普通的二进制数相加，我们可以从低位到高位进行运算，在运算的过程中维护一个变量 carry 表示进位。当运算到第 i 位时，令
     * x = arr_1[i] + arr_2[i] + carry：
     * <p>
     * 如果 x=0,1，第 i 位的结果就是 x，并且将carry 置 0；
     * <p>
     * 如果 x=2,3，第 i 位的结果是 x−2，需要进位，将 carry 置 1。
     * <p>
     * 而本题中是「负二进制数」，第 i 位对应的是 (−2)^i ，而第 i+1 位对应的是 (−2)^(i+1) ，是第 i 位的 −2 倍。
     * 因此，当第 i 位发生进位时， carry 应当置为 −1，而不是 1。
     * <p>
     * 此时，由于 arr_1[i] 和 arr_2[i] 的范围都是 {0,1}，而 carry 的范围从{0,1} 变成了 {−1,0}，因此 x 的范围从{0,1,2,3} 变成了 {−1,0,1,2}。那么：
     * <p>
     * 如果 x=0,1，第 i 位的结果就是 x，并且将 carry 置 0；
     * <p>
     * 如果 x=2，第 i 位的结果是 x−2，需要进位，将 carry 置 −1；
     * <p>
     * 如果 x=−1，此时第 i 位的结果应该是什么呢？可以发现，由于：
     * <p>
     * −(−2)^i = (−2)^(i+1) + (−2)^i
     * <p>
     * 等式左侧表示第  i 位为 −1 的值，右侧表示第  i 和 i+1 位为 1 的值。
     * 因此，第 i 位的结果应当为  1，同时需要进位，将 carry 置  1（注意这里不是 −1）。
     * 最终， carry 的范围为  {−1,0,1}，会多出 x=3 的情况，但它与 x=2 的情况是一致的。
     * <p>
     * 细节
     * <p>
     * 在最坏的情况下，当我们计算完  arr_1 和  arr_2 的最高位的  x 时，得到的结果为  x=3，此时产生 −1 的进位，
     * 而 −1 在之后又会产生  1 的进位，因此，答案的长度最多为  arr_1 和  arr_2  中较长的长度加  2。
     * <p>
     * 由于题目描述中  arr_1[i] 和  arr_2[i] 表示的是  arr_1 和 arr_2 从高到低的第  i 个数位，与题解中的叙述相反。
     * 因此，在实际的代码编写中，我们可以使用两个指针从  arr_1 和 arr_2 的末尾同时开始进行逆序的遍历，
     * 得到逆序的答案，去除前导零，再将答案反转即可得到顺序的最终答案。
     */
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int carry = 0;
        List<Integer> ans = new ArrayList<>();
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = carry;
            if (i >= 0) {
                x += arr1[i];
            }
            if (j >= 0) {
                x += arr2[j];
            }
            if (x >= 2) {
                ans.add(x - 2);
                carry = -1;
            } else if (x >= 0) {
                ans.add(x);
                carry = 0;
            } else {
                ans.add(1);
                carry = 1;
            }
            --i;
            --j;
        }
        while (ans.size() > 1 && ans.get(ans.size() - 1) == 0) {
            ans.remove(ans.size() - 1);
        }
        int[] arr = new int[ans.size()];
        for (i = 0, j = ans.size() - 1; j >= 0; i++, j--) {
            arr[i] = ans.get(j);
        }
        return arr;
    }
}
