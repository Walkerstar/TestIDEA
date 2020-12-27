package mcw.test.offer;

/**
 * @author mcw 2020\1\15 0015-14:50
 * <p>
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位位于数组的前半部分，所有的偶数位位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变
 */
public class Test13 {

    public static void reOrderArray(int[] array) {
        int[] temp = new int[array.length];
        int i, j = 0;
        for (i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                temp[j] = array[i];
                j++;
            }
        }

        for (i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                temp[j] = array[i];
                j++;
            }
        }

        for (int k = 0; k < array.length; k++) {
            array[k] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        reOrderArray(a);
        for (int i : a) {
            System.out.print(i + "\t");
        }

    }
}
