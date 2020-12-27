package mcw.test.leetcode.niuke;

/**
 * @author mcw 2020\3\13 0013-19:43
 *
 * 判断题目给出字符是不是回文，仅考虑字符串中的字母字符和数字字符，并且忽略大小写
 */
public class Test26 {

    public boolean isPalindrome(String s){
        int i=0;
        int j=s.length()-1;
        while (i<j){
            while(!isLetterOrNumber(s.charAt(i)) && i<j) i++;
            while(!isLetterOrNumber(s.charAt(j)) && i<j) j--;
            if(i<j && Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j)))
                return false;
            i++;
            j--;
        }
        return true;
    }

    private boolean isLetterOrNumber(char i) {
        if(i>='0' && i<='9' || i>='a' && i<= 'z' || i>='A' && i<='Z')
            return true;
        return false;
    }

    /*
      求最长回文子串
     */
    private static int index,len;

    public static String longestPalindrome(String s) {
        if(s.length()<2){
            return s;
        }
        for(int i=0;i<s.length()-1;i++){
            Palind(s,i,i);
            Palind(s,i,i+1);
        }
        return s.substring(index,index+len);
    }

    public static void Palind(String s,int l,int r){
        while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        if(len<r-l-1){
            index=l+1;
            len=r-l-1;
        }
    }

    public static void main(String[] args) {
//        boolean palindrome = new Test26().isPalindrome("A man,a plan,a canal:Panama");
//        System.out.println(palindrome);
        System.out.println(longestPalindrome("babad"));
    }
}
