package mcw.test.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author mcw 2020\3\24 0024-19:51
 * 在一个大日志文件中，找出访问次数最多的 IP？
 */
public class MaxCountIP {


    //1.构造数据 ，生成1亿个IP地址，写入文件
    public void genIP(String fileName) throws Exception {
        PrintWriter out = new PrintWriter(fileName);
        String s;
        Random r = new Random();
        for (int i = 0; i < 100000000; i++) {
            s = "159.227.";
            s += r.nextInt(256) + "." + r.nextInt(256);
            out.println(s);
        }
        out.close();
    }

    //2.把这个文件分割成1000个小文件
    public void splitMiniFile(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        PrintWriter[] out = new PrintWriter[1000];
        for (int i = 0; i < 1000; i++) {
            out[i] = new PrintWriter(fileName + i);
        }
        String ip;
        while (reader.readLine() != null) {
            ip = reader.readLine();
            int fileNum = ip.hashCode() % 1000;
            fileNum = (fileNum >= 0) ? fileNum : fileNum + 1000;
            out[fileNum].println(ip);
        }
        for (int i = 0; i < 1000; i++) {
            out[i].close();
        }
        reader.close();
    }

    //3.统计IP
    public Map.Entry<String, Integer> statisIPs(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        HashMap<String, Integer> map = new HashMap<>();
        String ip;
        while ((ip = reader.readLine()) != null) {
            if (map.containsKey(ip)) {
                map.put(ip, map.get(ip) + 1);
            } else {
                map.put(ip, 1);
            }
        }
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        reader.close();
        return maxEntry;
    }

    //4.IP排序
    public static void main(String[] args) throws Exception {
        MaxCountIP t = new MaxCountIP();
        String FileName = "e:/10.txt"; //文件存放的路径
        t.genIP(FileName);
        t.splitMiniFile(FileName);
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            entryList.add(t.statisIPs(FileName + i));
        }

        Map.Entry<String, Integer> maxEntry = entryList.get(0);
        for (int j = 1; j < 1000; j++) {
            if (entryList.get(j).getValue() > maxEntry.getValue()) {
                maxEntry = entryList.get(j);
            }
        }
        System.out.println(maxEntry.getKey());
        System.out.println(maxEntry.getValue());
    }
}
