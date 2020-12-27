package mcw.test.offer;

/**
 * @author mcw 2020\1\28 0028-18:36
 * <p>
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长的 m 段，（m,n都是整数，n>1且m>1）,
 * 每段绳子长度记为k[0],k[1]...k[m]。 请问k[0]*k[1]*k[2]...*k[m]可能的最大乘积是多少？
 */
public class Test67 {

    public static int cutRope(int target) {
        int a = 0;
        int c = 0;
        int maxValue = 2;
        if (target == 2) return 1;
        if (target == 3) return 2;
        if (target % 3 == 0) {
            maxValue = (int) Math.pow(3, target / 3);
        } else {
            a = target - 2;
            c = a % 3;
            maxValue = (int) (maxValue * Math.pow(3, a / 3));
            if (0 != c) {
                maxValue = maxValue * c;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        System.out.println(cutRope(10));
    }
}
