package mcw.test.leetcode.bzhan;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 设计一个算法：接收一个字符流，并检查这些字符的后缀是否是字符串数组 words 中的一个字符串。
 * <p>
 * 例如，words = ["abc", "xyz"] 且字符流中逐个依次加入 4 个字符 'a'、'x'、'y' 和 'z' ，
 * 你所设计的算法应当可以检测到 "axyz" 的后缀 "xyz" 与 words 中的字符串 "xyz" 匹配。
 * <p>
 * 按下述要求实现 StreamChecker 类：
 * <p>
 * StreamChecker(String[] words) ：构造函数，用字符串数组 words 初始化数据结构。
 * boolean query(char letter)：从字符流中接收一个新字符，如果字符流中的任一非空后缀能匹配 words 中的某一字符串，返回 true ；否则，返回 false。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * 输出：
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 * <p>
 * 解释：
 * StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 * streamChecker.query("a"); // 返回 False
 * streamChecker.query("b"); // 返回 False
 * streamChecker.query("c"); // 返回n False
 * streamChecker.query("d"); // 返回 True ，因为 'cd' 在 words 中
 * streamChecker.query("e"); // 返回 False
 * streamChecker.query("f"); // 返回 True ，因为 'f' 在 words 中
 * streamChecker.query("g"); // 返回 False
 * streamChecker.query("h"); // 返回 False
 * streamChecker.query("i"); // 返回 False
 * streamChecker.query("j"); // 返回 False
 * streamChecker.query("k"); // 返回 False
 * streamChecker.query("l"); // 返回 True ，因为 'kl' 在 words 中
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 200
 * words[i] 由小写英文字母组成
 * letter 是一个小写英文字母
 * 最多调用查询 4 * 10^4 次
 *
 * @author mcw 2023/3/24 11:36
 */
public class leetCode1032 {

    class StreamChecker {
        TrieNode root;
        TrieNode temp;

        /**
         * 那么如何更新所有节点的失配指针呢？
         * 首先，特殊地，我们将根节点和所有根节点的非空子节点的失配指针指向根节点，然后思考如何计算其他非空节点的失配指针。
         * <p>
         * 当我们要计算某个节点 u 的失配指针时，假设所有比 u 浅的节点的失配指针已经计算完毕，
         * 设 u 的父节点为 p，节点 p 依靠字符 c 指向节点 u（即节点 u 代表的前缀 pre_u 比节点 p 代表的前缀 pre_p  多一个字符 c）。
         * 我们需要考察 p.fail 依靠字符 c 指向的节点是否非空，如果非空，我们就找到 u 的失配节点，如果为空，我们需要继续考察 p.fail.fail，
         * 直到在这条失配链上找到一个节点，它依靠字符 c 指向的子节点非空；
         * 或者我们在失配链上不断考察的过程中，最终考察到了根节点，此时我们将 u 的失配节点指向根节点。
         * 这样一来，我们可以根据广度优先搜索的过程，计算出所有非空节点的失配节点。
         * <p>
         * 如此，我们就可以用这棵字典树来匹配后缀数据流了。初始化时，设置一个指针 temp 指向根节点，每接收一个字符 c，
         * temp 先试图依靠字符 c 移动到子节点，如果子节点为空，则在失配链上不停移动，直到找到一个节点，它依靠字符 c 指向的子节点非空，
         * 这时该子节点表示的前缀，是可以匹配到的数据流里的最长后缀。
         * 注意此时，并不能立刻返回结果，需要遍历该子节点的失配链上的所有节点，如果有节点的 isEnd 值为 true，则返回 true，否则返回 false。
         * <p>
         * 注意到此时已经可以解决这道题了，但是计算每个节点的失配节点和单次 query 的时间复杂度仍然较大，最差情况下，
         * 需要遍历整条失配链路才能得到结果，因此，我们在广度优先搜索时引入以下两个优化：
         * <p>
         * 1.当节点 p 的依靠字符 c 指向的子节点 u 为空时，将其指向 p.fail 依靠字符 c 指向的子节点 v，这种类似于路径压缩的思路，
         * 使得每个空子节点最终都会指向一个非空节点，从而省去了在失配链路上不停考察的过程。
         * 2.广度优先搜索过程中，节点 u 出队列时，将 u.isEnd 更新为 u.isEnd ∥ u.fail.isEnd，这样每个节点的 isEnd 参数的定义就变为：
         * 当自己或者自己的某一后缀能匹配字符串数组的某个字符串时为 true。这样一来，query 时也不需要遍历失配链路了。
         */
        public StreamChecker(String[] words) {
            root = new TrieNode();
            for (String word : words) {
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    int index = word.charAt(i) - 'a';
                    if (cur.getChild(index) == null) {
                        cur.setChild(index, new TrieNode());
                    }
                    cur = cur.getChild(index);
                }
                cur.setIsEnd(true);
            }
            root.setFail(root);

            Queue<TrieNode> q = new LinkedList<>();
            for (int i = 0; i < 26; i++) {
                if (root.getChild(i) != null) {
                    root.getChild(i).setFail(root);
                    q.add(root.getChild(i));
                } else {
                    root.setChild(i, root);
                }
            }
            while (!q.isEmpty()) {
                TrieNode node = q.poll();
                node.setIsEnd(node.getIsEnd() || node.getFail().getIsEnd());
                for (int i = 0; i < 26; i++) {
                    if (node.getChild(i) != null) {
                        node.getChild(i).setFail(node.getFail().getChild(i));
                        q.offer(node.getChild(i));
                    } else {
                        node.setChild(i, node.getFail().getChild(i));
                    }
                }
            }
            temp = root;
        }

        public boolean query(char letter) {
            temp = temp.getChild(letter - 'a');
            return temp.getIsEnd();
        }
    }


    static class TrieNode {
        TrieNode[] children;
        //表示当前节点是否为一个单词的结尾
        boolean isEnd;
        //失配指针
        // 失配指针的定义是，可以表示当前节点的最长后缀的节点，具体解释如下：
        // 当前节点能表示字符串数组 words 的某个前缀 pre_1 ，我们需要找到字典树中的另一个节点，
        // 且该节点表示的前缀 pre_2，是 pre_1 的一个后缀，并且是能在字典树中找到的最长的后缀
        TrieNode fail;

        public TrieNode() {
            children = new TrieNode[26];
        }

        public TrieNode getChild(int index) {
            return children[index];
        }

        public void setChild(int index, TrieNode node) {
            children[index] = node;
        }

        public boolean getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(boolean b) {
            isEnd = b;
        }

        public TrieNode getFail() {
            return fail;
        }

        public void setFail(TrieNode node) {
            fail = node;
        }

    }
}
