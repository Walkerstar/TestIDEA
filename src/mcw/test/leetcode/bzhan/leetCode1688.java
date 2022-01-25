package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 n ，表示比赛中的队伍数。比赛遵循一种独特的赛制：<br/>
 *
 * <li>如果当前队伍数是 偶数 ，那么每支队伍都会与另一支队伍配对。总共进行 n / 2 场比赛，且产生 n / 2 支队伍进入下一轮。</li>
 * <li>如果当前队伍数为 奇数 ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 (n - 1) / 2 场比赛，且产生 (n - 1) / 2 + 1 支队伍进入下一轮。</li>
 * 返回在比赛中进行的配对次数，直到决出获胜队伍为止。
 *
 * @author mcw 2022/1/25 10:50
 */
public class leetCode1688 {
    public int numberOfMatches(int n) {
        int ans = 0;
        while (n > 1) {
            if (n % 2 == 0) {
                ans += n / 2;
                n /= 2;
            } else {
                ans += (n - 1) / 2;
                n = (n - 1) / 2 + -1;
            }
        }
        return ans;
    }

    /**
     * 在每一场比赛中，输的队伍无法晋级，且不会再参加后续的比赛。
     * 由于最后只决出一个获胜队伍，因此就有 n−1 个无法晋级的队伍，也就是会有 n−1 场比赛。
     */
    public int numberOfMatches2(int n){
       return n-1;
    }

}
