package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
 * <p>
 * 如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的 子文件夹 。
 * <p>
 * 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/' 后跟一个或者多个小写英文字母。
 * <p>
 * 例如，"/leetcode" 和 "/leetcode/problems" 都是有效的路径，而空字符串和 "/" 不是。
 * <p>
 * 提示：
 * <li>1 <= folder.length <= 4 * 10^4</li>
 * <li>2 <= folder[i].length <= 100</li>
 * <li>folder[i] 只包含小写字母和 '/'</li>
 * <li>folder[i] 总是以字符 '/' 起始</li>
 * <li>每个文件夹名都是 唯一 的</li>
 *
 * @author mcw 2023/2/8 14:19
 */
public class leetCode1233 {

    /**
     * 排序
     */
    public List<String> removeSubfolders(String[] folder) {
        //字典序排序
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        for (int i = 1; i < folder.length; i++) {
            int pre = ans.get(ans.size() - 1).length();
            //后一个的长度比前一个大，且 后一个以前一个开头，且在后一个中前一个长度的位置字符是'/'，则说明是后一一个是前一个的子文件夹
            if (!(pre < folder[i].length() &&
                    ans.get(ans.size() - 1).equals(folder[i].substring(0, pre)) &&
                    folder[i].charAt(pre) == '/')) {
                ans.add(folder[i]);
            }
        }
        return ans;
    }

    /**
     * 字典树
     */
    public List<String> removeSubfolders2(String[] folder) {
        Trie root = new Trie();
        for (int i = 0; i < folder.length; i++) {
            //以 字符 '/' 分割
            List<String> path = split(folder[i]);
            Trie cur = root;
            for (String s : path) {
                cur.children.putIfAbsent(s, new Trie());
                cur = cur.children.get(s);
            }
            cur.ref = i;
        }
        List<String> ans = new ArrayList<>();
        dfs(folder, ans, root);
        return ans;
    }

    public List<String> split(String s) {
        List<String> ret = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '/') {
                ret.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(ch);
            }
        }
        ret.add(cur.toString());
        return ret;
    }

    public void dfs(String[] folder, List<String> ans, Trie cur) {
        if (cur.ref != -1) {
            ans.add(folder[cur.ref]);
            return;
        }
        for (Trie value : cur.children.values()) {
            dfs(folder, ans, value);
        }
    }

    class Trie {
        int ref;
        Map<String, Trie> children;

        public Trie() {
            ref = -1;
            children = new HashMap<>();
        }
    }


}
