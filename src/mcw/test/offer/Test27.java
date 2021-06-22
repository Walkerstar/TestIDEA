package mcw.test.offer;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mcw 2020\1\18 0018-14:09
 * <p>
 * 输入一个字符串，按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc，则输出有字符a,b,c所能排列出来的所有字符串
 * abc,acb,bca,bac,cab,cba。
 */
public class Test27 {

    static ArrayList<String> arrayList = new ArrayList<>();

    public static ArrayList<String> Permutation(String str) {
        char[] chars = str.toCharArray();
        boolean[] bf = new boolean[chars.length];
        String s = "";
        return Permutation(chars, bf, s, str);
    }

    private static ArrayList<String> Permutation(char[] chars, boolean[] bf, String s, String str) {
        if (str.length() == 0) {
            return arrayList;
        }
        if (s.length() == chars.length) {
            if (!arrayList.contains(s)) {
                arrayList.add(s);
            }
            return arrayList;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!bf[i]) {
                bf[i] = true;
                s += chars[i];
                Permutation(chars, bf, s, str);
                s = s.substring(0, s.length() - 1);
                bf[i] = false;
            }
        }
        return arrayList;
    }


    /**
     * 下一个排列
     */
    public String[] permutation1(String s) {
        List<String> ret = new ArrayList<>();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        do {
            ret.add(new String(arr));
        } while (nextPermutation(arr));
        int size = ret.size();
        String[] retArr = new String[size];
        for (int i = 0; i < size; i++) {
            retArr[i] = ret.get(i);
        }
        return retArr;
    }

    private boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = arr.length - 1;
        while (j >= 0 && arr[i] >= arr[j]) {
            j--;
        }
        swap(arr, i, j);
        reverse(arr, i + 1);
        return true;
    }

    private void reverse(char[] arr, int start) {
        int left = start, right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
