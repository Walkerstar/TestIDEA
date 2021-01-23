package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（
 * 从最高有效位到最低有效位）。返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 *
 * @author mcw 2021/1/14 17:21
 */
public class leetCode1018 {
    public List<Boolean> prefixesDivBy5(int[] A){
        List<Boolean> list=new ArrayList<>();
        int prefix=0;
        for (int j : A) {
            prefix = ((prefix << 1) + j) % 5;
            list.add(prefix == 0);
        }
        return list;
    }
}
