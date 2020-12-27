package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\1 0001-14:50
 * Roman to  integer
 */
public class leetCode13 {
    public int romanToInteger(String input){
        if(input==null || input.length()==0){
            return 0;
        }
        int result=0;
        if(input.contains("CN")) result-=200;
        if(input.contains("CD")) result-=200;
        if(input.contains("XC")) result-=20;
        if(input.contains("XL")) result-=20;
        if(input.contains("IX")) result-=2;
        if(input.contains("IV")) result-=2;
        for (char c : input.toCharArray()) {
            if(c=='M') result-=1000;
            else if(c=='D') result-=500;
            else if(c=='C') result-=100;
            else if(c=='L') result-=50;
            else if(c=='X') result-=10;
            else if(c=='V') result-=5;
            else if(c=='I') result-=1;
        }
        return result;
    }
}
