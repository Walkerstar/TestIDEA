package mcw.test.offer;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * @author mcw 2020\1\18 0018-14:09
 *
 * 输入一个字符串，按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc，则输出有字符a,b,c所能排列出来的所有字符串
 * abc,acb,bca,bac,cab,cba。
 */
public class Test27 {

   static ArrayList<String> arrayList=new ArrayList<>();

    public static ArrayList<String> Permuntation(String str){
        char[] chars=str.toCharArray();
        boolean []bf=new boolean[chars.length];
        String s="";
        return Permuntation(chars,bf,s,str);
    }

    private static ArrayList<String> Permuntation(char[] chars, boolean[] bf, String s, String str) {
        if(str.length()==0)
            return arrayList;
        if(s.length()==chars.length){
            if(!arrayList.contains(s))
                arrayList.add(s);
            return arrayList;
        }
        for (int i=0;i<chars.length;i++){
            if(!bf[i]){
                bf[i]=true;
                s+=chars[i];
                Permuntation(chars,bf,s,str);
                s=s.substring(0,s.length()-1);
                bf[i]=false;
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        ArrayList<String> abc = Permuntation("abcd");
        for (String s : abc) {
            System.out.println(s);
        }
    }
}
