package com.welph.leecode.algorithm.marscode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 小U 和 小R 各自拥有一个长度相等的二进制字符串 A 和 B。现在，他们想要将这两个字符串修改成相同的字符串。每次修改可以选择以下两种操作：
 * <p>
 * 1. 交换同一个字符串中的任意两个字符，交换操作的成本为它们索引之差的绝对值 |i - j|。
 * 2. 对某个字符进行取反操作，取反的成本为 2。
 * 小U 和 小R 想知道，将字符串 A 和 B 修改为相同字符串的最小总成本是多少？
 */
public class ConvertStrSame {
    public static int solution(String str1, String str2) {
        int n = str1.length();
        List<Integer> ones = new ArrayList<>();
        List<Integer> zeros = new ArrayList<>();

        // 构建差异数组
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (str1.charAt(i) == '1') {
                    ones.add(i);
                } else {
                    zeros.add(i);
                }
            }
        }

        int onesCount = ones.size();
        int zerosCount = zeros.size();

        //处理到第i个1和第j个0时的最小成本。
        //问题变成: 如何通过最少的操作使这两个列表变为空（即消除所有差异）
        int[][] dp = new int[onesCount + 1][zerosCount + 1];

        // 初始状态
        for (int i = 0; i <= onesCount; i++) {
            for (int j = 0; j <= zerosCount; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;

        for (int i = 0; i <= onesCount; i++) {
            for (int j = 0; j <= zerosCount; j++) {
                if (i < onesCount && j < zerosCount) {
                    // 交换操作, 不用管是否在4的范围, 因为始终能够在后续的取反或交换找到最佳值
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + Math.abs(ones.get(i) - zeros.get(j)));
                }
                if (i < onesCount) {
                    // 取反操作 (ones)
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 2); // 当前i处直接使用2
                }
                if (j < zerosCount) {
                    // 取反操作 (zeros)
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 2); // 当前j处直接使用2
                }
            }
        }

        return dp[onesCount][zerosCount];
    }

    //这个题解有问题, 因为没有考虑到 110  的情况, 按照这个方法思想是4 , 但最佳是第二号和三号互换, 最佳为3
    public static int solution1(String str1, String str2) {
        // Edit your code here
        int length = str1.length();
        int[] next = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (str1.charAt(i) == '0') {
                    next[i] = -1; //为0
                } else {
                    next[i] = 1; //为1
                }
            }
        }
        int totalCost = 0;
        for (int i = 0; i < length; i++) {
            if (next[i] != 0) {
                int originI = i;
                int step1 = 2;
                int step2 = 2;
                int expect = -next[originI];
                for (int nextI = i + 1; nextI < length; nextI++) {
                    if (next[nextI] == expect) {
                        if (nextI - originI < 4) {
                            int tmp = step1;
                            step1 = step2 - 2 + nextI - originI;
                            step2 = Math.min(tmp, step2) + 2;
                            expect = -next[nextI];
                            next[originI] = 0;  //剪枝
                            originI = nextI;
                        } else {
                            break;
                        }
                    }
                }
                next[originI] = 0;
                totalCost += Math.min(step1, step2);
            }
        }
        return totalCost;
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution("1010", "0001"));
        System.out.println(solution("10001", "10000") == 2);
        System.out.println(solution("100100", "100001") == 2);
        System.out.println(solution("1010", "0011") == 3);
        System.out.println(solution("1100", "0011") == 4);
        System.out.println(solution("1101000000001101", "0010110111100010"));
    }
}
