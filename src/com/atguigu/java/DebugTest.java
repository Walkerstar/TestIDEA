package com.atguigu.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author mcw
 * @date 2019\11\16 0016-17:43
 */
public class DebugTest {

    private static void Test01() {
        for (int i = 0; i <100 ; i++) {
            System.out.println(i);
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age","12");
        map.put("school","Tsinghua");
        map.put("major","computer");

        String age = map.get("age");
        System.out.println("age = " + age);

        map.remove("major");
        System.out.println(map);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int N = sc.nextInt();  //n 个人
            int M = sc.nextInt();  //m 组关系
            Map<Integer, Integer> mapRelation = new HashMap<>();//确认认识
            Map<Integer, Integer> mapUnRelation = new HashMap<>();//可能认识
            mapRelation.put(1, 1);
            for(int i = 0; i < M; i++){
                int one = sc.nextInt();
                int two = sc.nextInt();
                int three = sc.nextInt();
                //当有关系时
                if(three == 1){
                    if(mapRelation.containsKey(one) && !mapRelation.containsKey(two)){
                        mapRelation.put(two,two);
                        if(mapUnRelation.containsKey(two)){
                            int tmp = mapUnRelation.remove(two);
                            mapUnRelation.remove(tmp);
                            mapRelation.put(tmp, tmp);
                        }
                    }else if(mapRelation.containsKey(two) && !mapRelation.containsKey(one))  {
                        mapRelation.put(one,one);
                        if(mapUnRelation.containsKey(one)){
                            int tmp = mapUnRelation.remove(one);
                            mapUnRelation.remove(tmp);
                            mapRelation.put(tmp, tmp);
                        }
                    }else if(!mapRelation.containsKey(one) && !mapRelation.containsKey(two)) {
                        if(!mapUnRelation.containsKey(one)) mapUnRelation.put(one, two);
                        if(!mapUnRelation.containsKey(two)) mapUnRelation.put(two, one);
                    }
                }

            }

            System.out.println(mapRelation.size() - 1);
        }
    }
}
