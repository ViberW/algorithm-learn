package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小包非常喜欢吃甜点,他收到了一次性送来的$NS个甜点,每个个甜点都有一个对应的喜爱值。
 * 但是这还不够!小包让小哥连续送来了$M$次相同的SN$个甜点,并将这些甜点首尾相接排成一排。
 *
 * 现在,小包面前有$(N\times M)$个排成一排的甜点,小包希望从中选择一段连续的甜点,使得这段甜点的总喜爱值最大化。
 *
 * 注意:尽管小包喜欢甜食,但有些甜点可能不合口味,导致教其喜爱值为负数。小包至少要选择一个甜点来满足他对甜点的贪心。
 */
public class GreedySweet {

    public static int solution(int N, int M, int[] data) {
        int result = data[0]; //考虑到有负数的情况
        int sum = 0;
        //看从1开始能到的最大位置.
        int len = M * N;
        int left = -1;
        int limitMax = 0;
        int overMax = 0;
        for (int i = 0; i < len; i++) {
            sum += data[i % N];
            result = Math.max(result, sum);
            overMax = Math.max(overMax, sum); //不能使用result. 可能中间段会超过单纯的overMax
            if (i < N) {
                limitMax = Math.max(limitMax, sum);
            }
            if (sum < 0) { //需要考虑负数
                if (i % N <= left) {
                    break;
                }
                left = i;
                sum = 0;
                limitMax = 0;
                overMax = 0;
            } else if (left == i % N) {
                //这里有两种情况,主要聚焦到最后2段N中, 需要比较(跨2段的最大值)和(不超过两端的最值+sum) 两者之间的最大值
                //[......xxx][xxx...] 和[xxx][xxxyyyy]
                // 跨两端最大值: sum * (M - 2) + overMax
                // 不跨段: sum * (M - 2) + sum + limitMax
                result = Math.max(result, sum * (M - 2) + Math.max(overMax, sum + limitMax)); //找到这一段的最大值.
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Add your test cases here
        System.out.println(solution(4, 6, new int[]{-4, -16, -5, -10}) == -4);
        System.out.println(solution(5, 1, new int[]{1, 3, -9, 2, 4}) == 6);
        System.out.println(solution(5, 3, new int[]{1, 3, -9, 2, 4}) == 11);
        System.out.println(solution(13, 16, new int[]{12, 14, -20, -23, 13, 15, 2, 11, 20, 0, -21, -24, 17}));
    }

}
