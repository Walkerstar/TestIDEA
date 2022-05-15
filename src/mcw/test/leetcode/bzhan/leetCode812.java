package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 *
 * @author MCW 2022/5/15
 */
public class leetCode812 {

    /**
     * 方法一:枚举
     * 设三角形三个顶点的坐标为(x1,y1),(x2,y2),(x3,y3),则三角形的面积 S 可以用行列式的绝对值表示:
     * S= (1/2)|x1y2 + x2y3 + x3y1 - x1y3 - x2y1 - x3y2|
     */
    public static double largestTriangleArea(int[][] points) {
        int n = points.length;
        double ret = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    ret = Math.max(ret,
                            triangleArea(points[i][0], points[i][1], points[j][0], points[j][1], points[k][0], points[k][1]));
                }
            }
        }
        return ret;
    }

    public static double triangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2);
    }

    /**
     * 方法二：凸包
     * 思路与算法
     * <p>
     * 我们先使用 Andrew 算法求出所有点对应的凸包 convexHull，参考官方题解「587. 安装栅栏」的凸包算法。
     * <p>
     * 如果三角形的某一点不在凸包上，我们以其余两点的边为底，那么我们总可以在凸包上找到一个点，使得该点到此边的高大于原来的点到此边的高，
     * 因此面积最大的三角形的三个点都在凸包上。
     * <p>
     * 在凸包 convexHull 上枚举三角形，先枚举点 i，然后枚举点 j，最后枚举点 k，其中 i < j < k。
     * <p>
     * 在固定点 i 和 j 后，三角形的面积与 k 的关系是一个凸曲线，因此三角形只在 k 为极点时面积最大。在固定点 i 时，该极点在随点 j 增大而增大，
     * 因此在搜索极点只需要从上次的极点位置开始搜索。
     * <p>
     * 所以我们不需要枚举点 k，只需要搜索点 i 和 j 对应的极点，然后求解三角形面积。返回最大的三角形面积。
     */
    public double largestTriangleArea2(int[][] points) {
        int[][] convexHull = getConvexHull(points);
        int n = convexHull.length;
        double ret = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1, k = i + 2; j + 1 < n; j++) {
                while (k + 1 < n) {
                    double curArea = triangleArea(
                            convexHull[i][0], convexHull[i][1],
                            convexHull[j][0], convexHull[j][1],
                            convexHull[k][0], convexHull[k][1]);
                    double nextArea = triangleArea(
                            convexHull[i][0], convexHull[i][1],
                            convexHull[j][0], convexHull[j][1],
                            convexHull[k + 1][0], convexHull[k + 1][1]);
                    if (curArea >= nextArea) {
                        break;
                    }
                    k++;
                }
                double area = triangleArea(
                        convexHull[i][0], convexHull[i][1],
                        convexHull[j][0], convexHull[j][1],
                        convexHull[k][0], convexHull[k][1]);
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    public int[][] getConvexHull(int[][] points) {
        int n = points.length;
        if (n < 4) {
            return points;
        }
        /* 按照 x 大小进行排序，如果 x 相同，则按照 y 的大小进行排序 */
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        List<int[]> hull = new ArrayList<int[]>();
        /* 求出凸包的下半部分 */
        for (int i = 0; i < n; i++) {
            while (hull.size() > 1 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        int m = hull.size();
        /* 求出凸包的上半部分 */
        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() > m && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        /* hull[0] 同时参与凸包的上半部分检测，因此需去掉重复的 hull[0] */
        hull.remove(hull.size() - 1);
        m = hull.size();
        int[][] hullArr = new int[m][];
        for (int i = 0; i < m; i++) {
            hullArr[i] = hull.get(i);
        }
        return hullArr;
    }

    public int cross(int[] p, int[] q, int[] r) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
    }


    public static void main(String[] args) {
        System.out.println(largestTriangleArea(new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}}));
    }
}
