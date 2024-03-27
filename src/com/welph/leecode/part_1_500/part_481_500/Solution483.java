
package com.welph.leecode.part_1_500.part_481_500;

/**
 * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小 好进制 。
 * 如果 n 的 k(k>=2)进制数的所有数位全为1，则称k(k>=2)是 n 的一个好进制。
 * <p>
 * 示例 1：
 * 输入：n = "13"
 * 输出："3"
 * 解释：13 的 3 进制是 111。
 * <p>
 * 示例 2：
 * 输入：n = "4681"
 * 输出："8"
 * 解释：4681 的 8 进制是 11111。
 * <p>
 * 示例 3：
 * 输入：n = "1000000000000000000"
 * 输出："999999999999999999"
 * 解释：1000000000000000000 的 999999999999999999 进制是 11。
 * <p>
 * 提示：
 * n 的取值范围是[3, 10^18]
 * n 没有前导 0
 */
public class Solution483 {

    public static void main(String[] args) {
        System.out.println(smallestGoodBase("13"));
        System.out.println(smallestGoodBase("4681"));
        System.out.println(smallestGoodBase("1000000000000000000"));
    }

    /**
     * n的k进制, 所有位杜伟1
     * k^x-1+...+ 1*k^0 = n
     * 返回最小的k
     * ---
     * 等比数列的求和公式: n = ((k^x) - 1) / (k - 1) => k^(x)=k*n-n+1 < k*n
     * => k^(x) < k*n
     * => k^(x-1) < n //两边除k
     * =>(x-1)<log k(n)
     * --------------------------------------
     * n =k^x-1+...+ 1*k^0 > k^x-1
     * <a herf=
     * "https://leetcode.cn/problems/smallest-good-base/solutions/832832/zui-xiao-hao-jin-zhi-by-leetcode-solutio-csqn/"
     * />
     * 
     * k< pow(n,1/(x-1))<k+1
     */
    public static String smallestGoodBase(String n) {
        long val = Long.parseLong(n);
        long maxX = (long) (Math.log(val + 1) / Math.log(2));
        long sum;
        for (long i = maxX; i > 2; i--) {
            // 每种长度进行处理.
            long l = 2;
            // 选择一个x, 找到合适的sqrt(n,x-1)
            long r = (long) Math.pow(val, 1.0 / (i - 1)) + 1;// 找出最接近的K的值, 根号(x-1)的val, 向上取整
            while (l < r) {
                long k = l + (r - l) / 2; // 在当前返回寻找匹配的k值
                sum = 0;
                for (long j = 0; j < i; j++) {
                    sum = sum * k + 1;
                }
                if (sum == val) {
                    return String.valueOf(k);
                } else if (sum > val) {
                    r = k;
                } else {
                    l = k + 1;
                }
            }
        }
        return String.valueOf(val - 1);
    }
}
