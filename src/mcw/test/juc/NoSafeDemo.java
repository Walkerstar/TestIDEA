package mcw.test.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author mcw 2020\2\7 0007-21:20
 *
 * 1.故障现象
 *  java.util.ConcurrentModificationException
 *
 * 2.导致原因
 *
 * 3.解决方案
 *  3.1 new Vector<>()
 *  3.2 Collections.synchronizedList(new ArrayList<>())
 *  3.3 new CopyOnWriteArrayList<>()
 *
 * 4.优化建议（同样的错误，不出现 2 次）
 */
public class NoSafeDemo {
    public static void main(String[] args) {

        //testCurrentHashMap();
        mapNoSafe();
    }

    private static void mapNoSafe() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            }).start();
        }
    }

    private static void testCurrentHashMap() {
        Map<String,String> map=new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNoSafe() {
        Set<String> set=new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNoSafe() {
        List<String> list= new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
