package mcw.test.leetcode.bzhan;

/**
 * 给你两个字符串 a 和 b ，它们长度相同。请你选择一个下标，将两个字符串都在 相同的下标 分割开。由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix + asuffix ，同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。请你判断 aprefix + bsuffix 或者 bprefix + asuffix 能否构成回文串。
 * <p>
 * 当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。比方说， s = "abc" 那么 "" + "abc" ， "a" + "bc" ， "ab" + "c" 和 "abc" + "" 都是合法分割。
 * <p>
 * 如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。
 * <p>
 * 注意， x + y 表示连接字符串 x 和 y 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = "x", b = "y"
 * 输出：true
 * 解释：如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * 那么 aprefix + bsuffix = "" + "y" = "y" 是回文串。
 * 示例 2：
 * <p>
 * 输入：a = "abdef", b = "fecab"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：a = "ulacfd", b = "jizalu"
 * 输出：true
 * 解释：在下标为 3 处分割：
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * 那么 aprefix + bsuffix = "ula" + "alu" = "ulaalu" 是回文串。
 * <p>
 * 提示：
 * <p>
 * 1 <= a.length, b.length <= 10^5
 * a.length == b.length
 * a 和 b 都只包含小写英文字母
 *
 * @author MCW 2023/3/18
 */
public class leetCode1616 {


    /**
     * 方法一：双指针
     * 思路
     * <p>
     * 记字符串的长度为 n，分割的下标为 k，即下标 k 之前的字符构成前缀，下标 k 和之后的字符构成后缀，前缀长度为 k，后缀长度为 n−k，0 ≤ k ≤ n。
     * <p>
     * 接下来需要判断 aprefix+bsuffix 或者 bprefix+asuffix 能否构成回文字符串，首先判断 aprefix+bsuffix  能否构成回文字符串。
     * 这个字符串的起始位置是由 a 组成的，末尾位置是由 b 构成的。要想构成回文，起始的部分和末尾的部分必须是倒序相等的，这个可以用双指针来逐位判断。
     * 当遇到不相等的情况时，则说明遇到了分割点，分割的位置可能是左侧的指针，也可能是右侧的指针。
     * 如果分割点是左侧的指针，则需要 b 在双指针之间的字符串构成回文；
     * 如果分割点是右侧的指针，则需要 a 在双指针之间的字符串构成回文。
     * 这二者满足其一即可。
     * <p>
     * 判断 bprefix+asuffix 能否构成回文字符串也是类似的思路。
     */
    public boolean checkPalindromeFormation(String a, String b) {
        return checkConcatenation(a, b) || checkConcatenation(b, a);
    }

    public boolean checkConcatenation(String a, String b) {
        int n = a.length();
        int left = 0, right = n - 1;
        while (left < right && a.charAt(left) == b.charAt(right)) {
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        return checkSelfPalindrome(a, left, right) || checkSelfPalindrome(b, left, right);
    }

    public boolean checkSelfPalindrome(String a, int left, int right) {
        while (left < right && a.charAt(left) == a.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }


    /**
     * 中心扩展
     * 与双指针的方法相反，双指针是从两边往中间找，然后判断中间部分是否能构成回文串；
     * 而中心扩展是从中间往两边找，先判断中心是否含有回文串，然后判断两边的字符串是否为回文串
     */
    public boolean checkPalindromeFormation2(String a, String b) {
        int left = a.length() / 2 - 1;
        //第一次检测，分别从两个字符串的中心处往两边扩展，查找两个字符串中间是否含有回文串
        left = Math.min(check(a, a, left), check(b, b, left));
        //第二次检测，从上一步得到中间回文串的位置，然后依据此位置继续判断两个字符串的剩余位置是否为回文串
        left = Math.min(check(a, b, left), check(b, a, left));
        //如果能够构成合法的回文串，left会被减为 -1 ，否则不能构成
        return left == -1;
    }

    public int check(String str_l, String str_r, int left) {
        int right = str_l.length() - 1 - left;
        while (left >= 0 && right < str_l.length()) {
            if (str_l.charAt(left) != str_r.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return left;
    }
}
