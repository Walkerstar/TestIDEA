package mcw.test.sort;

/**
 * @author mcw 2020\1\30 0030-18:11
 * <p>
 * 插入排序  最好情况 O(n) 最坏 O(n^2) 平均 O(n^2)
 */
public class InsertionSort {

    public static int[] sort(int[] array) {
        if (array.length == 0) return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            //当前数 比前一个数小，前一个数后移，结束循环后，把当前数移到前一个位置
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sort = sort(new int[]{1, 8, 3, 4, 996, 32, 1, 4, 98, 9, 6, 32});
        for (int i : sort) {
            System.out.print(i + "\t");
        }
    }
}
