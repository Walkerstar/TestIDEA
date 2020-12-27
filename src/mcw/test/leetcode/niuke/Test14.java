package mcw.test.leetcode.niuke;

/**
 * @author mcw 2020\2\22 0022-20:38
 *
 * 现在有一个整数类型的数组，数组中只有一个元素只出现一次，其余元素都出现三次。
 * 找出只出现一次的元素 要求： 线性时间复杂度  &&  不用额外内存空间
 */
public class Test14 {

    public static int singleNumber(int[] A) {
        int ones = 0; //记录只出现一次的bits
        int twos = 0; //记录只出现两次的bits
        int threes;
        for (int i = 0; i < A.length; i++) {
            int t = A[i];
            twos |= ones & t; //要在更新ones前面更新twos
            ones ^= t;
            threes = ones & twos; //ones 和 twos 都为1，即出现了3次
            ones &= ~threes; //抹去出现了3次的bits
            twos &= ~threes;
        }
        return ones;
    }


    //数组中只有一个元素只出现 1 次，其余元素都出现 2 次
    public static int singleNumber1(int[] A) {
        int num=A[0];
        for (int i = 1; i < A.length; i++) {
            num^=A[i];
        }
        return num;
    }




    public static void main(String[] args) {
        int[] A={1,1,1,7,7,5,4,6,5,7,5,4,4};
        System.out.println(singleNumber(A));

        int[] B={1,1,7,7,5,4,6,5,4};
        System.out.println(singleNumber1(B));
    }
}
