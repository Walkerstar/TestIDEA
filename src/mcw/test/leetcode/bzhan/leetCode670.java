package mcw.test.leetcode.bzhan;

/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * @author mcw 2022/9/13 16:58
 */
public class leetCode670 {

    /**
     * 直接遍历
     */
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxNum = num;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(charArray, i, j);
                maxNum = Math.max(maxNum, Integer.parseInt(new String(charArray)));
                swap(charArray, i, j);
            }
        }
        return maxNum;
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }


    /**
     * 贪心
     * 每一位数字应该不小于所有排它后面的数字，否则找最大的且排最后面的数字与之交换
     * 13245
     *
     * 4 < 5 i1=3 i2=4 maxIdx=4
     * 2 < 5 i1=2 i2=4 maxIdx=4
     * 3 < 5 i1=1 i2=4 maxIdx=4
     * 1 < 5 i1=0 i2=4 maxIdx=4
     */
    public int maximumSwap2(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxIdx = n - 1;
        int idx1 = -1, idx2 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (charArray[i] > charArray[maxIdx]) {
                maxIdx = i;
            } else if (charArray[i] < charArray[maxIdx]) {
                idx1 = i;
                idx2 = maxIdx;
            }
        }
        if (idx1 >= 0) {
            swap(charArray, idx1, idx2);
            return Integer.parseInt(new String(charArray));
        } else {
            return num;
        }
    }


}