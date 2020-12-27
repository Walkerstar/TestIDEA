package mcw.test.sort;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author mcw 2020\1\30 0030-20:21
 * 快速排序  最好情况 O(nlogn) 最坏 O(n^2) 平均 O(nlogn)
 */
public class QuickSort {

    //数组的快速排序
    public static int[] sort(int []array,int begin,int end){
        int a=begin;
        int b=end;

        if(a>=b) return null;

        //基准数,默认设置为第一个值
        int x=array[a];

        while (a<b){
            //从后往前找,找到一个比基准数x小的值,赋给arr[a]
            //如果a和b的逻辑正确--a<b ,并且最后一个值arr[b]>x,就一直往下找,直到找到后面的值大于x
            while (a<b && array[b]>=x){
                b--;
            }
            //跳出循环,两种情况,一是a和b的逻辑不对了,a>=b,这时候排序结束.二是在后面找到了比x小的值
            if(a<b){
                //将这时候找到的arr[b]放到最前面arr[a]
                array[a]=array[b];
                //排序的起始位置后移一位
                a++;
            }
            //从前往后找,找到一个比基准数x大的值,放在最后面arr[b]
            while (a<b && array[a]<=x){
                a++;
            }
            if(a<b){
                array[b]=array[a];
                //排序的终止位置前移一位
                b--;
            }
        }
        //跳出循环 a < b的逻辑不成立了,a==b重合了,此时将x赋值回去arr[a]
        array[a]=x;
        sort(array,begin,a-1);
        sort(array,a+1,end);
        return array;
    }

    public static int[] quickSort(int[] array,int low,int high){
        if(low>=high) return null;
        int i=low;
        int j=high;
        int temp=array[i];

        while (i<j){
            while (i<j && temp<=array[j]) {
                j--;
            }
            array[i]=array[j];
            while (i<j && array[i]<=temp) {
                i++;
            }
            array[j]=array[i];
        }
        array[i]=temp;
        quickSort(array,low,i-1);
        quickSort(array,i+1,high);
        return array;
    }

    /*public static int[] quickSort(int[] array,int low,int high){
        int i;
        if(low<high){
            i=partition(array,low,high);
            quickSort(array,low,i-1);
            quickSort(array,i+1,high);
        }
        return array;
    }*/

    /**
     * 单链表的快速排序
     */
    public static void swap(LinkedList<Integer> list,int i,int j){
        //由于是单链表，所以直接交换两个值
        int t=list.get(j);
        list.set(j,list.get(i));
        list.set(i,t);
    }

    public static void qsort(LinkedList<Integer> list,int left,int right){
        if(left<right){
            int i=left;        // 初始化i，为链表第一个元素（最左边的元素）
            int j=i+1;         // 初始化j = i + 1
            int x=list.get(i); // 基准数字

            while(j<=right){  // 大循环条件，j不能超过链表长度

                // 如果 j 指向的值大于等于基准数字（如果比基准大，直接跳过）
                while (j<=right && list.get(j)>=x) {
                    j++;
                }

                // 否则，j 指向的值小于基准，则交换
                if(j<=right){
                    i++; // 交换时，i 首先要向后移动一位
                    swap(list,i,j); // 交换
                    j++; // 随后，j向后移动一位
                }
            }
            swap(list,left,i); // 最后，交换 i 位置的值和基准元素。一趟排序结束
            qsort(list,left,i-1); // 递归排序左边
            qsort(list,i+1,right); // 递归排序右边
        }
    }

    public static void main(String[] args) {
        /*int[] sort = sort(new int[]{1, 8, 3, 4, 996, 32, 1, 4, 98, 9, 6, 32},0,11);
        int[] sort = quickSort(new int[]{1, 8, 3, 4, 996, 32, 1, 4, 98, 9, 6,32},0,11);
        for (int i : sort) {
            System.out.print(i + "\t");
        }*/
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Random().nextInt(9));
        }
        System.out.println(list.toString());
        qsort(list,0,9);
        System.out.println(list.toString());
    }

}
