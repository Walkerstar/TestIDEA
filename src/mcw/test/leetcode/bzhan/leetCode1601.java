package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 * <p>
 * 给你一个数组 requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼。
 * <p>
 * 一开始 所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0 。意思是每栋楼 离开 的员工数目
 * 等于 该楼 搬入 的员工数数目。比方说 n = 3 且两个员工要离开楼 0 ，一个员工要离开楼 1 ，一个员工要离开楼 2 ，如果该请求列表可行，
 * 应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 。
 * <p>
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 *
 * @author mcw 2022/2/28 11:01
 */
public class leetCode1601 {


    // 方法一 : 回溯 + 枚举

    /**
     * delta 记录每一栋楼的员工变化量 ; 变量 cnt 记录被选择的请求数量 ; delta 中的 0 的个数，记作 zero
     */
    int[] delta;
    int ans = 0, cnt = 0, zero, n;

    public int maximumRequests(int n, int[][] requests) {
        delta = new int[n];
        zero = n;
        this.n = n;
        dfs(requests, 0);
        return ans;
    }

    public void dfs(int[][] requests, int pos) {
        if (pos == requests.length) {
            if (zero == n) {
                ans = Math.max(ans, cnt);
            }
            return;
        }
        //不选 requests[pos]
        dfs(requests, pos + 1);

        //选 requests[pos]
        // delta[x] 的值减 1，delta[y] 的值加 1，cnt 的值加 1
        int z = zero;
        ++cnt;
        int[] r = requests[pos];
        int x = r[0], y = r[1];

        //delta[x] 增加或减少前为 0，则将 zero 减 1；如果 delta[x] 增加或减少后为 0，则将 zero 加 1。
        zero -= delta[x] == 0 ? 1 : 0;
        --delta[x];
        zero += delta[x] == 0 ? 1 : 0;

        zero -= delta[y] == 0 ? 1 : 0;
        ++delta[y];
        zero += delta[y] == 0 ? 1 : 0;

        dfs(requests, pos + 1);
        --delta[y];
        ++delta[x];
        --cnt;
        zero = z;
    }


    /**
     * 方法二 : 二进制枚举
     *
     *我们可以使用一个长度为 m 的二进制数 mask 表示所有的请求，其中 mask 从低到高的第 i 位为 1 表示选择第 i 个请求，为 0 表示不选第 i 个请求。
     * 我们可以枚举 [0,2^m-1] 范围内的所有 mask，对于每个 mask，依次枚举其每一位，判断是否为 1，并使用与方法一相同的数组 delta
     * 以及变量 cnt 进行统计，在满足要求时更新答案。
     */
    public int maximumRequests2(int n, int[][] requests) {
        int[] delta = new int[n];
        int ans = 0, m = requests.length;
        for (int mask = 0; mask < (1 << m); ++mask) {
            int cnt = Integer.bitCount(mask);
            if (cnt <= ans) {
                continue;
            }
            Arrays.fill(delta, 0);
            for (int i = 0; i < m; ++i) {
                if ((mask & (1 << i)) != 0) {
                    ++delta[requests[i][0]];
                    --delta[requests[i][1]];
                }
            }
            boolean flag = true;
            for (int x : delta) {
                if (x != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans = cnt;
            }
        }
        return ans;
    }


    // 方法三 : 暴力枚举
    int requests[][];

    public int maximumRequests3(int n, int[][] requests) {
        this.requests = requests;
        int max = 1 << requests.length;
        int ans = 0;
        for (int i = 0; i < max; i++) {
            int bitCount = Integer.bitCount(i);
            if (bitCount < ans) {
                continue;
            }
            if (possible(n, i)) {
                ans = bitCount;
            }
        }
        return ans;
    }

    public boolean possible(int n, int k) {
        int shift[] = new int[n];
        for (int i = 0; i < requests.length; i++) {
            if ((k & 1) == 1) {
                shift[requests[i][0]]++;
                shift[requests[i][1]]--;
            }
            k >>= 1;
        }
        for (int i = 0; i < n; i++) {
            if (shift[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] requests = new int[][]{{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}};

        System.out.println(new leetCode1601().maximumRequests(5, requests));
    }
}
