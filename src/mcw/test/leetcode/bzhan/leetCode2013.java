package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：
 *  添加 一个在数据流中的新点到某个数据结构中。可以添加 重复 的点，并会视作不同的点进行处理。
 *  给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
 * 轴对齐正方形 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。
 *
 * 实现 DetectSquares 类：
 *  DetectSquares() 使用空数据结构初始化对象
 *  void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
 *  int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
 *
 * @author mcw 2022/1/26 14:19
 */
public class leetCode2013 {

    class DetectSquares {
        //先把点按照行来划分，键为行的纵坐标，值为另一个哈希表，其中键为该行中的点的横坐标，值为这样的点的个数。
        Map<Integer, Map<Integer, Integer>> cnt;

        public DetectSquares() {
            cnt = new HashMap<>();
        }

        public void add(int[] point) {
            int x = point[0], y = point[1];
            cnt.putIfAbsent(y, new HashMap<>());
            Map<Integer, Integer> yCnt = cnt.get(y);
            yCnt.put(x, yCnt.getOrDefault(x, 0) + 1);
        }

        /**
         *先考虑如何实现 int count(int[] point)，记输入的 point 的横纵坐标分别为 x 和 y。则形成的正方形的上下两条边中，其中一条边的纵坐标为 y，
         * 我们枚举另一条边的纵坐标为 col，则正方形的边长 d 为 |y - col|且大于 0。有了其中一个点的坐标 (x,y) 和一条横边的纵坐标 col，
         * 我们可以得到正方形的四个点的坐标分别为 (x,y)，(x,col)，(x+d,y)，(x+d,col) 或 (x,y)，(x,col)，(x−d,y)，(x−d,col)。
         *
         * 据此，我们可以用一个哈希表来存储 void add(int[] point) 函数中加入的点。先把点按照行来划分，键为行的纵坐标，值为另一个哈希表，
         * 其中键为该行中的点的横坐标，值为这样的点的个数。因为点会重复出现，所以计算正方形的个数时需要把另外三个坐标出现的次数相乘。
         *
         */
        public int count(int[] point) {
            int res = 0;
            int x = point[0], y = point[1];
            if (!cnt.containsKey(y)) {
                return 0;
            }
            Map<Integer, Integer> yCnt = cnt.get(y);
            Set<Map.Entry<Integer, Map<Integer, Integer>>> entries = cnt.entrySet();
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : entries) {
                int col = entry.getKey();
                Map<Integer, Integer> colCnt = entry.getValue();
                if (col != y) {
                    //根据对称性，这里可以不用取绝对值
                    int d = col - y;
                    res += colCnt.getOrDefault(x, 0) * yCnt.getOrDefault(x + d, 0) * colCnt.getOrDefault(x + d, 0);
                    res += colCnt.getOrDefault(x, 0) * yCnt.getOrDefault(x - d, 0) * colCnt.getOrDefault(x - d, 0);
                }
            }
            return res;
        }
    }
}
