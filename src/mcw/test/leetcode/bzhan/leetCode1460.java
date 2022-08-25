package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你两个长度相同的整数数组 target 和 arr 。每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
 * <p>
 * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
 *
 * @author MCW 2022/8/24
 */
public class leetCode1460 {

    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }

    public boolean canBeEqual2(int[] target, int[] arr) {
        Map<Integer, Integer> c1 = new HashMap<>();
        Map<Integer, Integer> c2 = new HashMap<>();

        for (int num : target) {
            c1.put(num, c1.getOrDefault(num, 0) + 1);
        }
        for (int num : arr) {
            c2.put(num, c2.getOrDefault(num, 0) + 1);
        }

        if (c1.size() != c2.size()) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry : c1.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (!c2.containsKey(key) || c2.get(key) != value) {
                return false;
            }
        }
        return true;
    }

    public boolean canBeEqual3(int[] target, int[] arr) {
        int n = target.length;
        int tot = 0;
        int[] cnt = new int[1010];
        for (int i = 0; i < n; i++) {
            if (++cnt[target[i]] == 1) {
                tot++;
            }
            if (--cnt[arr[i]] == 0) {
                tot--;
            }
        }
        return tot == 0;
    }
}
