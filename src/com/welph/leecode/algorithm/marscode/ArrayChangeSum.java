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
        int mod = 1000000007;
        //总长度为n 假设二进制n=14: 1110 可以发现,抛开最高位的1.
        // 从低位开始遍历, 碰到1.则最后一段不参与计算, 若是碰到0,则当次合并长度不参与计算
        // 如果之前有奇数, 需要用一个额外字段进行标记
        /*
         * 例如n=14=>1110: (bit为当前位bit, addition为标记之前为奇数个, end: 最终参与计算的截止索引, delta: 合并跨度)
         * 第一次bit=0 addition=0 end=n  delta=2 : end-2 => 12
         * 第二次bit=1 addition=0 delta=4: 不变  addition = 1;
         * 第三次bit=1 addition=1 delta=8: end-4=>8
         * 那么最终值x就是前8个值参与的计算, 多次遍历
         */


        long x = 0;
        return (int) x;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 5, new int[]{1, 1, 1, 1, 1}, new int[]{1, 2, 3, 4, 5}) == 13);
        System.out.println(solution(7, 2, new int[]{3, 4}, new int[]{1, 2}) == 7);
        System.out.println(solution(10, 2, new int[]{3, 7}, new int[]{1, 2}) == 20);
    }
}
