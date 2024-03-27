package com.welph.leecode.part_1_500.part_381_400;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 */
public class Solution394 {
    public static void main(String[] args) {
        // System.out.println(decodeString("3[a]2[bc]"));
        // System.out.println(decodeString("3[a2[c]]"));
        // System.out.println(decodeString("2[abc]3[cd]ef"));
        // System.out.println(decodeString("abc3[cd]xyz"));
        System.out.println(decodeString("100[leetcode]"));
    }

    public static String decodeString(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        decode(chars, 0, length, 1, builder);
        return builder.toString();
    }

    static int decode(char[] chars, int l, int r, int q, StringBuilder b) {
        int cycle = 0;
        StringBuilder builder = new StringBuilder();
        char c;
        for (; l < r; l++) {
            c = chars[l];
            if (c >= '0' && c <= '9') {
                cycle = 10 * cycle + (c - '0');
            } else if (c == '[') {
                l = decode(chars, l + 1, r, cycle, builder);
                cycle = 0;
            } else if (c == ']') {
                break;
            } else {
                builder.append(c);
            }
        }
        for (int j = 0; j < q; j++) {
            b.append(builder);
        }
        return l;
    }

    /* 官方题解 */ // 感觉我的写法还挺好
    String src;
    int ptr;

    public String decodeString2(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {// 数字
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString(); // 解析后面一层
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) { // 是否为字母
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }
}
