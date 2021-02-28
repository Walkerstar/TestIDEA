package mcw.test.leetcode.bzhan;

import java.util.*;


/**
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 *
 * 1.单词 word 中包含谜面 puzzle 的第一个字母。
 * 2.单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 *
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）
 * 以及 "based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 *
 * @author mcw 2021\2\26 0026-20:31
 */
public class leetCode1178 {

    /**
     * 二进制状态压缩
     */
    public List<Integer> findNumOfValidWord(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < words.length; i++) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int total = 0;

            //枚举子集方法一
//            for (int choose = 0; choose < (1 << 6); choose++) {
//                int mask=0;
//                for (int i = 0; i < 6; i++) {
//                    if ((choose &(1<<i))!=0){
//                        mask|=(1<<(puzzle.charAt(i+1)-'a'));
//                    }
//                }
//                mask|=(1<<(puzzle.charAt(0)-'a'));
//                if (frequency.containsKey(mask)) {
//                    total+= frequency.get(mask);
//                }
//            }

            //枚举子集方法二
            int mask = 0;
            for (int i = 1; i < 7; i++) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subSet = mask;
            do {
                int s = subSet | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                subSet = (subSet - 1) & mask;
            } while (subSet != mask);
            ans.add(total);
        }
        return ans;
    }

    /**
     * 字典树
     */
    TrieNode root;

    public List<Integer> findNumOfValidWord1(String[] words, String[] puzzles) {
        root = new TrieNode();

        for (String word : words) {
            //将 word 中的字母按照字典序排序并去重
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                if (i == 0 || arr[i] != arr[i - 1]) {
                    sb.append(arr[i]);
                }
            }
            //加入字典树中
            add(root, sb.toString());
        }
        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            char required = puzzle.charAt(0);
            char[] arr = puzzle.toCharArray();
            Arrays.sort(arr);
            ans.add(find(new String(arr), required, root, 0));
        }
        return ans;
    }

    private void add(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.child[ch - 'a'] == null) {
                cur.child[ch - 'a'] = new TrieNode();
            }
            cur = cur.child[ch - 'a'];
        }
        ++cur.frequency;
    }

    // 在回溯的过程中枚举 puzzle 的所有子集并统计答案
    // find(puzzle, required, cur, pos) 表示 puzzle 的首字母为 required, 当前搜索到字典树上的 cur 节点，并且准备枚举 puzzle 的第 pos 个字母是否选择（放入子集中）
    // find 函数的返回值即为谜底的数量
    private int find(String puzzle, char required, TrieNode cur, int pos) {
        //搜索到空节点，不合法，返回 0
        if (cur == null) {
            return 0;
        }
        //整个 puzzle 搜索完毕，返回谜底的数量
        if (pos == 7) {
            return cur.frequency;
        }
        //选择第 pos 个字母
        int ret = find(puzzle, required, cur.child[puzzle.charAt(pos) - 'a'], pos + 1);
        //当 puzzle.charAt(pos) 不为首字母时，可以不选择第 pos 个字母
        if (puzzle.charAt(pos) != required) {
            ret += find(puzzle, required, cur, pos + 1);
        }
        return ret;
    }


    static class TrieNode {
        int frequency;
        TrieNode[] child;

        public TrieNode() {
            this.frequency = 0;
            this.child = new TrieNode[26];
        }
    }
}
