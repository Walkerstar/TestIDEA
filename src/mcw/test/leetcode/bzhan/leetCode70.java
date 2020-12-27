package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\5 0005-15:01
 * climbing Stairs(爬楼梯)
 */
public class leetCode70 {
    public static int stairs(int n){
        if (n == 1) return 1;
        if (n == 2) return 2;
        int current = 2;
        int prev = 1;
        for (int i = 3; i <= n; i++) {
            current = current + prev;
            prev = current - prev;
        }
        return current;
    }
}
