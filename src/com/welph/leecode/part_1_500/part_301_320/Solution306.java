package com.welph.leecode.part_1_500.part_301_320;

/**
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * <p>
 * 示例 1:
 * 输入: "112358"
 * 输出: true
 * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * <p>
 * 示例 2:
 * 输入: "199100199"
 * 输出: true
 * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 * 进阶:
 * 你如何处理一个溢出的过大的整数输入?
 */
public class Solution306 {

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("112358"));
        System.out.println(isAdditiveNumber("199100199"));
        System.out.println(isAdditiveNumber("121474836462147483647"));
        System.out.println(isAdditiveNumber("101"));
        System.out.println(isAdditiveNumber("0235813"));///
        System.out.println(isAdditiveNumber("121474836472147483648"));
        System.out.println(isAdditiveNumber("199001200"));
        // 2147483647
    }

    // 首先确定好最前面的两个数据值, 之后就不断叠加就可以了
    // 碰到了0就往后叠加
    public static boolean isAdditiveNumber(String num) {
        int length = num.length();
        int l = length / 2;
        for (int i = 0; i <= l; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                if (isAdditiveNumber(num, -1, i, j, length)) {
                    return true;
                }
                if (j == i + 1 && num.charAt(j) - '0' == 0) {
                    break;
                }
            }
            if (i == 0 && num.charAt(i) - '0' == 0) {
                break;
            }
        }
        return false;
    }

    public static boolean isAdditiveNumber(String num, int l, int mid, int r, int length) {
        StringBuilder sb;
        int len = 0;
        while (r + len < length) {
            int m = mid;
            int n = r;
            int over = 0;
            sb = new StringBuilder();
            for (; m > l || n > mid; m--, n--) {
                if (m > l) {
                    over += num.charAt(m) - '0';
                }
                if (n > mid) {
                    over += num.charAt(n) - '0';
                }
                sb.append(over % 10);
                over /= 10;
            }
            if (over > 0) {
                sb.append(over % 10);
            }
            l = mid;
            mid = r;
            len = sb.length();
            if (r + len < length) {
                for (int i = len - 1; i >= 0; i--) {
                    if (sb.charAt(i) != num.charAt(++r)) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return r + 1 == length;
    }

    /* 官方题解 --和我的思路一样的*/
    public boolean isAdditiveNumber2(String num) {
        int n = num.length();
        for (int secondStart = 1; secondStart < n - 1; ++secondStart) {
            if (num.charAt(0) == '0' && secondStart != 1) { //筛选后面的累加序列不为0开头(除了二号位为0), 首位允许为0
                break;
            }
            for (int secondEnd = secondStart; secondEnd < n - 1; ++secondEnd) {
                if (num.charAt(secondStart) == '0' && secondStart != secondEnd) { //允许2号位为0
                    break;
                }
                if (valid(secondStart, secondEnd, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean valid(int secondStart, int secondEnd, String num) {
        int n = num.length();
        int firstStart = 0, firstEnd = secondStart - 1;
        while (secondEnd <= n - 1) {
            String third = stringAdd(num, firstStart, firstEnd, secondStart, secondEnd);
            int thirdStart = secondEnd + 1;
            int thirdEnd = secondEnd + third.length();
            if (thirdEnd >= n || !num.substring(thirdStart, thirdEnd + 1).equals(third)) {
                break;
            }
            if (thirdEnd == n - 1) {
                return true;
            }
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = thirdStart;
            secondEnd = thirdEnd;
        }
        return false;
    }

    public String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuffer third = new StringBuffer();
        int carry = 0, cur = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                cur += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            carry = cur / 10;
            cur %= 10;
            third.append((char) (cur + '0'));
        }
        third.reverse();
        return third.toString();
    }

}
