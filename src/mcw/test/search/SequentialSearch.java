package mcw.test.search;


/**
 * @author mcw 2020\2\3 0003-21:32
 *
 * 顺序查找
 */
public class SequentialSearch {


    //普通
    public static int search(int[] a,int key){
        for (int i = 0; i < a.length; i++) {
            if(a[i]==key)
                return i;
        }
        return -1;
    }

    //优化
    public static int search1(int[] a,int key){
        int index=a.length-1;
        if(key==a[index])
            return index;
        a[index]=key;
        int i=0;
        while (a[i++] != key);
        return i==index+1?-1:i-1;
    }
}
