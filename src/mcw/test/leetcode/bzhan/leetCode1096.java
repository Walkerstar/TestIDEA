package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 如果你熟悉 Shell 编程，那么一定了解过花括号展开，它可以用来生成任意字符串。
 * <p>
 * 花括号展开的表达式可以看作一个由 花括号、逗号 和 小写英文字母 组成的字符串，定义下面几条语法规则：
 * <p>
 * 如果只给出单一的元素 x，那么表达式表示的字符串就只有 "x"。R(x) = {x}
 * 例如，表达式 "a" 表示字符串 "a"。
 * 而表达式 "w" 就表示字符串 "w"。
 * 当两个或多个表达式并列，以逗号分隔，我们取这些表达式中元素的并集。R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * 例如，表达式 "{a,b,c}" 表示字符串 "a","b","c"。
 * 而表达式 "{{a,b},{b,c}}" 也可以表示字符串 "a","b","c"。
 * 要是两个或多个表达式相接，中间没有隔开时，我们从这些表达式中各取一个元素依次连接形成字符串。R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}
 * 例如，表达式 "{a,b}{c,d}" 表示字符串 "ac","ad","bc","bd"。
 * 表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
 * 例如，表达式 "a{b,c,d}" 表示字符串 "ab","ac","ad"。
 * 例如，表达式 "a{b,c}{d,e}f{g,h}" 可以表示字符串 "abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"。
 * 给出表示基于给定语法规则的表达式 expression，返回它所表示的所有字符串组成的有序列表。
 * <p>
 * 假如你希望以「集合」的概念了解此题，也可以通过点击 “显示英文描述” 获取详情。
 * <p>
 * 示例 1：
 * <p>
 * 输入：expression = "{a,b}{c,{d,e}}"
 * 输出：["ac","ad","ae","bc","bd","be"]
 * 示例 2：
 * <p>
 * 输入：expression = "{{a,z},a{b,c},{ab,z}}"
 * 输出：["a","ab","ac","z"]
 * 解释：输出中 不应 出现重复的组合结果。
 * <p>
 * 提示：
 * <p>
 * 1 <= expression.length <= 60
 * expression[i] 由 '{'，'}'，',' 或小写英文字母组成
 * 给出的表达式 expression 用以表示一组基于题目描述中语法构造的字符串
 *
 * @author mcw 2023/3/7 11:47
 */
public class leetCode1096 {

    String expression;
    int idx;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.idx = 0;
        Set<String> ret = expr();
        return new ArrayList<>(ret);
    }

    // item . letter | { expr }
    private Set<String> item() {
        Set<String> ret = new TreeSet<>();
        if (expression.charAt(idx) == '{') {
            idx++;
            ret = expr();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(expression.charAt(idx));
            ret.add(sb.toString());
        }
        idx++;
        return ret;
    }

    // term . item | item term
    private Set<String> term() {
        //初始化空集合后，与之后的求解结果求笛卡尔积
        Set<String> ret = new TreeSet<String>() {{
            add("");
        }};
        // item 的开头是 { 或小写字母，只有复合时才继续匹配
        while (idx < expression.length() && (expression.charAt(idx) == '{' || Character.isLetter(expression.charAt(idx)))) {
            Set<String> sub = item();
            Set<String> tmp = new TreeSet<>();
            for (String left : ret) {
                for (String right : sub) {
                    tmp.add(left + right);
                }
            }
            ret = tmp;
        }
        return ret;
    }

    // expr . term | term , expr
    private Set<String> expr() {
        Set<String> ret = new TreeSet<>();
        while (true) {
            //与 term() 求结果求并集
            ret.addAll(term());
            // 如果匹配到逗号则继续，否则结束匹配
            if (idx < expression.length() && expression.charAt(idx) == ',') {
                idx++;
                continue;
            } else {
                break;
            }
        }
        return ret;
    }

    /**
     * 栈
     * 如果把题目中的表达式并列关系看做是求和，把相接看做是求积，那么求解整个表达式的过程可以类比于求解中缀表达式的过程，
     * 例如：{a,b}{c,{d,e}} 可以看做是 {a,b}×{c+{d+e}}。
     * <p>
     * 与求解中缀表达式一样，在遍历表达式的过程中我们需要用到两个栈，一个用来存放运算符（即加号和乘号，以及左大括号），另一个用来存运算对象（即集合）。
     * <p>
     * 我们可以按照如下流程来计算表达式的值：
     * <p>
     * 1.如果遇到 ","，则先判断运算符栈顶是否是乘号，如果是乘号则需要先计算乘法，直到栈顶不是乘号为止，再将加号放入运算符栈中。
     * 2.如果遇到 "{"，则先判断是否需要添加乘号，再将 { 放入运算符栈。
     * 3.如果遇到 "}"，则不断地弹出运算符栈顶，并进行相应的计算，直到栈顶为左括号为止。
     * 4.如果遇到小写字母，则先判断是否需要添加乘号，再构造一个只包含当前小写字母的字符串集合，放入集合栈中。
     * 按照上述流程遍历完一次之后，由于题目给定的表达式中最外层可能没有大括号，例如 {a,b}{c,{d,e}}，
     * 因此运算符栈中可能依然有元素，我们需要依次将他们弹出并进行计算。最终，集合栈栈顶元素即为答案。
     */
    public List<String> braceExpansionII2(String expression) {
        Deque<Character> op = new ArrayDeque<>();
        List<Set<String>> stk = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ',') {
                //不断的弹出栈顶运算符，直到找到为空或者栈顶不为乘号
                while (!op.isEmpty() && op.peek() == '*') {
                    ope(op, stk);
                }
                op.push('+');
            } else if (expression.charAt(i) == '{') {
                //首先判断是否需要添加乘号，再将 {  添加到运算符栈中
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    op.push('*');
                }
                op.push('{');
            } else if (expression.charAt(i) == '}') {
                //不断地弹出栈顶运算符，直到栈顶为 {
                while (!op.isEmpty() && op.peek() != '{') {
                    ope(op, stk);
                }
                op.pop();
            } else {
                //首先判断是否需要添加乘号，再将新构造的集合添加到集合栈中
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    op.push('*');
                }
                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(i));
                stk.add(new TreeSet<String>() {{
                    add(sb.toString());
                }});
            }
        }
        while (!op.isEmpty()) {
            ope(op, stk);
        }
        return new ArrayList<>(stk.get(stk.size() - 1));
    }

    //弹出栈顶运算符，并进行运算
    public void ope(Deque<Character> op, List<Set<String>> stk) {
        int l = stk.size() - 2, r = stk.size() - 1;
        if (op.peek() == '+') {
            stk.get(l).addAll(stk.get(r));
        } else {
            Set<String> tmp = new TreeSet<>();
            for (String left : stk.get(l)) {
                for (String right : stk.get(r)) {
                    tmp.add(left + right);
                }
            }
            stk.set(l, tmp);
        }
        op.pop();
        stk.remove(stk.size() - 1);
    }
}
