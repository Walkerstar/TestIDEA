package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * @author mcw 2022/2/10 14:23
 */
public class leetCode1447 {

    /**
     * 由于要保证分数在 (0,1) 范围内，我们可以枚举分母 denominator ∈ [2,n] 和分子 numerator ∈ [1,denominator)，
     * 若分子分母的最大公约数为 1，则我们找到了一个最简分数。
     */
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();

        for (int denominator = 2; denominator <= n; denominator++) {
            for (int numerator = 1; numerator < denominator; numerator++) {
                if (gcd(numerator, denominator) == 1) {
                    ans.add(numerator + "/" + denominator);
                }
            }
        }
        return ans;
    }

    /**
     * 最大公约数
     */
    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    /**
     * 最小公倍数:两数乘积=最小公倍数与最大公约数乘积
     */
    int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        leetCode1447 the = new leetCode1447();
        System.out.println(the.simplifiedFractions(4));
        System.out.println(the.gcd(4,2));
        System.out.println(the.lcm(4, 2));
    }
}
