package mcw.test.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author mcw 2020\1\18 0018-14:48
 *
 * 输入n个整数,找出其中最小的 K 个数。
 *
 * 例如输入4,5,1,6,2,7,3,8,最小的4个数字是1,2,3,4
 */
public class Test29 {

    public static ArrayList<Integer> GetLeastNumbers(int[] input,int k){
        ArrayList<Integer> result=new ArrayList<>();
        int length=input.length;
        if(k==0)
            return result;
        if(k>=length) {
            for (int i : input) {
                result.add(i);
            }
            return result;
        }
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for(int i=0;i<length;i++){
            if(maxHeap.size()!=k){
                maxHeap.offer(input[i]);
            }else if(maxHeap.peek()>input[i]){
                /*Integer temp=*/maxHeap.poll();
                //temp=null;
                maxHeap.offer(input[i]);
            }
        }

        for(Integer i:maxHeap){
            result.add(i);
        }
        return result;
    }

    public static ArrayList<Integer> getLeastNum(int[] input,int k){
        ArrayList<Integer> list=new ArrayList<>();
        Arrays.sort(input);
        if(k>input.length) return list;
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    public static void main(String[] args) {
//        ArrayList<Integer> list = getLeastNum(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4);
        ArrayList<Integer> list = GetLeastNumbers(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4);
        for (Integer i : list) {
            System.out.print(i+"\t");
        }
    }
}
