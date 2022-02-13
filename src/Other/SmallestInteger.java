package Other;

/**
 * 你将获得一个正整数N。你的任务是查找不包含两个连续数字的大于N的最小整数。
 * N 是范围 [1..1,000,000,000]
 * @author MCW 2022/2/13
 */
public class SmallestInteger {

    public int solution(int N) {
        int ans = excludeMuti(N);
        if (ans == N) {
            //如果高位未变化，则 +1 后再判断是否有重复
            ans++;
        }
        while (true) {
            if (!containsMuti(ans)) {
                return ans;
            }
            ans++;
        }
    }


    private boolean containsMuti(int n) {
        //判断是否有重复的数
        String nStr = n + "";
        for (int i = 1; i < nStr.length(); i++) {
            if (nStr.charAt(i) == nStr.charAt(i - 1)) {
                return true;
            }
        }
        return false;
    }

    private int excludeMuti(int n) {
        //如果高位有重复，直接把高位 +1，并递归判断
        String newStr = n + "";
        if (newStr.length() < 3) {
            return n;
        }
        String left = newStr;
        for (int i = 1; i < left.length(); i++) {
            if (left.charAt(i) == left.charAt(i - 1)) {
                int currentValue = Integer.parseInt(left.substring(0, i + 1));
                int updateValue = currentValue + 1;
                //高位+1后，尾数都变为00
                String newNumString = updateValue + "";

                //修改次高位
                for (int j = 0; j < left.substring(i + 1).length(); j++) {
                    newNumString = newNumString + "0";
                }
                //修改尾数
                //+1后还可能重复，递归处理
                int result = excludeMuti(Integer.parseInt(newNumString));
                return result;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        SmallestInteger s = new SmallestInteger();
        System.out.println(s.solution(1444));
    }
}
