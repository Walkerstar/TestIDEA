package mcw.test.offer;


/**
 * @author mcw 2020\1\22 0022-21:15
 *
 * 在一副牌中抽取 5 张，大小王共四张，可以看成任意数字，（为代码方便，默认为0）
 * 如果5张牌能够组成顺子，输出true，否者输出false
 */
public class Test45 {

    public static boolean isContinuous(int[] number) {
        int[] d = new int[14];
        d[0] = -5;
        int len = number.length;
        int max = -1;
        int min = 14;
        for (int i = 0; i < len; i++) {
            d[number[i]]++;
            if (number[i] == 0) continue;
            if (d[number[i]] > 1) return false; //判断一张牌出现的次数
            if (number[i] > max) max = number[i];
            if (number[i] < min) min = number[i];
        }
        if (max - min < 5)
            return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isContinuous(new int[]{0,0,3,7,6}));
    }
}
