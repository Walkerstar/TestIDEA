package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..）表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 *
 * 请注意，返回的 规范路径 必须遵循下述格式：
 *      .始终以斜杠 '/' 开头。
 *      .两个目录名之间必须只有一个斜杠 '/' 。
 *      .最后一个目录名（如果存在）不能 以 '/' 结尾。
 *      .此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 *
 * @author mcw 2022/1/6 10:14
 */
public class leetCode71 {

    public static String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> deque = new ArrayDeque<>();
        //遍历路径，如果当前路径名是 '..' ,则在队列不空的情况下，删除最后一个元素（即返回上一层目录）
        for (String name : names) {
            if ("..".equals(name)) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                //如果当前路径名有效，则存放到队列中 ; '.' 代表当前路径，忽略
                deque.offerLast(name);
            }
        }

        StringBuffer ans = new StringBuffer();
        if (deque.isEmpty()) {
            ans.append("/");
        } else {
            while (!deque.isEmpty()) {
                ans.append("/");
                ans.append(deque.pollFirst());
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }
}
