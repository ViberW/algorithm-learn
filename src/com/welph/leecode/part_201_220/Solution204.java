package com.welph.leecode.part_201_220;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 * <p>
 * 0 <= n <= 5 * 106
 */
public class Solution204 {

    public static void main(String[] args) {
        System.out.println(countPrimes(100));
        System.out.println(countPrimes1(100));
    }

    //todo 这里还有个[线性筛] .....

    /**
     * 厄拉多塞筛法，简称埃氏筛。 --666
     * 如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。
     * <p>
     * 我们设 isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，
     * 否则为 0。从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），
     * 即 0，这样在运行结束的时候我们即能知道质数的个数。
     * <p>
     * 对于一个质数 x，如果按上文说的我们从 2x, 3x 开始标记其实是冗余的，
     * 应该直接从x*x 开始标记，因为 2x,3x,… 这些数一定在 x*x 之前就被其他数的倍数标记过了
     */
    public static int countPrimes1(int n) {
        int[] isPrime = new int[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 0) {
                count++;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 1;
                    }
                }
            }
        }
        return count;
    }


    //质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数
    //todo -_- 暴力法 就是针对每个数据进行从2~n-1是否有能够整除的.  --超出时间限制
    //todo  这个质数其他看题解 这很数学!
    //它总是等于 6x-1 或者 6x+1
    public static int countPrimes(int n) {
        int count = 0;
        if (n > 2) {
            count++;
        }
        if (n > 3) {
            count++;
        }
        int step = (n + 1) / 6;
        for (int i = 1; i <= step; i++) {
            if (isPrime1(n, 6 * i - 1)) {
                count++;
            }
            if (isPrime1(n, 6 * i + 1)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 如果 y 是 x 的因数，那么 x/y也必然是 x 的因数，因此我们只要校验 y 或者 x/y
     * 即可。而如果我们每次选择校验两者中的较小数，则不难发现较小数一定落在 [2,sqrt(x)] 的区间中
     */
    private static boolean isPrime1(int n, int v) {
        if (v >= n) {
            return false;
        }
        for (int i = 2; i * i <= v; i++) {
            if (v % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPrime(int n, int v) {
        if (v >= n) {
            return false;
        }
        for (int i = 2; i < v - 1; i++) {
            if (v % i == 0) {
                return false;
            }
        }
        return true;
    }
}
