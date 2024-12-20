package com.welph.leecode.algorithm.marscode;

/**
 * 小M拥有一个长度为n的数组，该数组会每秒发生一次变幻。具体规则是：
 * 每次变幻，数组的第1个位置的数字与第2个位置的数字合并，数组的第3个位置的数字与第4个位置的数字合并，
 * 依次类推，直到只剩下两个数字为止。合并的规则是将两个数字相加。
 *
 * 小M希望知道数组在每次变幻后，数组的第一个数字会累加到一个初始值为0的变量x中，最后输出变量x的值。
 * 由于数据量可能非常大，最后的答案需要对 10^9 +7 取模。
 *
 * 为了简化输入，数组是通过k条信息给出的，信息的形式为两个数组 a 和 b，其中 a[i] 表示数组中有 a[i] 个数字 b[i]。
 */
public class ArrayChangeSum {
    public static int solution(int n, int k, int[] a, int[] b) {
        if (n < 3) {
            return 0;
        }
        int mod = 1000000007;
        //总长度为n 其实可以发现, 前面的基本上都是2的次方累加. 那么截至的位置就是最靠近的2的n次方长度
        //如8最靠近的为4. 10最靠近的8  14最靠近的8  17最靠近为14
        int count = 1 << (31 - Integer.numberOfLeadingZeros(n - 1));
        long x = 0;
        long sum = 0;
        long l = 0;
        long r = 2;
        for (int i = 0; i < k; i++) {
            long c = a[i];
            while (l + c >= r && r <= count) {
                sum = (sum + (r - l) * b[i]) % mod;
                x = (x + sum) % mod;
                c -= (r - l);
                l = r;
                r = 2 * r;
            }
            sum = (sum + c * b[i]) % mod;
            l += c;
        }
        return (int) x;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 5, new int[]{1, 1, 1, 1, 1}, new int[]{1, 2, 3, 4, 5}) == 13);
        System.out.println(solution(7, 2, new int[]{3, 4}, new int[]{1, 2}) == 7);
        System.out.println(solution(10, 2, new int[]{3, 7}, new int[]{1, 2}) == 20);
    }
}
