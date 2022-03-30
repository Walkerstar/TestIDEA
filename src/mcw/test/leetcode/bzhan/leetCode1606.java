package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则如下：
 * <p>
 * 第 i （序号从 0 开始）个请求到达。
 * 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
 * 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
 * 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
 * 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
 * <p>
 * 请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
 *
 * @author mcw 2022/3/30 15:19
 */
public class leetCode1606 {

    /**
     * 模拟 + 有序集合 + 优先队列
     */
    public List<Integer> busiesServers(int k, int[] arrival, int[] load) {
        //空闲服务器的编号集合
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        //正在处理请求的服务器的处理结束时间和编号，队首的服务器的处理结束时间最小
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        //记录对应服务器处理的请求数目
        int[] requests = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            //将 在 arrival[i] 时或之前，处理完成的服务器加入到空闲集合中
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add(busy.poll()[1]);
            }
            //服务器不可用时，舍弃当前请求
            if (available.isEmpty()) {
                continue;
            }
            //选择当前请求对应的服务器，如果在使用中，依次选择下一个
            Integer p = available.ceiling(i % k);
            if (p == null) {
                p = available.first();
            }
            //对应服务器请求次数 +1
            requests[p]++;
            //将 请求达到时间+处理时间， 服务器编号， 加入到处理队列中
            busy.offer(new int[]{arrival[i] + load[i], p});
            //从空闲集合中删除该服务器编号
            available.remove(p);
        }
        int maxRequest = Arrays.stream(requests).max().getAsInt();
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) {
                ret.add(i);
            }
        }
        return ret;
    }

    /**
     * 模拟 + 双优先队列
     * 设在第 i 个请求到来时，编号为 id 的服务器已经处理完请求，那么将 id 从 busy 中移除，并放入一个不小于 i 且同余于 id 的编号，
     * 这样就能在保证 available 中，编号小于 i mod k 的空闲服务器能排到编号不小于 i mod k 的空闲服务器后面。
     */
    public List<Integer> busiesServers2(int k, int[] arrival, int[] load) {
        PriorityQueue<Integer> available = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        for (int i = 0; i < k; i++) {
            available.offer(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[] request = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int id = busy.peek()[1];
                busy.poll();
                // 保证得到的是一个不小于 i 的且与 id 同余的数
                available.offer(i + ((id - i) % k + k) % k);
            }
            if (available.isEmpty()) {
                continue;
            }
            int server = available.poll() % k;
            request[server]++;
            busy.offer(new int[]{arrival[i] + load[i], server});
        }
        int maxRequest = Arrays.stream(request).max().getAsInt();
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (request[i] == maxRequest) {
                ret.add(i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new leetCode1606().busiesServers2(3, new int[]{1, 2, 3, 4, 5}, new int[]{5, 2, 3, 3, 3}));
    }
}
