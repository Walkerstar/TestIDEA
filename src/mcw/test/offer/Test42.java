package mcw.test.offer;


/**
 * @author mcw 2020\1\22 0022-19:48
 *
 * 输入一个递增排序的数组和一个数字S,查找和为S的两个数，
 * 如果有多对，输出两个数乘积最小的。
 */
public class Test42 {

    public static int FindNumbersWithSum(int[] array,int sum){
        int result=0;
        int n=array.length;
        int i=0,j=n-1;
        while(i<j){
            if(array[i]+array[j]==sum){
                result=array[i]*array[j];
                break;
            }
            while (i<j && array[i]+array[j]>sum) --j;
            while (i<j && array[i]+array[j]<sum) ++i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(FindNumbersWithSum(new int[]{1,2,3,4,5,6,7,8,9},8));
    }
}
