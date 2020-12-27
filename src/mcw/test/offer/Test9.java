package mcw.test.offer;

/**
 * @author mcw 2020\1\14 0014-13:48
 *
 * 一只青蛙一次可以跳 1 级或 2 级或 n 级台阶，求跳 n 级台阶有多少种跳法（斐波拉数列变形 结果：f(n)=2*f(n-1)）
 */
public class Test9 {

    public static int JumpFloor(int target){
        if(target<=0)
            return 0;
        if(target==1)
            return 1;
        else
            return JumpFloor(target-1)*2;
    }

    public static int JumpFloor1(int target){
        if(target<=0)
            return 0;
        if(target==1)
            return 1;
        int a=1,b=2;
        for (int i=2;i<target;i++){
            b=2*a;
            a=b;
        }
        return b;
    }

}
