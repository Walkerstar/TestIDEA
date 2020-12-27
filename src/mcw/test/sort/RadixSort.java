package mcw.test.sort;

import java.util.ArrayList;

/**
 * @author mcw 2020\1\31 0031-20:02
 * <p>
 * 基数排序 最好 最坏 平均 O(d(n+r))
 */
public class RadixSort {

    public static int[] sort(int[] array) {
        if (array == null || array.length < 2)
            return array;

        //1.先算出最大数的位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;

        //2.构建包含10个集合的桶
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }

        //3.排序
        /*for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
                int index = 0;
                for (int m = 0; m < bucketList.size(); m++) {
                    for (int k = 0; k < bucketList.get(m).size(); k++)
                        array[index++] = bucketList.get(m).get(k);
                    bucketList.get(m).clear();
                }
            }*/
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int value : array) {
                int num = (value % mod) / div;
                bucketList.get(num).add(value);
            }
            int index = 0;
            for (ArrayList<Integer> list : bucketList) {
                for (Integer integer : list) array[index++] = integer;
                list.clear();
            }
        }
        return array;
    }


    public static void main(String[] args) {
        int[] sort = sort(new int[]{11, 8, 3, 4, 996, 32, 1, 4, 98, 9, 6,32});
        for (int i : sort) {
            System.out.print(i + "\t");
        }
    }

    //===================================链表的基数排序=========================================================
    /**
     * 链表节点定义
     */
    private static class Node<Integer> {
        int element;
        private Node<Integer> next;

        private Node(int element, Node<Integer> next) {
            this.element = element;
            this.next = next;
        }
    }

    void addlast(Node<Integer> n, Node<Integer> m) {
        if (n == null)
            n = m;
        if (n != null) {
            Node<Integer> nod = n;
            while (nod.next != null) {
                nod = nod.next;
            }
            nod.next = m;
        }

    }

    public void sort(int[] n, int d) {
        int lon = n.length;//链表长度
        int h = 1;//用来求个十百千位数值的
        Node<Integer> node = new Node<Integer>(n[0], null);//node存储数组
        Node<Integer> no = node;
        Node<Integer>[] next = (Node<Integer>[]) new Node<?>[19];//桶
        for (int i = 1; i < n.length; i++) {
            Node<Integer> nod = new Node<Integer>(n[i], null);
            no.next = nod;
            no = no.next;
        }
        for (int j = 0; j < d; j++) {//分配与收集的次数
            for (int k = 0; k < 19; k++) {//按桶号分配
                Node<Integer> y = node;
                int address = 0; //此号桶第一个元素所在的位置
                for (int sd = 0; sd < lon; sd++) {//找到该桶的第一个元素，
                    // 因为null与Node类的element(int)类型不同，如果不这样给赋值
                    //在下面直接用node.element运算虽然不报错，但运行时确实是错误的。
                    //赋值是为了把null存储为int型数据，在调用这个对象的element可以
                    //直接运算
                    if ((y.element / h % 10) + 9 == k) {
                        next[k] = new Node<Integer>(y.element, null);
                        address = sd;
                        break;
                    }
                    y = y.next;
                }
                Node<Integer> b = node;
                Node<Integer> s = next[k];
                for (int zx = 0; zx < lon; zx++) {//往k号桶里装
                    if (zx > address && (b.element / h) % 10 + 9 == k) {
                        //zx>address是为了不再裝一遍这个桶的一号元素，避免重复
                        s.next = new Node<Integer>(b.element, null);
                        s = s.next;
                    }
                    b = b.next;
                }
            }
            h = h * 10;
            int g = 0;
            node = null;//clear
            Node<Integer> zxc = node;
            while (g < 19) {//按照桶号收集
                if (node == null) {
                    node = next[g];
                } else if (node != null) {
                    Node<Integer> nod = node;
                    while (nod.next != null) {
                        nod = nod.next;
                    }
                    nod.next = next[g];
                }
                next[g] = null;
                g++;
            }
        }
        Node<Integer> node1 = node;
        while (node1 != null) {
            System.out.println(node1.element + "顺序");
            node1 = node1.next;
        }
    }

}
