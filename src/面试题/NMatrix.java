package 面试题;

/**
 * 输入正整数 N （N 不大于 100 ），输出一个 N 行矩阵 。 例 ： N=5
 *   1  3   6   10   15
 *   2  5   9   14   16
 *   4  8   13  17   22
 *   7  12  18  21   23
 *   11 19  20  24   25
 * @author mcw 2020\8\21 0021-15:02
 */
public class NMatrix {
    public static void outPutMatrix(int num) {
        int[][] ary = new int[num][num];
        int start = 1;

        for (int i = 1; i < 2 * num; i++) {
            if (i <= num) {
                int startPoint = i - 1;
                // 方向都是从j=0的方向开始
                for (int j = 0; j < i; j++) {
                    ary[startPoint--][j] = start++;
                }
            } else {
                int foot = num - 1;
                int footEnd = i - num;

                for (int k = 0; k < 2 * num - i; k++) {
                    if (num % 2 == 1) { // 考虑sqrt为奇数和偶数情况
                        if (i % 2 == 1) {
                            ary[foot--][footEnd++] = start++;
                        } else {
                            ary[footEnd++][foot--] = start++;
                        }
                    } else {
                        if (i % 2 == 0) {
                            ary[foot--][footEnd++] = start++;
                        } else {
                            ary[footEnd++][foot--] = start++;
                        }
                    }
                }
            }
        }
        //打印数组
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(ary[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
       outPutMatrix(6);
    }
}

