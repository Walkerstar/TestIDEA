package mcw.test.offer;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Set;

/**
 * @author mcw 2020\1\18 0018-14:37
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这样的数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过了数组长度的一半，因此输出2，否则输出0
 */
public class Test28 {

    public static int MoreThanHalf(int[] array ){
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int value:array){
            if(!map.containsKey(value)){
                map.put(value,1);
            }else {
                map.put(value,map.get(value)+1);
            }
        }
        Set<Integer> set=map.keySet();
        for (Integer i : set) {
            if(map.get(i)>(array.length/2)){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr={1,2,3,2,2,2,5,4,2};
        System.out.println(MoreThanHalf(arr));
    }
}
