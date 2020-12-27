package mcw.test.offer;

/**
 * @author mcw 2020\1\20 0020-19:41
 *
 * 在一个字符串（0<=字符串长度<=10000,全都由字母组成）中找到第一个只出现一次的字符，
 * 并返回它的位置，如果没有则返回-1（区分大小写）
 */
public class Test34 {

    public static int FirstNotRepeatingChar(String str){
        if(str.length()==0)
            return -1;
        int[] words=new int[58];  //字母下标index=int(word)-65
        for (int i=0;i<str.length();i++){
            words[(int)str.charAt(i)-65]+=1;
        }
        for (int i=0;i<str.length();i++){
            if(words[(int)str.charAt(i)-65]==1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(FirstNotRepeatingChar("aadsdafghfh"));
    }
}
