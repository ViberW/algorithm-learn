package com.welph.leecode.algorithm.marscode;

/**
 * 小F正在研究一个数组，并想要计算出其中的连续子数组的某种特性。
 *
 * 给定一个整数数组，你需要编写一个函数来返回乘积末尾零的数量大于等于 x 的连续子数组的数量。
 *
 * 由于答案可能非常大，你需要将结果对 10^9 + 7 取模后再返回。
 */
public class SubArrayWithZeroEnd {

    public static int solution(int[] a, int x) {
        //乘积末尾的0, 则需要考虑0的个数, 2的个数, 5的个数
        int mod = 1000000007;
        //末尾0的个数,要大于等于2的数量,
        int[][] counts = new int[a.length + 1][3];
        long result = 0;
        for (int i = 1; i <= a.length; i++) {
            int val = a[i - 1];
            int _count2 = Integer.numberOfTrailingZeros(val);
            val >>= _count2;
            int _count5 = 0;
            while (val % 5 == 0) {
                _count5++;
                val /= 5;
            }
            _count2 += counts[i - 1][1];
            _count5 += counts[i - 1][2];
            int min = Math.min(_count5, _count2);
            counts[i][0] = counts[i - 1][0] + min;
            counts[i][1] = Math.abs(_count2 - min);
            counts[i][2] = Math.abs(_count5 - min);
            if (counts[i][0] >= x) {
                for (int j = i - 1; j >= 0; j--) {
                    if (counts[i][0] - counts[j][0] >= x
                            && counts[i][1] >= counts[j][1]
                            && counts[i][2] >= counts[j][2]) { //考虑2和5的数量
                        //说明从此处开始的子数组是符合的.
                        result = (result + j + 1) % mod;
                        break;
                    }
                }
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 2, 3, 50, 4}, 2) == 6);
        System.out.println(solution(new int[]{10, 5, 2, 1}, 3) == 0);
        System.out.println(solution(new int[]{25, 4, 8}, 1) == 2);
    }

}
