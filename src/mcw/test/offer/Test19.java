package mcw.test.offer;

import java.util.ArrayList;

/**
 * @author mcw 2020\1\16 0016-13:50
 * <p>
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，
 * 如果输入如下4 x 4矩阵:
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16，则
 * 依次打印数字: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
 */
public class Test19 {

    //旋转魔方方法，一次取一行，然后旋转
    public static ArrayList<Integer> printMatrix_2(int[][] matrix) {
        ArrayList<Integer> a1 = new ArrayList<>();
        int row = matrix.length;
        while (row != 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                a1.add(matrix[0][i]);
            }
            if (row == 1)
                break;
            matrix = turn(matrix);
            row = matrix.length;
        }
        return a1;
    }

    private static int[][] turn(int[][] matrix) {
        int col = matrix[0].length;
        int row = matrix.length;
        int[][] newMatrix = new int[col][row - 1];
        for (int j = col - 1; j >= 0; j--) {
            for (int i = 1; i < row; i++) {
                newMatrix[col - 1 - j][i - 1] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static void main(String[] args) {
        int [][]a={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        ArrayList<Integer> integers = printMatrix_2(a);
        for (Integer integer : integers) {
            System.out.print(integer+"\t");
        }

    }

}
