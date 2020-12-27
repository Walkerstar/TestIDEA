package mcw.test.offer;

/**
 * @author mcw 2020\1\19 0019-15:08
 *
 * 求任意非负整数区间中 1 出现的次数（从 1 到 n 中 1 出现的次数）
 */
public class Test31 {

    public static int SearchNumber1(int n){
        int res=0;
        for (int i=1;i<=n;i*=10){
            int a=n/i,b=n%i;
            res+=(a+8)/10*i+(a%10==1?(b+1):0);
        }
        return res;
    }

    public static int SearchNumber(int n){
        if(n<1)
            return 0;
        int cnt=0;
        int i;
        for(int j=1;j<=n;j++){
            i=j;
            while(i!=0){
                if(i%10==1) ++cnt;
                i=i/10;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int i = SearchNumber1(20);
        System.out.println(i);
    }
}
