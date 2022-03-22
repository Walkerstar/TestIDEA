package mcw.test.leetcode.bzhan;

/**
 * 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
 * <p>
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 *
 * @author MCW 2022/3/13
 */
public class leetCode393 {

    static final int MASK1 = 1 << 7;
    static final int MASK2 = (1 << 7) + (1 << 6);

    public boolean validUtf8(int[] data) {
        int m = data.length;
        int index = 0;
        while (index < m) {
            int num = data[index];
            int n = getBytes(num);
            if (n < 0 || index + n > m) {
                return false;
            }
            for (int i = 1; i < n; i++) {
                if (!isValid(data[index + i])) {
                    return false;
                }
            }
            index += n;
        }
        return true;
    }

    public int getBytes(int num) {
        if ((num & MASK1) == 0) {
            return 1;
        }
        int n = 0;
        int mask = MASK1;
        while ((num & mask) != 0) {
            n++;
            if (n > 4) {
                return -1;
            }
            mask >>= 1;
        }
        return n >= 2 ? n : -1;
    }

    public boolean isValid(int num) {
        return (num & MASK2) == MASK1;
    }


    /**
     * 一字节码：第一字节<128；
     * 二字节码：192<=第一字节<224；
     * 三字节码：224<=第一字节<240；
     * 四字节码：248>第一字节>=240；
     * 其他大小的第一字节非法；
     * 大于二字节的码，后边的字节都大于等于128小于192
     */
    public boolean validUtf82(int[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 128) {
                continue;
            }
            if (data[i] < 192 || data[i] >= 248) {
                return false;
            }
            if (data[i] < 224) {
                if (i + 2 > data.length) {
                    return false;
                }
                if (data[i + 1] < 128 || data[i + 1] >= 192) {
                    return false;
                }
                i++;
            } else if (data[i] < 240) {
                if (i + 3 > data.length) {
                    return false;
                }
                for (int j = 1; j <= 2; j++) {
                    if (data[i + j] < 128 || data[i + j] >= 192) {
                        return false;
                    }
                }
                i += 2;
            } else {
                if (i + 4 > data.length) {
                    return false;
                }
                for (int j = 1; j <= 3; j++) {
                    if (data[i + j] < 128 || data[i + j] >= 192) {
                        return false;
                    }
                }
                i += 3;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new leetCode393().validUtf82(new int[]{235, 140, 4}));
    }
}
