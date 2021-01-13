package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * @author mcw 2021/1/12 19:41
 */
public class leetCode1203 {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        //第一步，数据预处理，给没有归属于一个组的项目编上编号
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = m;
                m++;
            }
        }

        //第二步，实例化组合项目的邻接表
        List<Integer>[] groupAdj = new ArrayList[m];
        List<Integer>[] itemAdj = new ArrayList[n];
        for (int i = 0; i < m; i++) {
            groupAdj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            itemAdj[i] = new ArrayList<>();
        }

        //第三步，建图合统计入度数组
        int[] groupInDegree = new int[m];
        int[] itemInDegree = new int[n];
        int len = group.length;
        for (int i = 0; i < len; i++) {
            int currentGroup = group[i];
            for (Integer before : beforeItems.get(i)) {
                int beforeGroup = group[before];
                if (beforeGroup != currentGroup) {
                    groupAdj[beforeGroup].add(currentGroup);
                    groupInDegree[currentGroup]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (Integer item : beforeItems.get(i)) {
                itemAdj[item].add(i);
                itemInDegree[i]++;
            }
        }

        //第四步，得到组和项目的拓扑排序结果
        List<Integer> groupList = topologicalSort(groupAdj, groupInDegree, m);
        if (groupList.size() == 0) {
            return new int[0];
        }
        List<Integer> itemList = topologicalSort(itemAdj, itemInDegree, n);
        if (itemList.size() == 0) {
            return new int[0];
        }

        //第五步，根据项目的拓扑排序结果，项目到组的多对一关系，建立组到项目的一对多关系
        //key : 组，value : 在同一组的项目列表
        Map<Integer, List<Integer>> group2Items = new HashMap<>();
        for (Integer item : itemList) {
            group2Items.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
        }

        //第六步，把组的拓扑排序结果替换成为项目的拓扑排序结果
        List<Integer> res = new ArrayList<>();
        for (Integer groupId : groupList) {
            List<Integer> items = group2Items.getOrDefault(groupId, new ArrayList<>());
            res.addAll(items);
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private List<Integer> topologicalSort(List<Integer>[] adj, int[] inDegree, int n) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer front = queue.poll();
            res.add(front);
            for (Integer successor : adj[front]) {
                inDegree[successor]--;
                if (inDegree[successor] == 0) {
                    queue.offer(successor);
                }
            }
        }
        if (res.size() == 0) {
            return res;
        }
        return new ArrayList<>();
    }
}