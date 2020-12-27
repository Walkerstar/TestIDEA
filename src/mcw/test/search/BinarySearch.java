package mcw.test.search;


/**
 * @author mcw 2020\2\3 0003-21:37
 *
 * 二分查找
 */
public class BinarySearch {

    //非递归
    public int search(int[] array,int value){
        int low=0;
        int high=array.length-1;
        while(low<=high){
            int middle=low+((high-low)>>1);
            if (value==array[middle]){
                return middle;
            }
            if(value>array[middle])
                low=middle+1;
            if(value<array[middle])
                high=middle-1;
        }
        return -1;
    }


    //递归
    public int search1(int[] a,int value){
        int low=0;
        int high=a.length-1;
        return searchmy(a,low,high,value);
    }

    private int searchmy(int[] a, int low, int high, int value) {
        if(low>high)
            return -1;
        int mid=low+((high-low)>>1);
        if(value==a[mid])
            return mid;
        if(value<a[mid])
            return searchmy(a,low,mid-1,value);
        return searchmy(a,mid+1,high,value);
    }
}
