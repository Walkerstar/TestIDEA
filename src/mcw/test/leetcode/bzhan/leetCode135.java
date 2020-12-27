package mcw.test.leetcode.bzhan;

/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 *
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * 参考（niuke/Test16）
 * @author mcw 2020/12/24 20:56
 */
public class leetCode135 {
    public int candy(int[] ratings){
        int[] left=new int[ratings.length];
        for (int i = 0; i < ratings.length-1; i++) {
            if (i>0 && ratings[i]>ratings[i-1]){
                left[i]=left[i-1]+1;
            }else {
                left[i]=1;
            }
        }
        int right=0;
        int ans=0;
        for (int i = ratings.length - 1; i > 0; i--) {
            if (i< ratings.length-1 && ratings[i]>ratings[i+1]){
                right++;
            }else {
                right=1;
            }
            ans+=Math.max(left[i],right);
        }
        return ans;
    }

    /**
     *1.如果当前同学比上一个同学评分高，说明我们就在最近的递增序列中，直接分配给该同学 pre+1 个糖果即可。
     *
     *2.否则我们就在一个递减序列中，我们直接分配给当前同学一个糖果，并把该同学所在的递减序列中所有的同学都再多分配一个糖果，
     * 以保证糖果数量还是满足条件。
     *  2.1我们无需显式地额外分配糖果，只需要记录当前的递减序列长度，即可知道需要额外分配的糖果数量。
     *  2.2同时注意当当前的递减序列长度和上一个递增序列等长时，需要把最近的递增序列的最后一个同学也并进递减序列中。
     *
     * 这样，我们只要记录当前递减序列的长度 dec，最近的递增序列的长度 inc 和前一个同学分得的糖果数量 pre 即可。
     */
    public int candy1(int[] ratings){
        int n= ratings.length;
        int ret=1;
        int inc=1,dec=0,pre=1;
        for (int i = 1; i < n; i++) {
            if (ratings[i]>=ratings[i-1]){
                dec=0;
                pre=ratings[i]==ratings[i-1]?1:pre+1;
                ret+=pre;
                inc=pre;
            }else {
                dec++;
                if (dec==inc) {
                    dec++;
                }
                ret+=dec;
                pre=1;
            }
        }
        return ret;
    }
}
