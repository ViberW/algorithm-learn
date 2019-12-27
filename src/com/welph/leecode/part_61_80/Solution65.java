package com.welph.leecode.part_61_80;

/**
 * 验证给定的字符串是否可以解释为十进制数字。
 * <p>
 * 例如:
 * <p>
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * <p>
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * <p>
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+" "-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 */
public class Solution65 {

    public static void main(String[] args) {

        System.out.println(isNumber(" 005047e+6"));
    }

    public static boolean isNumber(String s) {
        /**
         * 1.数字
         * 2。包含一个.  必须包含数字中间
         * 3。包含e 后面必须是整数，若有- 则必须紧接在e后面
         * 4 +- 存在与最前面和e后面
         *
         * //分两端判断， 前后必须是数字， 后面的必须没有.
         */
        char[] chars = s.toCharArray();
        //允许包含 数字 - + e .
        int len = chars.length;
        for (int i = len - 1; i >= 0; i--) {
            if (' ' == chars[i]) {
                len--;
            } else {
                break;
            }
        }
        return checkNumber(chars, 0, len, false);
    }

    private static boolean checkNumber(char[] chars, int start, int len, boolean real) {
        int number = 0;
        boolean dot = false;
        boolean symbol = false;
        for (int i = start; i < len; i++) {
            char aChar = chars[i];
            if (' ' == aChar) {
                if (number != 0 || dot || symbol || real) {
                    return false;
                }
            } else if ('+' == aChar || '-' == aChar) {
                if (symbol || number > 0 || dot) {
                    return false;
                }
                symbol = true;
            } else if ('e' == aChar) {
                if (real || !checkNumber(chars, i + 1, len, true)) {
                    return false;
                }
                break;
            } else if ('.' == aChar) {
                if (dot || real) {
                    return false;
                }
                dot = true;
            } else {
                if (!isNum(aChar)) {
                    return false;
                }
                number++;
            }
        }
        return number > 0;
    }

    private static boolean isNum(char aChar) {
        int l = aChar - '0';
        return l >= 0 && l <= 9;
    }
}
