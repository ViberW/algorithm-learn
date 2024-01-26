package com.welph.leecode.part_1_500.part_221_240;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * <p>
 * 示例 1：
 * 输入：n = 13
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 2 * 10^9
 */
public class Solution233 {

    public static void main(String[] args) {
        System.out.println(countDigitOne(13));
        System.out.println(countDigitOne(21));
        System.out.println(countDigitOne(10));
        System.out.println(countDigitOne(1410065408));
    }

    public static int countDigitOne(int n) {
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(1, 0);
        int i = 1;
        int total = 0;
        int limit = Integer.MAX_VALUE / 2;
        for (; i <= n; i *= 10) {
            counts.put(i * 10, 10 * counts.get(i) + i); // 10的每段的1 加上 1开头的数值(对应10的位数)
            /**
             * 余数 : 判断首位为1的数值
             * 除数 : 判断当前的能够乘以多少个上一次的大小
             */
            int a = n % i;
            int b = (n / i) % 10;
            if (b > 1) {
                total += i; // 这里加的是 1的所有的 总共i个, 下面也是同样的逻辑, 只不多等于1, 所以只能取a+1 (上面是考虑首页的1的情况)
            } else if (b == 1) {
                total += a + 1;
            }
            total += b * counts.get(i); // 这里是针对1,2,3,4...9 的余数有多少个1 不考虑首位
            if (i * 2 > limit) {
                break;
            }
        }
        return total;
    }

    /* 官方题解 */
    public int countDigitOne2(int n) {
        // mulk 表示 10^k
        // 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用 10^k）
        // 但为了让代码看起来更加直观，这里保留了 k
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            // 其实就是计算mulk的位数可能 + (n % (mulk * 10) - mulk + 1) || 0 || mulk
            // ............................... mulk/10 ~ 2*mulk/10 <mulk/10 >2*mulk/10
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }

}
