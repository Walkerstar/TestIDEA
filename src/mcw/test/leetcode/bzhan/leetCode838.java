package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * <p>
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * <p>
 * 返回表示最终状态的字符串。
 *
 * @author MCW 2022/2/21
 */
public class leetCode838 {

    /**
     * 方法一：广度优先搜索
     * 这样的思路类似于广度优先搜索。我们用一个队列 q 模拟搜索的顺序；数组 time 记录骨牌翻倒或者确定不翻倒的时间，翻倒的骨牌不会对正在翻倒
     * 或者已经翻倒的骨牌施加力；数组 force 记录骨牌受到的力，骨牌仅在受到单侧的力时会翻倒。
     */
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        Deque<Integer> queue = new ArrayDeque<>();
        int[] time = new int[n];
        Arrays.fill(time, -1);
        List<Character>[] force = new List[n];
        for (int i = 0; i < n; i++) {
            force[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            char f = dominoes.charAt(i);
            if (f != '.') {
                queue.offer(i);
                time[i] = 0;
                force[i].add(f);
            }
        }
        char[] res = new char[n];
        Arrays.fill(res, '.');
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (force[i].size() == 1) {
                char f = force[i].get(0);
                res[i] = f;
                int ni = f == 'L' ? i - 1 : i + 1;
                if (ni >= 0 && ni < n) {
                    int t = time[i];
                    if (time[ni] == -1) {
                        queue.offer(ni);
                        time[ni] = t + 1;
                        force[ni].add(f);
                    } else if (time[ni] == t + 1) {
                        force[ni].add(f);
                    }
                }
            }
        }
        return new String(res);
    }

    /**
     * 我们可以枚举所有连续的没有被推动的骨牌，根据这段骨牌的两边骨牌（如果有的话）的推倒方向决定这段骨牌的最终状态：<br/>
     * 如果两边的骨牌同向，那么这段连续的竖立骨牌会倒向同一方向。
     * 如果两边的骨牌相对，那么这段骨牌会向中间倒。
     * 如果两边的骨牌相反，那么这段骨牌会保持竖立。
     * <p>
     * 我们可以使用两个指针 i 和 j 来枚举所有连续的没有被推动的骨牌，left 和 right 表示两边骨牌的推倒方向。
     * 根据上述三种情况来计算骨牌的最终状态。
     */
    public String pushDominoes2(String dominoes) {
        char[] s = dominoes.toCharArray();
        int n = s.length;
        int i = 0;
        char left = 'L';
        while (i < n) {
            int j = i;
            //找到一段连续的没有被推动的骨牌
            while (j < n && s[j] == '.') {
                j++;
            }
            char right = j < n ? s[j] : 'R';
            //方向相同，那么这些竖立骨牌也会倒向同一方向
            if (left == right) {
                while (i < j) {
                    s[i++] = right;
                }
            } else if (left == 'R' && right == 'L') {
                //方向相对时，那么就从两侧向中间倒
                int k = j - 1;
                while (i < k) {
                    s[i++] = 'R';
                    s[k--] = 'L';
                }
            }
            left = right;
            i = j + 1;
        }
        return new String(s);
    }

    public static void main(String[] args) {
        String d=".L.R...LR..L..";
        System.out.println(new leetCode838().pushDominoes2(d));
    }
}
