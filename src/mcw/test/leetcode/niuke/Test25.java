package mcw.test.leetcode.niuke;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author mcw 2020\3\12 0012-20:47
 * <p>
 * 给定两个单词和一个单词词典，找出所有的从初始单词到目标单词的最短转换序列的长度
 * 例：hit --> cog    [hot,dot,dog,lot,log]
 * 最短转换序列为: hit -> hot -> dot -> dog -> cog ,返回长度为 5
 */
public class Test25 {
    public int ladderLength(String start, String end, HashSet<String> dict) {
        HashSet<String> begin = new HashSet<>();
        HashSet<String> visit = new HashSet<>();
        HashSet<String> endset = new HashSet<>();
        begin.add(start);
        visit.add(start);
        endset.add(end);
        int res = 1;

        while (!begin.isEmpty() && !endset.isEmpty()) {
            if (begin.size() > endset.size()) {
                HashSet<String> temp = begin;
                begin = endset;
                endset = temp;
            }
            HashSet<String> temp = new HashSet<>();
            for (String str : begin) {
                char[] ch = str.toCharArray();
                for (int i = 0; i < ch.length; i++) {
                    char old = ch[i];
                    for (char c = 'a'; c < 'z'; c++) {
                        ch[i] = c;
                        String cur = String.valueOf(ch);
                        if (endset.contains(cur))
                            return res + 1;
                        if (!visit.contains(cur) && dict.contains(cur)) {
                            visit.add(cur);
                            temp.add(cur);
                        }
                        ch[i] = old;
                    }
                }
            }
            begin = temp;
            res++;
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] strings={"hot","dot","dog","lot","log"};
        HashSet<String> set = new HashSet<>(Arrays.asList(strings));

        int i = new Test25().ladderLength("hit", "cog", set);
        System.out.println(i);

    }
}
