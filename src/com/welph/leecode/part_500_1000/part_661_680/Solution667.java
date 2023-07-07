package com.welph.leecode.part_500_1000.part_661_680;

import java.util.Arrays;

/**
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 * <p>
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|]
 * 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 * <p>
 * 示例 1：
 * 输入：n = 3, k = 1
 * 输出：[1, 2, 3]
 * 解释：[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
 * <p>
 * 示例 2：
 * 输入：n = 3, k = 2
 * 输出：[1, 3, 2]
 * 解释：[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
 * <p>
 * 提示：
 * 1 <= k < n <= 10^4
 */
public class Solution667 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructArray(3, 2)));
    }

    /**
     * {@link com.welph.leecode.part_500_1000.part_521_540.Solution526}
     * 下标从[0, k]中，偶数下标填充[1,2,3…]，奇数下标填充[k + 1, k, k - 1…]，后面[k + 2, n ]都是顺序填充
     * //前面k, k-1,k-2,k-3.... 1,1,1,1,1,1 处理1有多个 剩下的都是k到2的填充
     */
    public static int[] constructArray(int n, int k) {
        int[] ret = new int[n];
        int numK = k + 1;
        int num = 1;
        for (int i = 0; i <= k; i += 2) {
            ret[i] = num++;
        }
        for (int i = 1; i <= k; i += 2) {
            ret[i] = numK--;
        }
        for (int i = k + 1; i < n; i++) {
            ret[i] = i + 1;
        }
        return ret;
    }
}
