package mcw.test.offer;

/**
 * @author mcw 2020\1\12 0012-13:39
 *在一个二维数组中，每一行都是从左到右递增，每一列都是从上到下递增。
 *完成一个函数，输入这样的一个二维数组和一个整数，判断该整数是否在这个二维数组中。
 */
public class Test1 {

    public boolean Find(int target, int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        int i = rows - 1, j = 0;
        while (i >= 0 && j < cols) {
            if (target < array[i][j]) {
                i--;
            } else {
                if (target > array[i][j])
                    j++;
                else
                    return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

        int[][] array={{1,2,3},{4,5,6},{7,8,9}};
        Test1 test=new Test1();
        System.out.println(test.Find(10,array));

    }
}
