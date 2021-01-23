package mcw.test.leetcode.bzhan;

/**
 * 在一个XY 坐标系中有一些点，我们用数组coordinates来分别记录它们的坐标，其中coordinates[i] = [x, y]
 * 表示横坐标为 x、纵坐标为 y的点。
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * @author mcw 2021/1/17 20:05
 */
public class leetCode1232 {

    public static boolean checkStraightLine(int[][] coordinates) {

        int a = coordinates[1][0];
        int b = coordinates[1][1];
        int dx = a - coordinates[0][0];
        int dy = b - coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            if ((coordinates[i][0] - a) * dy != (coordinates[i][1] - b) * dx) {
                return false;
            }
        }
        return true;
    }
}
