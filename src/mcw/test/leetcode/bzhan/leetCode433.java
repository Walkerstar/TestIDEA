package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * <p>
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * <p>
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * <p>
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * <p>
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 *
 * @author mcw 2022/5/7 11:55
 */
public class leetCode433 {

    /**
     * 广度优先
     */
    public static int minMutation(String start, String end, String[] bank) {
        Set<String> cnt = new HashSet<>();
        Set<String> visited = new HashSet<>();
        char[] keys = {'A', 'C', 'G', 'T'};
        Collections.addAll(cnt, bank);
        //如果 初始字符串 与 最终字符串 相等，返回 0
        if (start.equals(end)) {
            return 0;
        }
        //如果 最终字符串不是有效字符串，返回 -1
        if (!cnt.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                //将 栈顶元素 取出，并在栈中删除
                String curr = queue.poll();
                //遍历 字符串，8位字符组成
                for (int j = 0; j < 8; j++) {
                    //遍历 当前字符 可以改变的 情况，
                    for (int k = 0; k < 4; k++) {
                        //如果当前字符，与 keys[k] 不等，则进行变化
                        if (keys[k] != curr.charAt(j)) {
                            StringBuffer sb = new StringBuffer(curr);
                            //将索引为 j 的字符改为 keys[k]
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            //如果当前字符串未使用过 且 基因库中 包含此字符串，则判断 该字符串 是否与最终字符串一致
                            if (!visited.contains(next) && cnt.contains(next)) {
                                if (next.equals(end)) {
                                    //如果一致，则返回 最小 次数
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * 预处理
     */
    public static int minMutation2(String start, String end, String[] bank) {
        int m = start.length();
        int n = bank.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int endIndex = -1;
        for (int i = 0; i < n; i++) {
            if (end.equals(bank[i])) {
                endIndex = i;
            }
            for (int j = i + 1; j < n; j++) {
                int mutations = 0;
                for (int k = 0; k < m; k++) {
                    if (bank[i].charAt(k) != bank[j].charAt(k)) {
                        mutations++;
                    }
                    if (mutations > 1) {
                        break;
                    }
                }
                if (mutations == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        if (endIndex == -1) {
            return -1;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int step = 1;
        for (int i = 0; i < n; i++) {
            int mutations = 0;
            for (int k = 0; k < m; k++) {
                if (start.charAt(k) != bank[i].charAt(k)) {
                    mutations++;
                }
                if (mutations > 1) {
                    break;
                }
            }
            if (mutations == 1) {
                queue.offer(i);
                visited[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int curr = queue.poll();
                if (curr == endIndex) {
                    return step;
                }
                for (int next : adj[curr]) {
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * 建图 ，BFS
     */
    public int minMutation3(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        int endIdx = -1, ans = 1;

        //存储每个基因的合法变化关系
        List<Integer>[] path = new List[bank.length];
        for (int i = 0; i < bank.length; i++) {
            path[i] = new ArrayList<>();
        }
        boolean used[] = new boolean[bank.length];
        for (int i = 0; i < bank.length; i++) {
            //基因库包含 初始字符 ，则标记为 已使用
            if (start.equals(bank[i])) {
                used[i] = true;
            } else {
                //记录 最终字符 在基因库中的下标
                if (end.equals(bank[i])) {
                    endIdx = i;
                }
                //遍历 基因库 下标 i 的后续字符串
                for (int j = i + 1; j < bank.length; j++) {
                    //如果 当前字符 变化一位后 ，与 后续字符 相同，即 能够合法变化。
                    if (numOfDifference(bank[i], bank[j]) == 1) {
                        //存储此对应关系
                        path[i].add(j);
                        path[j].add(i);
                    }
                }
            }
        }
        //如果 最终字符串不是有效字符串，返回 -1
        if (endIdx == -1) {
            return -1;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < bank.length; i++) {
            if (!used[i] && numOfDifference(start, bank[i]) == 1) {
                if (i == endIdx) {
                    return 1;
                }
                q.add(i);
                used[i] = true;
            }
        }
        while (q.size() > 0) {
            ans++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int a = q.poll();
                for (int j = 0; j < path[a].size(); j++) {
                    int b = path[a].get(j);
                    if (!used[b]) {
                        if (b == endIdx) {
                            return ans;
                        }
                        used[b] = true;
                        q.add(b);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 返回 s1 和 s2 中，下标对应字符不同的 个数
     */
    public int numOfDifference(String s1, String s2) {
        char c1[] = s1.toCharArray(), c2[] = s2.toCharArray();
        int count = 0;
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String start = "AACCGGTT", end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};

        System.out.println(minMutation2(start, end, bank));
    }
}
