package com.welph.leecode.part_1_500.part_401_420;

/**
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用{补码}运算方法。
 * <p>
 * 注意:
 * 十六进制中所有字母(a-f)都必须是小写。
 * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
 * 给定的数确保在32位有符号整数范围内。
 * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 * <p>
 * 示例 1：
 * 输入:
 * 26
 * 输出:
 * "1a"
 * <p>
 * 示例 2：
 * 输入:
 * -1
 * 输出:
 * "ffffffff"
 */
public class Solution405 {

    public static void main(String[] args) {
        System.out.println(toHex(26));
        System.out.println(toHex(16));
        System.out.println(toHex(-1));
    }

    static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        int val;
        StringBuilder builder = new StringBuilder();
        boolean zero = true;
        for (int i = 0; i < 8; i++) {
            val = num >>> ((7 - i) * 4) & 15;
            if (val != 0 || !zero) {
                zero = false;
                builder.append(chars[val]);
            }
        }
        return builder.toString();
    }

    public String toHex2(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        //倒序看起来好一点 因为十六进制, 一共有8位 0xFFFFFFFF
        for (int i = 7; i >= 0; i--) {
            int val = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
                sb.append(digit);
            }
        }
        return sb.toString();
    }

}
