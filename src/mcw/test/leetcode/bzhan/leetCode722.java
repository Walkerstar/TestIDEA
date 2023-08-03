package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 为避免 注释中 / 混淆， 以下用 \ 代替 /
 * <p>
 * 给一个 C++ 程序，删除程序中的注释。这个程序source是一个数组，其中source[i]表示第 i 行源码。 这表示每行源码由 '\n' 分隔。
 * <p>
 * 在 C++ 中有两种注释风格，行内注释和块注释。
 * <p>
 * 1. 字符串 "\\" 表示行注释，表示 "\\"和其右侧的其余字符应该被忽略。
 * 2. 字符串 "\*" 表示一个块注释，它表示直到下一个（非重叠）出现的 "*\" 之间的所有字符都应该被忽略。
 * （阅读顺序为从左到右）非重叠是指，字符串 \*\  并没有结束块注释，因为注释的结尾与开头相重叠。
 * <p>
 * 第一个有效注释优先于其他注释。
 * <p>
 * 1. 如果字符串 \\ 出现在块注释中会被忽略。
 * 2. 同样，如果字符串 \*出现在行或块注释中也会被忽略。
 * 如果一行在删除注释之后变为空字符串，那么 不要 输出该行。即，答案列表中的每个字符串都是非空的。
 * <p>
 * 样例中没有控制字符，单引号或双引号字符。
 * <p>
 * 比如，source = "string s = "\* Not a comment. *\";"不会出现在测试样例里。
 * 此外，没有其他内容（如定义或宏）会干扰注释。
 * <p>
 * 我们保证每一个块注释最终都会被闭合， 所以在行或块注释之外的 \* 总是开始新的注释。
 * <p>
 * 最后，隐式换行符可以通过块注释删除。 有关详细信息，请参阅下面的示例。
 * <p>
 * 从源代码中删除注释后，需要以相同的格式返回源代码。
 * <p>
 * 示例 1:
 * 输入: source = [ "\*Test program *\",
 * "int main()", "{",
 * "   \\ variable declaration ",
 * "int a, b, c;",
 * "\* This is a test", "   multiline  ", "   comment for ", "   testing *\",
 * "a = b + c;",
 * "}"
 * ]
 * 输出: ["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
 * 解释:示例代码可以编排成这样:
 * \*Test program *\
 * int main()
 * {
 * \\ variable declaration
 * int a,b,c;
 * \* This is a test
 * multiline
 * comment for
 * testing *\
 * a=b+c;
 * }
 * 第 1行和第 6-9行的字符串 \* 表示块注释。第 4 行的字符串  \\ 表示行注释。
 * 编排后:
 * int main()
 * {
 * int a, b, c;
 * a = b + c;
 * }
 * <p>
 * 示例 2:
 * 输入: source = ["a\*comment", "line", "more_comment*\b"]
 * 输出:["ab"]
 * 解释:原始的 source 字符串是"a \*comment\n line\n more_comment*\ b",其中我们用粗体显示了换行符。
 * 删除注释后，隐含的换行符被删除，留下字符串"ab"用换行符分隔成数组时就是["ab"].
 * <p>
 * 提示:
 * <p>
 * 1<=source.length<=100
 * 0<=source[i].length<=80
 * source[i] 由可打印的 ASCII 字符组成。
 * 每个块注释都会被闭合。
 * 给定的源码中不会有单引号、双引号或其他控制字符。
 *
 * @author MCW 2023/8/3
 */
public class leetCode722 {

    /**
     * 方法一：模拟
     * 思路与算法
     * <p>
     * 我们需要逐行分析源代码。每个字符有两种情况，要么在一个注释内要么不在。
     * 因此我们用 in_block 变量来标记状态，该变量为 true 表示在注释内，反之则不在。
     * <p>
     * 假设此刻不在注释块内：
     * <p>
     * 遇到 ‘\*’，则将状态改为在注释块内，继续遍历后面第三个字符。
     * 遇到 ‘\\’，则直接忽略该行后面的部分。
     * 遇到其他字符，将该字符记录到 new_line 中。
     * <p>
     * 假设此刻在注释块内，遇到 ‘*\’ 则将状态改为不在注释块内，继续遍历后面第三个字符。
     * <p>
     * 我们用 new_line 记录新的一行，当遍历到每行的末尾时，如果不在注释块内并且 new_line 不为空，就把它放入答案中。
     */
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder newLine = new StringBuilder();
        boolean inBlock = false;
        for (String line : source) {
            // 读取每一行代码，从头开始遍历
            for (int i = 0; i < line.length(); i++) {
                if (inBlock) {
                    // 如果在注释内 ，且碰到了 */ ，则说明注释块 结束了，将 inBlock 改为 false
                    if (i + 1 < line.length() && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                        inBlock = false;
                        i++;
                    }
                } else {
                    // 如果 以 /* 开头，则表明在注释块内，inBlock 改为 true
                    if (i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                        inBlock = true;
                        i++;
                    } else if (i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                        // 如果是行注释，则直接丢弃该行
                        break;
                    } else {
                        // 如果是代码，加入到新行中
                        newLine.append(line.charAt(i));
                    }
                }
            }
            // 每一行结束后，将可用代码加入到 集合中
            if (!inBlock && newLine.length() > 0) {
                res.add(newLine.toString());
                newLine.setLength(0);
            }
        }
        return res;
    }

    /**
     * 正则表达式
     */
    public List<String> removeComments2(String[] source) {
        return Arrays.stream(
                        String.join("\n", source)
                                .replaceAll("//.*|/\\*(.|\n)*?\\*/", "")
                                .split("\n"))
                .filter(e -> e.length() > 0)
                .collect(Collectors.toList());
    }
}
