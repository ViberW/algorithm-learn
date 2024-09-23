package com.welph.leecode.part_500_1000.part_561_580;

import java.util.ArrayList;
import java.util.List;

import com.welph.leecode.part_1_500.part_461_480.Solution479;

/**
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * “最近的”定义为两个整数差的绝对值最小。
 * <p>
 * 示例 1:
 * 输入: n = "123"
 * 输出: "121"
 * <p>
 * 示例 2:
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 * <p>
 * 提示:
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 10^18 - 1] 范围内的整数
 */
public class Solution564 {

    public static void main(String[] args) {
        System.out.println(nearestPalindromic("9")); // 8
        System.out.println(nearestPalindromic("11")); // 9
        System.out.println(nearestPalindromic("100000")); // 这里的回文结果是99999 而不是100001
        System.out.println(nearestPalindromic("99999")); // 这里的回文结果是100001 最近的就是它了
        System.out.println(nearestPalindromic("121"));
        System.out.println(nearestPalindromic("321"));
        System.out.println(nearestPalindromic("11111"));// 11011
    }

    /**
     * 返回最近且最小的回文串
     * 需要最小的 说明需要从后往前寻找
     * {@link Solution479}
     */
    public static String nearestPalindromic(String n) {
        char[] chars = n.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {

        }
        return null;
    }

    /*
     * 官方题解
     * 模拟
     * ------------------------
     * 用原数的较高位的数字替换其对应的较低位
     * 特殊情况:
     * 
     * 构造的回文整数大于原数时，我们可以减小该回文整数的中间部分来缩小回文整数和原数的差距。例如，对于数 99321，我们将构造出回文整数 99399，实际上
     * 99299 更接近原数。
     * >> 当我们减小构造的回文整数时，可能导致回文整数的位数变化。
     * >> 例如，对于数 100，我们将构造出回文整数 101，减小其中间部分将导致位数减少。得到的回文整数形如 999…999（10^len−1）。
     * 
     * 构造的回文整数小于原数时，我们可以增大该回文整数的中间部分来缩小回文整数和原数的差距。
     * 例如，对于数 12399，我们将构造出回文整数 12321，实际上12421 更接近原数。
     * >> 当我们增大构造的回文整数时，可能导致回文整数的位数变化。
     * >> 例如，对于数 998，我们将构造出回文整数 999，增大其中间部分将导致位数增加。得到的回文整数形如 100…001（10^len +1）。
     * ------------------------
     * 1. 用原数的前半部分替换后半部分得到的回文整数。
     * 2. 用原数的前半部分加一后的结果替换后半部分得到的回文整数。
     * 3. 用原数的前半部分减一后的结果替换后半部分得到的回文整数。
     * 4. 为防止位数变化导致构造的回文整数错误，因此直接构造 999…999 和 100…001 作为备选答案。
     */
    public String nearestPalindromic2(String n) {
        long selfNumber = Long.parseLong(n), ans = -1;
        List<Long> candidates = getCandidates(n);
        // 所有结果中找到最小值的即可
        for (long candidate : candidates) {
            if (candidate != selfNumber) {
                if (ans == -1 ||
                        Math.abs(candidate - selfNumber) < Math.abs(ans - selfNumber) ||
                        Math.abs(candidate - selfNumber) == Math.abs(ans - selfNumber) && candidate < ans) {
                    ans = candidate;
                }
            }
        }
        return Long.toString(ans);
    }

    // 构建所有可能
    public List<Long> getCandidates(String n) {
        // 假设n=123
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {
            {
                add((long) Math.pow(10, len - 1) - 1); // 添加99
                add((long) Math.pow(10, len) + 1); // 添加1001
            }
        };
        // 左边的字符串
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        // 从11 到13 相当于是中间的数字从11 ->12 -> 13
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));// 长度为奇数 则删除第一个字符
            String candidate = sb.toString();
            try {
                candidates.add(Long.parseLong(candidate));
            } catch (NumberFormatException ex) {
                continue;
            }
        }
        return candidates;
    }

}
