package mcw.test.offer;

/**
 * @author mcw 2020\1\14 0014-13:42
 *
 * 一只青蛙一次可以跳 1 级或 2 级台阶，求跳 n 级台阶有多少种跳法（f(n)=f(n-1)+f(n-2)）
 */
public class Test8 {

    public static int JumpFloor(int target){
        if(target==0)
            return 0;
        if(target==1)
            return 1;
        if(target==2)
            return 2;
        else
            return JumpFloor(target-1)+JumpFloor(target-2);
    }
}
