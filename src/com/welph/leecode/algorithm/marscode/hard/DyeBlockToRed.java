package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小R有一排长度为 n 的格子，每个格子从左到右编号为 1 到 n。
 * 起初，部分格子已经被染成了红色，其他格子则没有颜色。
 *
 * 红色格子的状态由一个长度为 n 的字符串 s 描述，其中 s[i] = 1 表示第 i 个格子是红色的，而 s[i] = 0 表示该格子没有颜色。
 *
 * 小R希望通过以下两种操作将所有格子都染成红色：
 *
 * 如果第 i 个格子是红色的，且 i + 1 ≤ n，则可以将第 i + 1 个没有颜色的格子染成红色。
 * 如果第 i 个格子是红色的，且 i - 1 ≥ 1，则可以将第 i - 1 个没有颜色的格子染成红色。
 * 请你帮小R计算出，存在多少种不同的染色顺序可以使所有格子最终都被染成红色，并输出答案对 10^9 + 7 取模后的结果。
 */
public class DyeBlockToRed {

    public static int solution(int n, String s) {
        // 两种操作: 当前红色格子   下一个格子染红或上一个格子染红 (无颜色的)
        //只需要考量两个1之间的0个数
        //若两个1之间有m个0, 则有 2^(m-1) 种选择方法. 注意这里只是染红, 不用考虑是i-1或i+1导致的i变红的不同场景
        //对于前后两端的0,则有m种方法
        int mod = 1000000007;
        long result = 1L;
        long orderResult = 1L;
        int total = 0;
        int l = 0, r = 0;
        while (r <= n) {
            if (r == n || s.charAt(r) == '1') {
                //当前范围为0
                if (l < r) {
                    int count = r - l;
                    if (l > 0 && r < n) {
                        orderResult = (orderResult * (1L << (count - 1))) % mod;
                    }
                    //相当于是C(total, count); 在total中选择count个位 = total!/(count! *(total-count)!)
                    long current = 1L;
                    long current2 = 1L;//count!
                    for (int i = 1; i <= count; i++) {
                        current = (current * (total + i)) % mod;
                        current2 = (current2 * i) % mod;
                    }
                    result = (result * (current / current2)) % mod;
                    total += count;
                }
                l = r + 1;
            }
            r++;
        }
        result = (result * orderResult) % mod;
        return (int) result;
    }

    public static void main(String[] args) {
//        System.out.println(solution(5, "00101") == 3);
//        System.out.println(solution(6, "100001") == 8);
//        System.out.println(solution(7, "0001000") == 20);
        System.out.println(solution(14, "10101011100101"));
    }

}
