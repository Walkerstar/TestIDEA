package mcw.test.leetcode.niuke;

import java.util.ArrayList;

/**
 * 给定一个索引 K ，返回杨辉三角的第 K行 。K从0开始  备注：能够只使用 O(k) 的额外空间完成吗？
 *
 * @author mcw 2020\3\22 0022-18:19
 */
public class Test32 {

    /**
     * 递归调用，每次计算出 当前行的前一行数据
     */
    public static ArrayList<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            return list;
        }
        ArrayList<Integer> preRow = getRow(rowIndex - 1);
        ArrayList<Integer> thisRow = new ArrayList<>();
        thisRow.add(1);

        for (int i = 1; i < rowIndex; i++) {
            thisRow.add(preRow.get(i - 1) + preRow.get(i));
        }
        thisRow.add(1);
        return thisRow;
    }

    /**
     *从后往前计算，避免数据被覆盖。
     */
    public static ArrayList<Integer> getrow(int rowIndex) {
        ArrayList<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i < rowIndex + 1; i++) {
            for (int j = i - 1; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
            //System.out.println(row);
        }
        return row;
    }

    //数组解决
    public static ArrayList<Integer> getrow1(int rowIndex) {
        int[] last = new int[rowIndex + 1];
        int[] now = new int[rowIndex + 1];
        int[] temp ;
        last[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            now[0] = 1;
            now[i] = 1;
            for (int j = 1; j < i; j++) {
                now[j] = last[j - 1] + last[j];
            }
            temp = last;
            last = now;
            now = temp;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            ans.add(last[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getrow1(3));
    }
}
