package mcw.test.offer;

/**
 * @author mcw 2020\1\24 0024-20:40
 * <p>
 * 给定一个数组A[0,1,.....,n-1],请构建一个数组B[0,1,......,n-1]
 * 其中数组B的元素B[i]=A[0]*A[1]*A[2]*...*A[i-1]*A[i+1]*...*A[n-1]，不能用除法
 */
public class Test51 {

    public static int[] muitiplky(int[] A) {
        int N = A.length;
        int[] ans = new int[N];

        int[] pre = new int[N];
        int[] post = new int[N];

        pre[0] = A[0];
        post[N - 1] = A[N - 1];
        for (int i = 1; i < N; i++) {
            pre[i] = pre[i - 1] * A[i];
        }
        for (int i = N - 2; i >= 0; --i) {
            post[i] = post[i + 1] * A[i];
        }

        ans[0] = post[1];
        ans[N - 1] = pre[N - 2];
        for (int i = 1; i < N - 1; ++i) {
            ans[i] = pre[i - 1] * post[i + 1];
        }
        return ans;

    }
}
