package com.welph.leecode.part_1_500.part_421_440;

import com.sun.corba.se.impl.oa.toa.TOA;

/**
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 * 注意：1 ≤ k ≤ n ≤ 10^9。
 * <p>
 * 示例 :
 * 输入:
 * n: 13   k: 2
 * 输出:
 * 10
 * <p>
 * 解释:
 * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 */
public class Solution440 {

    public static void main(String[] args) {
//        System.out.println(findKthNumber(13, 2));//10
//        System.out.println(findKthNumber(13, 8));//4
//        System.out.println(findKthNumber(681692778, 351251360));//416126219
        System.out.println("===============================");
//        System.out.println(findKthNumber1(13, 2));
        System.out.println(findKthNumber1(100, 54));
//        System.out.println(findKthNumber1(681692778, 351251360));
//        System.out.println(Integer.MAX_VALUE);//2147483647
//        System.out.println(Long.toString((long) Math.pow(10, 9)));//1000000000
    }

    /**
     * 字典树?+回溯
     * ----
     * 根据k, 剩余节点 尽量精简剩余能够达到的节点名
     * //每次下降一个级别, 就能看到相关的
     * 10^0 + 10^1 + 10^2+...+10^9
     * <p>
     * -------------------
     * todo 来自 官方的题解
     */
    public static int findKthNumber1(int n, int k) {
        //寻找到k最接近的10^m = m次方
        //需要考虑(mod k*10) 的 与n的最大值是否超过了
        int cur = 1;
        --k;//初始化为cur = 1，k需要自减1
        while (k > 0) {
            long step = 0, first = cur, last = cur + 1;
            //统计这棵子树下所有节点数（step）
            while (first <= n) {
                //n+1 因为差值最大n+1
                step += Math.min((long) n + 1, last) - first;//不能超过n的值，并不是所有节点都有十个子节点
                first *= 10;
                last *= 10;
            }
            if (step <= k) {//不在子树中
                ++cur;
                k -= step;
            } else {//在子树中，进入子树
                cur *= 10;
                --k;
            }
        }
        return cur;
    }


    /**
     * {@link com.welph.leecode.part_1_500.part_381_400.Solution386}
     * ----------------
     * ........... 超时了.
     */
    public static int findKthNumber(int n, int k) {
        int[] len = new int[1];
        return (int) lexical(0, len, n, k);
    }

    static long lexical(long n, int[] len, int m, int k) {
        n *= 10;
        long v;
        for (long i = n == 0 ? 1 : 0; i < 10; i++) {
            v = n + i;
            if (v > m) {
                return -1;
            }
            if (++len[0] == k) {
                return v;
            }
            long lexical = lexical(v, len, m, k);
            if (lexical > 0) {
                return lexical;
            }
        }
        return -1;
    }
}
