package mcw.test.leetcode.bzhan;

/**
 * 给你一个长度为 5 的字符串 time ，表示一个电子时钟当前的时间，格式为 "hh:mm" 。最早 可能的时间是 "00:00" ，最晚 可能的时间是 "23:59" 。
 * <p>
 * 在字符串 time 中，被字符 ? 替换掉的数位是 未知的 ，被替换的数字可能是 0 到 9 中的任何一个。
 * <p>
 * 请你返回一个整数 answer ，将每一个 ? 都用 0 到 9 中一个数字替换后，可以得到的有效时间的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：time = "?5:00"
 * 输出：2
 * 解释：我们可以将 ? 替换成 0 或 1 ，得到 "05:00" 或者 "15:00" 。注意我们不能替换成 2 ，因为时间 "25:00" 是无效时间。所以我们有两个选择。
 * 示例 2：
 * <p>
 * 输入：time = "0?:0?"
 * 输出：100
 * 解释：两个 ? 都可以被 0 到 9 之间的任意数字替换，所以我们总共有 100 种选择。
 * 示例 3：
 * <p>
 * 输入：time = "??:??"
 * 输出：1440
 * 解释：小时总共有 24 种选择，分钟总共有 60 种选择。所以总共有 24 * 60 = 1440 种选择。
 * <p>
 * <p>
 * 提示：
 * <p>
 * time 是一个长度为 5 的有效字符串，格式为 "hh:mm" 。
 * "00" <= hh <= "23"
 * "00" <= mm <= "59"
 * 字符串中有的数位是 '?' ，需要用 0 到 9 之间的数字替换。
 *
 * @author MCW 2023/5/9
 */
public class leetCode2437 {

    int res = 0;

    /**
     * 方法一：回溯
     * 思路与算法
     * <p>
     * 由于字符串  time 中的  ‘?’ 可以被  ‘0’ 到  ‘9’ 中的任意字符替换，则依次尝试将字符串中的每个  ‘?’ 替换为  ‘0’ 到  ‘9’ 后，
     * 并检测该时间的合法性即可，此时合法的时间需要满足如下:
     * “00"≤hh≤“23"；
     * “00"≤mm≤“59"；
     * 统计合法的时间数目并返回即可。
     */
    public int countTime(String time) {

        char[] arr = time.toCharArray();
        dfs(arr, 0);
        return res;
    }

    public void dfs(char[] arr, int pos) {
        if (pos == arr.length) {
            if (check(arr)) {
                res++;
            }
            return;
        }

        if (arr[pos] == '?') {
            for (int i = 0; i < 9; i++) {
                arr[pos] = (char) ('0' + i);
                dfs(arr, pos + 1);
                arr[pos] = '?';
            }
        } else {
            dfs(arr, pos + 1);
        }
    }

    public boolean check(char[] arr) {
        int hh = (arr[0] - '0') * 10 + arr[1] - '0';
        int mm = (arr[3] - '0') * 10 + arr[4] - '0';
        return hh < 24 && mm < 60;
    }


    /***
     * 方法二：分开枚举
     * 思路与算法
     *
     * 由于题目中小时和分钟的限制不同，因此没有必要枚举所有可能的数字，由于小时和分钟限制如下：
     *
     * “00"≤hh≤“23"；
     * “00"≤mm≤“59"；
     * 我们检测所有符合当前字符串  time 匹配的小时  hh 的数目为  countHour，
     * 同时检测检测所有符合当前字符串  time 匹配的分钟  hh 的数目为  countMinute，
     * 则合法有效的时间数目为  countHour × countMinute。
     */
    public int countTime2(String time) {
        int countHour = 0;
        int countMinute = 0;
        for (int i = 0; i < 24; i++) {
            int hiHour = i / 10;
            int loHour = i % 10;
            if ((time.charAt(0) == '?' || time.charAt(0) == hiHour + '0') &&
                    (time.charAt(1) == '?' || time.charAt(1) == loHour + '0')) {
                countHour++;
            }
        }
        for (int j = 0; j < 60; j++) {
            int hiMinute = j / 10;
            int loMinute = j % 10;
            if ((time.charAt(3) == '?' || time.charAt(3) == hiMinute + '0') &&
                    (time.charAt(4) == '?' || time.charAt(4) == loMinute + '0'))
                countMinute++;
        }
        return countHour * countMinute;
    }


}
