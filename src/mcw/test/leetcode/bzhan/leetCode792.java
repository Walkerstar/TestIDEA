package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 * <p>
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * <p>
 * 例如， “ace” 是 “abcde” 的子序列。
 * <p>
 * 提示:
 * <p>
 * <li>1 <= s.length <= 5 * 104</li>
 * <li>1 <= words.length <= 5000</li>
 * <li>1 <= words[i].length <= 50</li>
 * <li> words[i]和 s 都只由小写字母组成。</li>
 *
 * @author mcw 2022/11/17 15:47
 */
public class leetCode792 {

    /**
     * 如果我们将字符串 s 中的全部的字符的位置按照对应的字符进行存储，令其为数组pos，
     * 其中 pos[c] 存储的是字符串 s 中字符为 c 的从小到大排列的位置。
     * 那么对于需要匹配的字符 t[j] 我们就可以通过在对应的 pos 数组中进行「二分查找」来找到第一个大于当前 i 指针的位置，
     * 若不存在则说明匹配不成功，否则就将指针 i 直接移到找到的对应位置，并将指针 j 后移一个单位，这样就加速了指针 i 的移动。
     */
    public static int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }
        //按顺序记录 S 中每个字符下标
        for (int i = 0; i < s.length(); i++) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        int res = words.length;
        for (String word : words) {
            //如果单词长度大于 s 的长串，则 该单词不是 s 的子串
            if (word.length() > s.length()) {
                --res;
                continue;
            }
            int p = -1;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                //如果单词中的字符不在 S 中，或者 该字符在 pos 中的最大下标 小于等于 p ,则说明 单词不是 s 的子序列
                if (pos[c - 'a'].isEmpty() || pos[c - 'a'].get(pos[c - 'a'].size() - 1) <= p) {
                    --res;
                    break;
                }
                //找出 c 在 s 中的下标
                p = binarySearch(pos[c - 'a'], p);
            }
        }
        return res;
    }

    public static int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return list.get(left);
    }


    /**
     * 方法二：多指针
     * <p>
     * 同样我们还可以在朴素方法的基础上进行优化，
     * 因为朴素方法中是每一个单词分别和字符串 s 进行匹配，这样对于每一次匹配都需要从头开始遍历字符串 s，这增加了额外的时间开销。
     * 所以我们考虑将字符串数组 words 中的全部字符串和字符串 s 同时进行匹配——同样对于每一个需要匹配的字符串
     * 我们用一个指针来指向它需要匹配的字符，那么在遍历字符串 s 的过程中，对于当前遍历到的字符如果有可以匹配的字符串，
     * 那么将对应的字符串指针往后移动一单位即可。那么当字符串 s 遍历结束时，字符串数组中全部字符串的匹配情况也就全部知道了。
     */
    public static int numMatchingSubseq2(String s, String[] words) {
        Queue<int[]>[] p = new Queue[26];
        for (int i = 0; i < 26; i++) {
            p[i] = new ArrayDeque<int[]>();
        }
        for (int i = 0; i < words.length; i++) {
            p[words[i].charAt(0) - 'a'].offer(new int[]{i, 0});
        }

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //挑选出 以 c 开头的 word 的个数
            int len = p[c - 'a'].size();
            while (len > 0) {
                //t[0] : word 在 words 数组的下标
                int[] t = p[c - 'a'].poll();
                //如果 t[1] 等于 word 的长度，则表示 该 word 是子串
                if (t[1] == words[t[0]].length() - 1) {
                    ++res;
                } else {
                    // +1 : 该单词有一位字符是 S 的子串
                    ++t[1];
                    //将该单词的余下字符串存入队列中
                    p[words[t[0]].charAt(t[1]) - 'a'].offer(t);
                }
                --len;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println(numMatchingSubseq2(s, words));
    }
}
