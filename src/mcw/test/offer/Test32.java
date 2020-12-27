package mcw.test.offer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author mcw 2020\1\19 0019-15:29
 *
 *
 * 输入一个正整数数组，把数组里所有的数字拼起来排成一排，
 * 打印出所能拼接的所有数字中最小的一个
 */
public class Test32 {

    public static String PrintMinNumber(int[] numbers){
        if(numbers==null || numbers.length==0)
            return "";
        int len=numbers.length;
        String[] arr=new String[len];
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<len;i++){
            arr[i]=String.valueOf(numbers[i]);
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1=o1+o2;
                String s2=o2+o1;
                return s1.compareTo(s2);
            }
        });
        for (String s:arr){
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = PrintMinNumber(new int[]{3, 32, 321,5234});
        System.out.println(s);
    }
}
