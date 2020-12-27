package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\11 0011-20:44
 * Sort Color
 */
public class leetCode75 {

    public static void sortColors(int[] colors) {
        if (colors == null || colors.length <= 1) {
            return;
        }
        //左指针找到第一个不是 0 的数
        int colFirst = 0;
        while (colFirst < colors.length && colors[colFirst] == 0) {
            colFirst++;
        }
        //右指针找到第一个不是 2 的数
        int colLast = colors.length - 1;
        while (colLast >= 0 && colors[colLast] == 2) {
            colLast--;
        }

        //start form first non-number
        int index = colFirst;
        while (index <= colLast) {
            //System.out.println(colFirst+" "+colLast)
            if (colors[index] == 1) {
                index++;
            } else if (colors[index] == 0) {
                switchColor(colors, colFirst, index);
                colFirst++;
                index++;
            } else if (colors[index] == 2) {
                switchColor(colors, colLast, index);
                colLast--;
            }
        }
    }

    private static void switchColor(int[] colors, int i, int j) {
        int tmp = colors[i];
        colors[i] = colors[j];
        colors[j] = tmp;
    }
}
