package mcw.test.offer;

import java.util.PriorityQueue;

/**
 * @author mcw 2020\1\28 0028-15:14
 * <p>
 * 求一个数据流的中位数？ 输入的数据流需要进行排序，然后在去中位数
 */
public class Test63 {

    //降序就是大顶堆，
    private static PriorityQueue<Integer> leftHeap = new PriorityQueue<>((x, y) -> y - x);

    //升序就是小顶堆
    private static PriorityQueue<Integer> rightHeap = new PriorityQueue<>();

    //当前数据流的个数
    public static int count = 0;

    //每次插入小顶堆的是当前大顶堆中最大的数
    //每次插入大顶堆的是当前小顶堆中最小的数
    //这样保证小顶堆中的数永远大于大顶堆中的数
    //中位数就可以方便的从两者的根节点中获取

    public static void insert(Integer num) {
        if (count % 2 == 0) { //个数为偶数，则先插入到大顶堆，然后将大顶堆最大的数据插入到小顶堆
            leftHeap.add(num);
            rightHeap.add(leftHeap.poll());
        } else {             //个数为奇数，则先插入到小顶堆，然后将小顶堆最小的数据插入到大顶堆
            rightHeap.add(num);
            leftHeap.add(rightHeap.poll());
        }
        count++;
    }

    public static Double GetMedian() {
        if (leftHeap.isEmpty()) return 0.0;
        if (count % 2 == 0)
            return (leftHeap.peek() + rightHeap.peek()) / 2.0;
        else
            return leftHeap.peek() / 1.0;
    }
}
