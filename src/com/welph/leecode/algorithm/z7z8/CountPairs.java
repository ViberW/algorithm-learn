package com.welph.leecode.algorithm.z7z8;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 题意：给一个数组，任意两个数字相乘，乘积结果可以被k整除的个数。
 * 例子：
 * 输入：nums = [1,2,3,4,5], k = 2 输出: 7
 * @since 2022/2/21 9:30
 */
public class CountPairs {

    public static void main(String[] args) {
        System.out.println(countPairs(new int[]{1, 2, 3, 4, 5}, 2));
    }

    /**
     * {@link com.welph.leecode.algorithm.thinking.ExpandEuclidean_8}的gcd方法
     */
    public static long countPairs(int[] nums, int k) {
        // 取得最大数字m
        int m = 0;
        for (int num : nums) {
            if (num > m) {
                m = num;
            }
        }
        // 创建2个数组
        int[] cnt = new int[m + 1];
        int[] s = new int[m + 1];
        //统计数字x出现的次数
        for (int x : nums) cnt[x] += 1;
        // 计算数字i的倍数的数量，比如2，那么2、4、6、8...所有数字的数量
        for (int i = 1; i <= m; i += 1)
            for (int j = i; j <= m; j += i) {
                s[i] += cnt[j];
            }
        int ans = 0;
        for (int i = 1; i <= m; i += 1) {
            // 计算k-24 i-18最大公约数是6，k剩余部分是4
            // k-4 i-6
            int x = k / gcd(k, i);
            if (x <= m) ans += cnt[i] * s[x];
        }
        // 注意上面gcd出来的x可能是i的约数，这样重复计数了i，这里需要将i去掉, 去除重复计算
        for (int i = 1; i <= m; i += 1) {
            if (i * i % k == 0) ans -= cnt[i];
        }
        return ans / 2;
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
