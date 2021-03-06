package mcw.test.offer;

/**
 * @author mcw 2020\1\20 0020-20:18
 *
 * 在数组中输出两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 *
 * 输入一个数组，求这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。即输出P%1000000007
 */
public class Test35 {

    public static int InverseParis(int[] array){
        if(array==null || array.length==0)
            return 0;
        int[] copy=new int[array.length];
        for (int i=0;i<array.length;i++){
            copy[i]=array[i];
        }
        int count=InverseParisCore(array,copy,0,array.length-1);
        return count;
    }

    private static int InverseParisCore(int[] array, int[] copy, int low, int high) {
        if(low==high)
            return 0;
        int mid=(low+high)>>1;
        int leftCount=InverseParisCore(array,copy,low,mid)%1000000007;
        int rightCount=InverseParisCore(array,copy,mid+1,high)%1000000007;
        int count=0;
        int i=mid,j=high;
        int locCopy=high;
        while(i>=low && j>mid){
            if(array[i]>array[j]){
                count+=j-mid;
                copy[locCopy--]=array[i--];
                if(count>=1000000007){
                    count%=1000000007;
                }
            }else{
                copy[locCopy--]=array[j--];
            }
        }
        for (;i>=low;i--){
            copy[locCopy--]=array[i];
        }
        for(;j>=mid;j--){
            copy[locCopy--]=array[i];
        }
        for(int s=low;s<=high;s++){
            array[s]=copy[s];
        }
        return (leftCount+rightCount+count)%1000000007;
    }
}
