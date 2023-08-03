package com.welph.leecode.part_1_500.part_81_100;

/**
 * 一条包含字母A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释:它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释:它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6)
 */
public class Solution91 {

    public static void main(String[] args) {
        //System.out.println(numDecodings("12"));
        //System.out.println(numDecodings("226"));
        System.out.println(numDecodings2("226"));
    }

    //执行时间有点慢
    public static int numDecodings(String s) {
        return numDecodings(s, 0, s.length());
    }

    public static int numDecodings(String s, int start, int len) {
        if (start > len) {
            return 0;
        }
        if (start == len) {
            return 1;
        }
        int i = s.charAt(start) - '0';
        if (i == 0) {
            return 0;
        }
        int total = numDecodings(s, start + 1, len);
        if (i == 1) {
            total += numDecodings(s, start + 2, len);
        }
        if (i == 2 && start + 1 < len && s.charAt(start + 1) - '0' < 7) {
            total += numDecodings(s, start + 2, len);
        }
        return total;
    }

    //动态规划
    public static int numDecodings2(String s) {
        int lastValue = s.charAt(0);
        if (lastValue == '0') {
            return 0;
        }
        int pre = 1, curr = 1;
        int tmp;
        int currValue;
        for (int i = 1; i < s.length(); i++) {
            tmp = curr;
            currValue = s.charAt(i);
            if (currValue == '0')
                if (lastValue == '1' || lastValue == '2') curr = pre;
                else return 0;
            else if (lastValue == '1' || (lastValue == '2' && currValue >= '1' && currValue <= '6'))
                curr = curr + pre;
            pre = tmp;
            lastValue = currValue;
        }
        return curr;
    }
}
