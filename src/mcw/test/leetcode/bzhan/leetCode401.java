package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 *
 * @author mcw 2021/6/21 10:06
 */
public class leetCode401 {

    /**
     * 枚举时分
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if ((Integer.bitCount(h) + Integer.bitCount(m)) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：二进制枚举
     * 另一种枚举方法是枚举所有 2^{10}=1024 种灯的开闭组合，即用一个二进制数表示灯的开闭，其高 4 位为小时，低 6 位为分钟。
     * 若小时和分钟的值均在合法范围内，且二进制中 1 的个数为 turnedOn，则将其加入到答案中。
     */
    public List<String> readBinaryWatch1(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1024; i++) {
            // 用位运算取出高 4 位和低 6 位
            int h = i >> 6, m = i & 63;
            if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
                ans.add(h + ":" + (m < 10 ? "0" : "") + m);
            }
        }
        return ans;
    }
}
