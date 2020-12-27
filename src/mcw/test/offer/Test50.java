package mcw.test.offer;

/**
 * @author mcw 2020\1\24 0024-20:18
 *
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 找出 数组中任意一个重复的数字，
 */
public class Test50 {

    public static boolean duplicate(int array[],int length,int duplication[]){
        if(array==null)
            return false;
        for (int i=0;i<length;i++){
            if(array[i]<0 || array[i]>length-1){
                return false;
            }
        }

        int []copy=new int[length];
        for (int i=0;i<length;i++){
            copy[array[i]]++;
        }
        for (int i=0;i<length;i++){
            if(copy[i]>1){
                duplication[0]=i;
                return true;
            }
        }
        return false;
    }
}
