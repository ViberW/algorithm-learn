package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小S正在处理一个数组，目标是找到满足特定条件的四元组数量。
 * 给定一个长度为n的数组a，他需要计算有多少四元组(i,j,k,l)满足：
 * a_i + a_j = a_k ⊕ a_l，其中 ⊕ 表示按位异或，并且满足索引的条件 i < j < k < l。
 *
 * 由于答案可能非常大，所以你需要对 10^9 + 7 取模后的结果。
 */
public class FourNumEquations {

    //不考虑无限遍历的情况.
    public static int solution(int n, int[] a) {
        int mod = 1000000007;
        long result = 0;
        // a_k ⊕ a_l 要获取a_l = (a_i + a_j) ⊕ a_k
        for (int i = n - 4; i >= 0; i--) {
            for (int j = n - 3; j > i; j--) {
                int total = a[i] + a[j];
                for (int k = n - 2; k > j; k--) {
                    int expect = total ^ a[k];
                    for (int l = n - 1; l > k; l--) {
                        if (expect == a[l]) {
                            result++;
                        }
                    }
                }
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{2, 3, 1, 5, 4}) == 1);
        System.out.println(solution(6, new int[]{1, 2, 3, 4, 5, 6}) == 1);
        System.out.println(solution(4, new int[]{4, 1, 3, 2}) == 0);
    }
}
