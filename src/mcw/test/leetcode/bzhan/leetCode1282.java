package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有 n 个人被分成数量未知的组。每个人都被标记为一个从 0 到 n - 1 的唯一ID 。
 * <p>
 * 给定一个整数数组 groupSizes ，其中 groupSizes[i] 是第 i 个人所在的组的大小。例如，如果 groupSizes[1] = 3 ，则第 1 个人必须位于大小为 3 的组中。
 * <p>
 * 返回一个组列表，使每个人 i 都在一个大小为 groupSizes[i] 的组中。
 * <p>
 * 每个人应该 恰好只 出现在 一个组 中，并且每个人必须在一个组中。如果有多个答案，返回其中 任何 一个。可以 保证 给定输入 至少有一个 有效的解。
 *
 * @author mcw 2022/8/12 17:37
 */
public class leetCode1282 {
    public List<List<Integer>> groupThePeople(int[] groupSize) {
        //将每个人分配到所属的组中
        Map<Integer, List<Integer>> groups = new HashMap<>();
        int n = groupSize.length;
        for (int i = 0; i < n; i++) {
            int size = groupSize[i];
            groups.putIfAbsent(size, new ArrayList<Integer>());
            groups.get(size).add(i);
        }

        // 然后遍历哈希表，对于大小为 x 的组，得到对应的所有人编号列表，将列表的大小记为 y，则 y 一定能被 x 整除，
        // 将列表分成 x/y 个大小为 x 的组，并将每个组添加到答案中。遍历结束之后，即完成分组。
        List<List<Integer>> groupList = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : groups.entrySet()) {
            int size = entry.getKey();
            List<Integer> people = entry.getValue();
            int groupCount = people.size() / size;
            for (int i = 0; i < groupCount; i++) {
                List<Integer> group = new ArrayList<>();
                int start = i * size;
                for (int j = 0; j < size; j++) {
                    group.add(people.get(start + 1));
                }
                groupList.add(group);
            }
        }

        return groupList;
    }
}
