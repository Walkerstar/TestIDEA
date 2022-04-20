package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 假设有一个同时存储文件和目录的文件系统。下图展示了文件系统的一个示例：
 * <p>
 * 这里将 dir 作为根目录中的唯一目录。dir 包含两个子目录 subdir1 和 subdir2 。subdir1 包含文件 file1.ext 和子目录 subsubdir1；
 * subdir2 包含子目录 subsubdir2，该子目录下包含文件 file2.ext 。
 * <p>
 * 在文本格式中，如下所示(⟶表示制表符)：
 * dir
 * ⟶ subdir1
 * ⟶ ⟶ file1.ext
 * ⟶ ⟶ subsubdir1
 * ⟶ subdir2
 * ⟶ ⟶ subsubdir2
 * ⟶ ⟶ ⟶ file2.ext
 * 如果是代码表示，上面的文件系统可以写为 "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" 。
 * '\n' 和 '\t' 分别是换行符和制表符。
 * <p>
 * 文件系统中的每个文件和文件夹都有一个唯一的 绝对路径 ，即必须打开才能到达文件/目录所在位置的目录顺序，所有路径用 '/' 连接。
 * 上面例子中，指向 file2.ext 的 绝对路径 是 "dir/subdir2/subsubdir2/file2.ext" 。每个目录名由字母、数字和/或空格组成，
 * 每个文件名遵循 name.extension 的格式，其中 name 和 extension由字母、数字和/或空格组成。
 * <p>
 * 给定一个以上述格式表示文件系统的字符串 input ，返回文件系统中 指向 文件 的 最长绝对路径 的长度 。 如果系统中没有文件，返回 0。
 *
 * @author mcw 2022/4/20 17:51
 */
public class leetCode388 {

    /**
     * 栈
     */
    public int lengthLongestPath(String input) {
        int n = input.length();
        int pos = 0;
        int ans = 0;

        //保存当前已遍历路径的长度
        Deque<Integer> stack = new ArrayDeque<>();

        while (pos < n) {
            //检测当前文件的深度
            int depth = 1;
            while (pos < n && input.charAt(pos) == '\t') {
                pos++;
                depth++;
            }
            //统计当前文件名的长度
            boolean isFile = false;
            int len = 0;
            while (pos < n && input.charAt(pos) != '\n') {
                if (input.charAt(pos) == '.') {
                    isFile = true;
                }
                len++;
                pos++;
            }
            //跳过当前的换行符
            pos++;

            while (stack.size() >= depth) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                len += stack.peek() + 1;
            }
            if (isFile) {
                ans = Math.max(ans, len);
            } else {
                stack.push(len);
            }
        }
        return ans;
    }


    /**
     * 遍历
     */
    public int lengthLongestPath2(String input) {
        int n = input.length();
        //下标
        int pos = 0;
        int ans = 0;

        // level[i] 保存当前已遍历路径中第 i 层目录的长度
        int[] level = new int[n + 1];

        while (pos < n) {
            //检测当前文件的深度
            int depth = 1;
            while (pos < n && input.charAt(pos) == '\t') {
                pos++;
                depth++;
            }
            //统计文件名的长度
            int len = 0;
            boolean isFile = false;
            while (pos < n && input.charAt(pos) != '\n') {
                if (input.charAt(pos) == '.') {
                    isFile = true;
                }
                len++;
                pos++;
            }
            //跳过换行符
            pos++;
            if (depth > 1) {
                len += level[depth - 1] + 1;
            }
            if (isFile) {
                ans = Math.max(ans, len);
            } else {
                level[depth] = len;
            }
        }
        return ans;
    }
}
