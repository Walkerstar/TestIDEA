package mcw.test.offer;

import java.util.LinkedList;

/**
 * 约瑟夫环问题 公式为：x'=(x+m)%n  其中 n 为总人数,m 为喊出的编号
 * @author mcw 2020\1\23 0023-20:42
 */
public class Test46 {

    public static int LastRemaining(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int bt = 0;
        while (list.size() > 1) {
            //从0开始计数，每个被删除的人相隔m-1个，从第二轮开始，bt可能大于n,所以取余
            bt = (bt + m - 1) % list.size();
            list.remove(bt);
        }
        return list.size() == 1 ? list.get(0) : -1;
    }

    public static void main(String[] args) {
        System.out.println(LastRemaining(5,2));
    }

}
