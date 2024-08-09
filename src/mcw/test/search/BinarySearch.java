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

    public static void main(String[] args) {

        // 图案上半部分的高度
        int upperHalfHeight = 2;
        // 打印上半部分
        for (int i = 1; i <= upperHalfHeight; i++) {
            // 打印空格
            for (int j = upperHalfHeight - i; j > 0; j--) {
                System.out.print(" ");
            }
            // 打印星号
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            // 换行
            System.out.println();
        }

        // 图案下半部分高度（不包括中间的一行）
        int lowerHalfHeight = upperHalfHeight - 1;
        // 打印下半部分
        for (int i = lowerHalfHeight; i >= 1; i--) {
            // 打印空格
            for (int j = upperHalfHeight - i; j > 0; j--) {
                System.out.print(" ");
            }
            // 打印星号
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            // 换行
            System.out.println();
        }
    }
}
