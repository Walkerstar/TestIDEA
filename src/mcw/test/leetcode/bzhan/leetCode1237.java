package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个函数  f(x, y) 和一个目标结果 z，函数公式未知，请你计算方程 f(x,y) == z 所有可能的正整数 数对 x 和 y。满足条件的结果数对可以按任意顺序返回。
 * <p>
 * 尽管函数的具体式子未知，但它是单调递增函数，也就是说：
 * <p>
 * f(x, y) < f(x + 1, y)
 * f(x, y) < f(x, y + 1)
 * 函数接口定义如下：
 * <p>
 * interface CustomFunction {
 * public:
 * // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
 * int f(int x, int y);
 * };
 * 你的解决方案将按如下规则进行评判：
 * <p>
 * 判题程序有一个由 CustomFunction 的 9 种实现组成的列表，以及一种为特定的 z 生成所有有效数对的答案的方法。
 * 判题程序接受两个输入：function_id（决定使用哪种实现测试你的代码）以及目标结果 z 。
 * 判题程序将会调用你实现的 findSolution 并将你的结果与答案进行比较。
 * 如果你的结果与答案相符，那么解决方案将被视作正确答案，即 Accepted 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：function_id = 1, z = 5
 * 输出：[[1,4],[2,3],[3,2],[4,1]]
 * 解释：function_id = 1 暗含的函数式子为 f(x, y) = x + y
 * 以下 x 和 y 满足 f(x, y) 等于 5：
 * x=1, y=4 -> f(1, 4) = 1 + 4 = 5
 * x=2, y=3 -> f(2, 3) = 2 + 3 = 5
 * x=3, y=2 -> f(3, 2) = 3 + 2 = 5
 * x=4, y=1 -> f(4, 1) = 4 + 1 = 5
 * 示例 2：
 * <p>
 * 输入：function_id = 2, z = 5
 * 输出：[[1,5],[5,1]]
 * 解释：function_id = 2 暗含的函数式子为 f(x, y) = x * y
 * 以下 x 和 y 满足 f(x, y) 等于 5：
 * x=1, y=5 -> f(1, 5) = 1 * 5 = 5
 * x=5, y=1 -> f(5, 1) = 5 * 1 = 5
 * <p>
 * 提示：
 * <p>
 * 1 <= function_id <= 9
 * 1 <= z <= 100
 * 题目保证 f(x, y) == z 的解处于 1 <= x, y <= 1000 的范围内。
 * 在 1 <= x, y <= 1000 的前提下，题目保证 f(x, y) 是一个 32 位有符号整数。
 *
 * @author mcw 2023/2/18 11:57
 */
public class leetCode1237 {

    /**
     * 枚举
     */
    public List<List<Integer>> findSolution1(CustomFunction customFunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int x = 1; x <= 1000; x++) {
            for (int y = 1; y <= 1000; y++) {
                if (customFunction.f(x, y) == z) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(x);
                    pair.add(y);
                    res.add(pair);
                }
            }
        }
        return res;
    }

    /**
     * 方法二：二分查找
     * <p>
     * 当我们固定 x = x0时，函数 g(y)=f(x0,y) 是单调递增函数，可以通过二分查找来判断是否存在 y=y0，使 g(y0)=f(x0,y0)=z 成立。
     */
    public List<List<Integer>> findSolution2(CustomFunction customFunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int x = 1; x <= 1000; x++) {
            int yLeft = 1, yRight = 1000;
            while (yLeft <= yRight) {
                int yMiddle = (yLeft + yRight) / 2;
                if (customFunction.f(x, yMiddle) == z) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(x);
                    pair.add(yMiddle);
                    res.add(pair);
                    break;
                }
                if (customFunction.f(x, yMiddle) > z) {
                    yRight = yMiddle - 1;
                } else {
                    yLeft = yMiddle + 1;
                }
            }
        }
        return res;
    }

    /**
     * 方法三：双指针
     * 假设 x1 < x2，且 f(x1,y1) = f(x2,y2) = z，显然有 y1 > y2 。
     * 因此我们从小到大进行枚举 x，并且从大到小枚举 y，当固定 x 时，不需要重头开始枚举所有的 y，只需要从上次结束的值开始枚举即可。
     */
    public List<List<Integer>> findSolution3(CustomFunction customFunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int x = 1, y = 1000; x <= 1000 && y >= 1; x++) {
            while (y >= 1 && customFunction.f(x, y) > z) {
                y--;
            }
            if (y >= 1 && customFunction.f(x, y) == z) {
                List<Integer> pair = new ArrayList<>();
                pair.add(x);
                pair.add(y);
                res.add(pair);
            }
        }
        return res;
    }

}

interface CustomFunction {
    // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
    int f(int x, int y);
};
