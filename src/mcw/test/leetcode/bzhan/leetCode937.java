package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
 * <p>
 * 有两种不同类型的日志：
 * <li>字母日志：除标识符之外，所有字均由小写字母组成</li>
 * <li>数字日志：除标识符之外，所有字均由数字组成</li>
 * <br></br>
 * 请按下述规则将日志重新排序：
 * <li>所有 字母日志 都排在 数字日志 之前。</li>
 * <li>字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。</li>
 * <li>数字日志 应该保留原来的相对顺序。</li>
 * 返回日志的最终顺序。
 *
 * @author MCW 2022/5/3
 */
public class leetCode937 {
    public String[] reorderLogFiles(String[] logs) {
        int length = logs.length;
        Pair[] arr = new Pair[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Pair(logs[i], i);
        }
        Arrays.sort(arr, (a, b) -> logCompare(a, b));
        String[] reordered = new String[length];
        for (int i = 0; i < length; i++) {
            reordered[i] = arr[i].log;
        }
        return reordered;
    }

    /**
     * @return 当相等时，返回 0；当 log1 大时，返回正数；当 log2 大时，返回负数。
     */
    public int logCompare(Pair pair1, Pair pair2) {
        String log1 = pair1.log;
        String log2 = pair2.log;
        int index1 = pair1.index;
        int index2 = pair2.index;
        //分割标识符和日志内容
        String[] split1 = log1.split(" ", 2);
        String[] split2 = log2.split(" ", 2);
        //判断日志类型
        boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
        boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
        //如果都是数字日志，按日志的索引位置排序
        if (isDigit1 && isDigit2) {
            return index1 - index2;
        }
        //如果都是字母日志，
        if (!isDigit1 && !isDigit2) {
            //判断内容是否一致
            int sc = split1[1].compareTo(split2[1]);
            if (sc != 0) {
                return sc;
            }
            //不一致，则判断标识符
            return split1[0].compareTo(split2[0]);
        }
        return isDigit1 ? 1 : -1;
    }

    class Pair {
        String log;
        int index;

        public Pair(String log, int index) {
            this.log = log;
            this.index = index;
        }
    }


    public String[] reorderLogFile2(String[] logs) {
        Arrays.sort(logs, this::compare);
        return logs;
    }

    public int compare(String log1, String log2) {
        int s1 = log1.indexOf(" ") + 1;
        int s2 = log2.indexOf(" ") + 1;
        if (Character.isDigit(log1.charAt(s1)) && Character.isDigit(log2.charAt(s2))) {
            return 0;
        } else if (Character.isDigit(log1.charAt(s1))) {
            return 1;
        } else if (Character.isDigit(log2.charAt(s2))) {
            return -1;
        } else {
            int cmp = log1.substring(s1).compareTo(log2.substring(s2));
            if (cmp == 0) {
                return log1.compareTo(log2);
            } else {
                return cmp;
            }
        }
    }
}
