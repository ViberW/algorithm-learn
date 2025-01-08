package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小E在一个游戏中遇到了n个按顺序出现的怪物。每个怪物都有其特定的血量hi和攻击力ai。
 * 小E的初始血量为H，攻击力为A。游戏规则如下：
 *
 * 1.小E可以击败血量和攻击力都小于她当前属性的怪物
 * 2.对于每只怪物，小E可以选择与它战斗或者跳过这只怪物
 * 3.为了保持战斗节奏，要求击败的怪物序列中，后一个怪物的血量和攻击力都必须严格大于前一个怪物
 *
 * 小E想知道，她最多能击败多少怪物。
 *
 * **输入 **
 * n：怪物的数量
 * H：小E的血量
 * A：小E的攻击力
 * h[i]：第i个怪物的血量
 * a[i]：第i个怪物的攻击力
 * **输出 **
 *
 * 返回小E最多能击败的怪物数量
 * **约束条件 **
 *
 * 1 < n < 100
 * 1 < H,A,h[i],a[i] < 1000
 */
public class DefeatMaxCountMonster {

    //{@link Solution300}
    public static int solution(int n, int H, int A, int[] h, int[] a) {
        int[] dp = new int[n];
        int max = dp[0] = 1;  //表示以num[i]作为最长上升子序列的结尾的长度
        for (int i = 1; i < n; i++) {
            if (h[i] >= H || a[i] >= A) {
                continue;
            }
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (h[j] < h[i] && a[j] < a[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, 4, 5, new int[]{1, 2, 3},
                new int[]{3, 2, 1}) == 1);
        System.out.println(solution(5, 10, 10, new int[]{6, 9, 12, 4, 7},
                new int[]{8, 9, 10, 2, 5}) == 2);
        System.out.println(solution(4, 20, 25, new int[]{10, 15, 18, 22},
                new int[]{12, 18, 20, 26}) == 3);
        System.out.println(solution(13, 17, 14,
                new int[]{2, 3, 12, 13, 7, 8, 5, 15, 1, 12, 17, 14, 4},
                new int[]{13, 13, 8, 15, 2, 5, 7, 8, 13, 7, 6, 8, 1}));//4
    }
}
