package mcw.test.sort;


/**
 * 堆排序  最佳情况： O(nlogn)  最差情况：O(nlogn)   平均情况：O(nlogn)
 * @author mcw 2020\1\31 0031-17:50
 */
public class HeapSort {

    public static int[] HeapSort(int[] array) {
        int len = array.length; //记录数组长度
        if (len < 1) return array;
        //构建一个最大堆
        buildMaxHeap(array,len);
        //2.循环将堆首位（最大值）与末位交换，然后再重新调整最大堆
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0, len);
        }
        return array;
    }

    /**
     * 建立最大堆
     */
    private static void buildMaxHeap(int[] array,int len) {
        //从最后一个非叶子结点开始向上构造最大堆， i表示最后一个非叶子结点的数组索引
        //for循环这样写会更好一点： i的左子树和右子树分别为2i+1 和 2(i+1)
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustHeap(array, i, len);
        }
    }

    private static void adjustHeap(int[] array, int i,int len) {
        int maxIndex = i;
        //如果有左子树，且左子树节点大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[i * 2] > array[maxIndex])
            maxIndex = i * 2 ;

        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
            maxIndex = i * 2 + 1;

        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex, len);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
//        int[] sort = HeapSort(new int[]{1, 8, 3, 4, 996, 32, 1, 4, 98, 9, 6,32});
//        for (int i : sort) {
//            System.out.print(i + "\t");
//        }
        int[] a = {5, 11, 7, 2, 3, 17};
        buildMaxHeap(a,6);
        for (int i : a) {
            System.out.println(i);
        }

    }

}
