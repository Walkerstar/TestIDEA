package mcw.test.offer;

/**
 * @author mcw 2020\1\12 0012-13:55
 */
public class Test2 {

    public static String replaceSpace(StringBuffer str){
        int spacenum=0;
        for (int i=0;i<str.length();i++){
            if(str.charAt(i)==' ')
                spacenum++;
        }
        int indexold=str.length()-1; //替换前的str下标
        int newlength=str.length()+spacenum*2;//计算空格转换成%20后的str长度
        int indexnew=newlength-1; //indexnew为把空格替换后的str下标
        str.setLength(newlength); //扩张，防止下标越界
        for(;indexold>=0 && indexold<newlength;--indexold) {
            if(str.charAt(indexold)==' '){
                str.setCharAt(indexnew--,'0');
                str.setCharAt(indexnew--,'2');
                str.setCharAt(indexnew--,'%');
            }else{
                str.setCharAt(indexnew--,str.charAt(indexold));
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        StringBuffer str=new StringBuffer("we are happy");
        str.append("? are u sure");
        System.out.println(replaceSpace(str));
    }
}
