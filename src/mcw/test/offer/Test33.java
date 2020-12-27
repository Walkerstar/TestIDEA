package mcw.test.offer;

import java.lang.management.MemoryManagerMXBean;
import java.util.ArrayList;

/**
 * @author mcw 2020\1\19 0019-16:08
 * <p>
 * 把只包含质因子 2 or 3 or 5的数称作为丑数。习惯上我们把 1 当做是第一个丑数。
 * 求按从小到大的顺序的第 N 个丑数。
 */
public class Test33 {

    public static int GetUglyNumber(int n) {
        if (n <= 0)
            return 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while (list.size() < n) {
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);
            if (min == m2) i2++;
            if (min == m3) i3++;
            if (min == m5) i5++;
        }
        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(GetUglyNumber(6));
    }
}
