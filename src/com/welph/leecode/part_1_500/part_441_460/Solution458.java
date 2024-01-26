package com.welph.leecode.part_1_500.part_441_460;

/**
 * 有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。
 * 为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，
 * 你只有minutesToTest 分钟时间来确定哪桶液体是有毒的。
 * <p>
 * 喂猪的规则如下：
 * 选择若干活猪进行喂养
 * 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
 * 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
 * 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
 * 重复这一过程，直到时间用完。
 * 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在 规定时间内判断哪个桶有毒所需的 最小 猪数。
 * <p>
 * 示例 1：
 * 输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
 * 输出：2
 * <p>
 * 提示：
 * 1 <= buckets <= 1000
 * 1 <=minutesToDie <=minutesToTest <= 100
 */
public class Solution458 {

    public static void main(String[] args) {
        // System.out.println(poorPigs(1000, 15, 60));
        System.out.println(poorPigs(4, 15, 15));
        System.out.println(poorPigs(4, 15, 30));
    }

    /**
     * {@link com.welph.leecode.algorithm.z7z8.MousePoison}
     * 首先判断每条猪最多能够喝多少桶(x)
     * --------------------------
     * (x+1)^m >= N ==>计算m条猪
     * --------------------------
     * 因为每条猪最多能够试(x+1) 因为x桶没毒,则毒在x桶之外
     * ==替代计算数值的n次方根 = log(n)/log(T) = N的开T次方根
     */
    // {@link MousePoison}
    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets-- == 1) {
            return 0;
        }
        int x = minutesToTest / minutesToDie + 1;
        int ret = 0;
        while (buckets > 0) {
            ret++;
            buckets /= x;
        }
        return ret;
    }

    /* 官方题解 (第二个题解有对上面方法原理的阐述) */

    // 动态规划
    public int poorPigs1(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) {
            return 0;
        }
        int[][] combinations = new int[buckets + 1][buckets + 1];
        combinations[0][0] = 1;
        // 需要测试的轮次
        int iterations = minutesToTest / minutesToDie;
        // f[i][j] 表示i只小猪测试j轮最多能够在多少桶中确定一桶有毒
        int[][] f = new int[buckets][iterations + 1];

        // 无论是0轮次或者0猪 都无法完成测试, 此时为了确定有一桶有毒, 则最多能测1桶
        for (int i = 0; i < buckets; i++) {
            f[i][0] = 1;
        }
        for (int j = 0; j <= iterations; j++) {
            f[0][j] = 1;
        }

        /*
         * f(i,j)
         * 当一轮测试后, 剩下k只猪和j-1次测试, 由于下一轮最大桶数为f(k,j-1)
         * 此时i只猪中能存活k只小猪的组合数: C(i,k) --数学的概率论
         * 那么 f(i,j) = sum(f(k,j-1) * C(i,k))
         */
        for (int i = 1; i < buckets; i++) {
            // C(i,k) 在存活0或者i头猪时, 组合数为1
            combinations[i][0] = 1;
            combinations[i][i] = 1;
            for (int j = 1; j < i; j++) {
                combinations[i][j] = combinations[i - 1][j - 1] + combinations[i - 1][j];
            }

            for (int j = 1; j <= iterations; j++) {
                for (int k = 0; k <= i; k++) {
                    f[i][j] += f[k][j - 1] * combinations[i][i - k];
                }
            }
            if (f[i][iterations] >= buckets) {
                return i;
            }
        }
        return 0;
    }

    // 数学
    public int poorPigs2(int buckets, int minutesToDie, int minutesToTest) {
        // 测试次数
        int k = minutesToTest / minutesToDie;
        int states = k + 1;
        /*
         * 根据{MousePoison} 这一题解, 可以理解为在有且仅有 1 轮的情况下,当前轮次针对m号药水是否选取
         * 这样就形成了 二进制 (0 1) 的选择情况
         * -----------------------------------------
         * 思维拓展:
         * 那么当要进行 k 次的选择时,
         * 那么在 第k次选择时, 也可以选择是否在当前进行选取 m 号水桶
         * 这样就形成了 k+1禁止 (0, 1 .... k)
         * 例如 现在存在n = 3头猪, 进行 k =3 轮次选择 4^3 = 64桶水
         * 那么
         * (0 0 0) 代表第1轮3只猪没喝第0桶水
         * (0 1 0) 代表第1轮第2头猪喝第1桶水
         * (0 0 1) 代表第1轮第3头猪喝第2桶水
         * (0 0 3) 代表第3轮第3头猪喝第...桶水
         * ...
         * (1 0 0) 代表第1轮第1头猪喝第 (K+1)的n 次方 => 16 桶水
         * (2 0 0) 代表第2轮第1头猪喝第 (K+1)的n 次方 => 32 桶水
         * (3 0 0) 代表第3轮第1头猪喝第 (K+1)的n 次方 => 48 桶水
         * ...
         * (2 3 1) 代表第 231进制: (2*16+ 3*4 + 1*1) = 45 桶水
         * ===========>第2轮第1头猪喝, 第3轮第2头猪喝, 第1轮第3头猪喝
         * 所以 一共是: (k+1) ^ n 桶水
         * --------------------------------------------
         * 当然了 我们可以这样思考
         * 第一轮次 取前y桶水 = n^2 每一桶水 为二进制
         * 第二轮次 本身(0,0,2)->(2,2,2) 转变前的水 让猪第二轮喝水,再加上有些水可以让猪在第一轮喝掉
         * .....
         */
        // 这就是buckets对states的log值
        int pigs = (int) Math.ceil(Math.log(buckets) / Math.log(states) - 1e-5);
        return pigs;
    }

}
