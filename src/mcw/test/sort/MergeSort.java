package mcw.test.sort;


/**
 * @author mcw 2020\1\30 0030-19:42
 * <p>
 * 归并排序 最好 O(nLogn) 最坏 O(nLogn)  平均 O(nLogn)
 */
public class MergeSort {


    public static int[] sort(int[] array,int low,int high){
        int mid = (low + high) / 2;
        if (low < high){
            sort(array,low,mid);
            sort(array,mid+1,high);
            //归并
            merge(array,low,mid,high);
        }
        return array;
    }

    private static void merge(int[] a, int low, int mid, int high) {
        //初始化一个从起始s到终止e的一个数组
        int[] temp = new int[(high - low) + 1];
        //左起始指针
        int l = low;
        //右起始指针
        int r = mid+1;
        int i = 0;
        //将s-e这段数据在逻辑上一分为二,l-m为一个左边的数组,r-e为一个右边的数组,两边都是有序的
        //从两边的第一个指针开始遍历,将其中小的那个值放在temp数组中
        while (l <= mid && r <= high){
            if (a[l] < a[r]){
                temp[i++] = a[l++];
            }else{
                temp[i++] = a[r++];
            }
        }
        //将两个数组剩余的数放到temp中
        while (l <= mid){
            temp[i++] = a[l++];
        }
        while (r <= high){
            temp[i++] = a[r++];
        }

        //将temp数组覆盖原数组
       /* for (int n = 0; n < temp.length; n++) {
            a[low+n] = temp[n];
        }*/
        if (temp.length >= 0) System.arraycopy(temp, 0, a, low, temp.length);

    }


    public static void main(String[] args) {
        int[] sort = sort(new int[]{1, 8, 3, 4, 996, 32, 1, 4, 98, 9, 6, 32},0,11);
        for (int i : sort) {
            System.out.print(i + "\t");
        }
    }
}
