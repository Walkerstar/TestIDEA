package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 对于非负整数 X  而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为[1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *
 * @author mcw 2021/1/23 18:56
 */
public class leetCode989 {
    public static List<Integer> addToArrayForm(int[] A, int k) {
        List<Integer> list = new ArrayList<>();
        int n = A.length;
        for (int i = n - 1; i >= 0 || k > 0; --i, k /= 10) {
            if (i >= 0) {
                k += A[i];
            }
            list.add(k % 10);
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> addToArrayForm1(int[] A, int K) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for (int i = n - 1; i >= 0; --i) {
            int sum = A[i] + K % 10;
            K /= 10;
            if (sum >= 10) {
                K++;
                sum -= 10;
            }
            res.add(sum);
        }
        for (; K > 0; K /= 10) {
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }
}
