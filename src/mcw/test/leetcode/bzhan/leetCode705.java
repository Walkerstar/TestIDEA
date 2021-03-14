package mcw.test.leetcode.bzhan;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * <p>
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * @author mcw 2021\3\13 0013-15:42
 */
public class leetCode705 {

    /**
     * 方法一：链地址法
     * 设哈希表的大小为 base，则可以设计一个简单的哈希函数：hash(x)=x mod base。
     *
     * 我们开辟一个大小为 base 的数组，数组的每个位置是一个链表。当计算出哈希值之后，就插入到对应位置的链表当中。
     *
     * 由于我们使用整数除法作为哈希函数，为了尽可能避免冲突，应当将 base 取为一个质数。在这里，我们取 base=769。
     */
    class MyHashSet{
        private static final int BASE=769;
        private LinkedList[] data;

        public MyHashSet() {
            this.data = new LinkedList[BASE];
            for (int i = 0; i < BASE; i++) {
                data[i]=new LinkedList<Integer>();
            }
        }

        public void add(int key){
            int h=hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element==key){
                    return;
                }
            }
            data[h].offerLast(key);
        }

        public void remove(int key){
            int h=hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element==key){
                    data[h].remove(element);
                    return;
                }
            }
        }

        public boolean contains(int key){
            int h=hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element==key){
                    return true;
                }
            }
            return false;
        }


        private int hash(int key){
            return key%BASE;
        }
    }
}
