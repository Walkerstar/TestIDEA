package mcw.test.leetcode.bzhan;

/**
 * 给你两个整数 x 和 y ，表示你在一个笛卡尔坐标系下的 (x, y) 处。同时，在同一个坐标系下给你一个数组 points ，
 * 其中 points[i] = [ai, bi] 表示在 (ai, bi) 处有一个点。当一个点与你所在的位置有相同的 x 坐标或者相同的 y 坐标时，我们称这个点是 有效的 。
 * <p>
 * 请返回距离你当前位置 曼哈顿距离 最近的 有效 点的下标（下标从 0 开始）。如果有多个最近的有效点，请返回下标 最小 的一个。如果没有有效点，请返回 -1 。
 * <p>
 * 两个点 (x1, y1) 和 (x2, y2) 之间的 曼哈顿距离 为 abs(x1 - x2) + abs(y1 - y2) 。
 *
 * @author mcw 2022/12/1 15:38
 */
public class leetCode1779 {

    //2ms
    public static int nearestValidPoint(int x, int y, int[][] points) {
        int index = -1;
        int distance = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            if (points[i][0] != x && points[i][1] != y) {
                continue;
            }
            int dis = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
            if (dis < distance) {
                distance = dis;
                index = i;

                //如果有多个最近的有效点，请返回下标 最小 的一个
                //此处的下标是指当前点在数组中的下标索引，故此 不需要判断 有效点间谁的坐标 与目标点最近
                //if (Math.abs(points[i][0]-x) <= Math.abs(result[0][0]-x) || Math.abs(points[i][1]-y) <= Math.abs(result[0][1]-y)) {
                //    result[0][0] = points[i][0];
                //    result[0][1] = points[i][1];
                //}
            }
        }
        return index;
    }

    //1ms
    public static int nearestValidPoint2(int x, int y, int[][] points) {
        int minDis = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i=0;i<points.length;i++){
            if(points[i][0] == x || points[i][1] == y){
                int dis = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
                if(dis < minDis){
                    minDis = dis;
                    minIndex = i;
                }
            }
        }
        return minIndex;

    }

    public static void main(String[] args) {
        int x = 12,  y = 6;
        int[][] points = {{1,2},{3,1},{2,4},{2,3},{4,4}};
       // points= new int[][]{{3, 4}};
        points=new int[][]{{13,6},{11,6}};
        System.out.println(nearestValidPoint(x, y, points));
    }
}
