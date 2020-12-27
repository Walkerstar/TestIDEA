package mcw.test.search;

/**
 * @author mcw 2020\2\4 0004-13:19
 *
 * 插值查找
 */
public class InsertSearch {
    public static int search(int[] a,int key,int left,int right){
        while(left<=right){
            if(a[right] == a[left]){
                if(a[right] ==key)
                    return right;
                return -1;
            }

            int middle=left+((key-a[left])/(a[right]-a[left]))*(right-left);
            if(a[middle]==key){
                return middle;
            }
            if(key<a[middle])
                right=middle-1;
            else
                left=middle+1;
        }
        return -1;
    }
}
