package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 * @author MCW 2022/2/13
 */
public class leetCode1189 {

    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[5];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'b') {
                cnt[0]++;
            } else if (ch == 'a') {
                cnt[1]++;
            } else if (ch == 'l') {
                cnt[2]++;
            } else if (ch == 'o') {
                cnt[3]++;
            } else if (ch == 'n') {
                cnt[4]++;
            }
        }
        //其中每个字母 "balloon" 需要两个 ‘l’,‘o’，可以将字母 l’,‘o’ 的数量除以 2，
        // 返回字母 ‘a’,‘b’,‘l’,‘o’,‘n’ 中数量最小值即为可以构成的单词数量。
        cnt[2] /= 2;
        cnt[3] /= 2;
        return Arrays.stream(cnt).min().getAsInt();
    }
}
