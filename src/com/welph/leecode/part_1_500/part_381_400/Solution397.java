package com.welph.leecode.part_1_500.part_381_400;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正整数 n ，你可以做如下操作：
 * <p>
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * n 变为 1 所需的最小替换次数是多少？
 * <p>
 * 示例 1：
 * 输入：n = 8
 * 输出：3
 * 解释：8 -> 4 -> 2 -> 1
 * <p>
 * 示例 2：
 * 输入：n = 7
 * 输出：4
 * 解释：7 -> 8 -> 4 -> 2 -> 1
 * 或 7 -> 6 -> 3 -> 2 -> 1
 * <p>
 * 示例 3：
 * 输入：n = 4
 * 输出：2
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class Solution397 {

    public static void main(String[] args) {
        System.out.println(integerReplacement(8));
        System.out.println(integerReplacement(7));
        System.out.println(integerReplacement(3));
        System.out.println(integerReplacement(2147483647));
    }

    /**
     * 需要趋近于2的幂次方最好.
     * 最后一位是0直接右移，最后两位都是1，且该数不是3是，就选择+1，否则就选择－1，直到该数变成1
     */
    public static int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if ((n & 1) == 0) {
            int i = Integer.numberOfTrailingZeros(n);
            return integerReplacement(n >> i) + i;
        } else {
            if (n == Integer.MAX_VALUE) {
                return integerReplacement(n / 2 + 1) + 2;
            } else {
                if (n == 3) {
                    return integerReplacement(n - 1) + 1;
                } else if ((n & 2) == 2) {
                    return integerReplacement(n + 1) + 1; // 因为若是不+1, 则会多出来一步
                } else {
                    return integerReplacement(n - 1) + 1;
                }
            }
        }
    }

    /* 另一种写法 官方 */
    // 保证2的幂次
    public int integerReplacement2(int n) {
        int ans = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                ++ans;
                n /= 2;
            } else if (n % 4 == 1) {
                ans += 2;
                n /= 2;
            } else {
                if (n == 3) {
                    ans += 2;
                    n = 1;
                } else {
                    ans += 2;
                    n = n / 2 + 1;
                }
            }
        }
        return ans;
    }

    // 一种效率不高的写法, 也添加进来, 记忆化迭代
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int integerReplacement3(int n) {
        if (n == 1) {
            return 0;
        }
        if (!memo.containsKey(n)) {
            if (n % 2 == 0) {
                memo.put(n, 1 + integerReplacement(n / 2));
            } else {
                memo.put(n, 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)));
            }
        }
        return memo.get(n);
    }

}
