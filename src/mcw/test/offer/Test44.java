package mcw.test.offer;

/**
 * @author mcw 2020\1\22 0022-20:23
 *
 * 将句子单词顺序翻转
 */
public class Test44 {
    public static String ReverseSentence(String str){
        if(str==null || str.trim().getClass().equals(""))
            return str;
        char[] c=str.toCharArray();
        reverse(c,0,str.length()-1); //翻转整个句子

        //翻转句子中的每个单词
        int begin=0;
        int end=0;
        while(begin!=c.length){
            //若起始符为空格，则begin和end都自加
            if(c[begin]==' '){
                begin++;
                end++;
            }else if(c[end]== ' '){
                //遍历到终止字符为空格，就可进行翻转
                reverse(c,begin,--end);
                begin=++end;
            }else {
                //没有遍历到空格或者遍历结果，则单独对end自减
                end++;
            }
        }
        return String.valueOf(c);
    }

    public static void reverse(char[] chars, int low, int high) {
        char temp;
        while (low < high) {
            temp = chars[low];
            chars[low] = chars[high];
            chars[high] = temp;
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        System.out.println(ReverseSentence("student! a am I "));
    }
}
