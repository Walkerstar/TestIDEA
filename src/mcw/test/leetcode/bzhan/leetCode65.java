package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\5 0005-15:12
 * Valid Number
 * validate if a given string is numeric.
 */
public class leetCode65 {
    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        //checking leading space
        int i = 0;
        int n = s.length();
        while (i < n && Character.isWhitespace(s.charAt(i))) i++;
        //check +/- sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
        //check digits until
        boolean isDigits = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            i++;
            isDigits = true;
        }
        //check post . parts
        if (i < n && s.charAt(i) == '.') {
            i++;
            //isDigits=false ; // 3. is valid
            while (i < n && Character.isDigit(s.charAt(i))) {
                isDigits = true;
                i++;
            }
        }
        if (i < n && s.charAt(i) == 'e' && isDigits) {
            i++;
            isDigits = false;
            //check +/- sign
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isDigits = true;
            }
        }

        //handle trailing space
        while (i < n && Character.isWhitespace(s.charAt(i))) i++;
        return isDigits && i == s.length();
    }
}
