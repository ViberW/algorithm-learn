package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小U和小R喜欢探索二进制数字的奥秘。他们想找到一个方法，将两个二进制字符串相加并以十进制的形式呈现。
 * 这个过程需要注意的是，他们的二进制串可能非常长，所以常规的方法可能无法处理大数。小U和小R希望你帮助他们设计一个算法，
 * 该算法能在保证时间复杂度不超过O(n^2)的前提下，返回两个二进制字符串的十进制求和结果。
 */
public class BinarySum {

    public static String solution(String binary1, String binary2) {
        StringBuilder builder = new StringBuilder();
        int last = 0;
        int i = binary1.length() - 1;
        int j = binary2.length() - 1;
        while (i >= 0 || j >= 0) {
            int sum = (i >= 0 ? binary1.charAt(i--) - '0' : 0)
                    + (j >= 0 ? binary2.charAt(j--) - '0' : 0) + last;
            last = sum / 2;
            builder.append(sum % 2);
        }

        if (last != 0) {
            builder.append(last);
        }
        String str = builder.reverse().toString();
        return binaryToDecimal(str).toString().intern();
    }

    public static String binaryToDecimal(String binary) {
        String decimal = "0";  // 初始化十进制结果为字符串 "0"
        int length = binary.length();

        // 从最高位到最低位进行处理
        for (int i = 0; i < length; i++) {
            // 将当前十进制结果乘以 2（左移一位）
            decimal = multiplyByTwo(decimal);
            // 如果当前位为 1，则将 1 加到十进制结果中
            if (binary.charAt(i) == '1') {
                decimal = addOne(decimal);
            }
        }
        return decimal;
    }

    // 将十进制字符串乘以 2
    private static String multiplyByTwo(String decimal) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        // 从最低位开始，逐位乘以 2
        for (int i = decimal.length() - 1; i >= 0; i--) {
            int digit = decimal.charAt(i) - '0';
            int product = digit * 2 + carry;
            result.append(product % 10);
            carry = product / 10;
        }
        if (carry > 0) {
            result.append(carry);
        }
        // 反转结果并返回
        return result.reverse().toString();
    }

    // 将十进制字符串加 1
    private static String addOne(String decimal) {
        StringBuilder result = new StringBuilder();
        int carry = 1;  // 初始时为 1，因为我们要加 1
        // 从最低位开始，逐位相加
        for (int i = decimal.length() - 1; i >= 0; i--) {
            int digit = decimal.charAt(i) - '0';
            int sum = digit + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            result.append(carry);
        }
        // 反转结果并返回
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        // You can add more test cases here
        System.out.println(solution("101", "110").equals("11"));
        System.out.println(solution("111111", "10100").equals("83"));
        System.out.println(solution("111010101001001011", "100010101001").equals("242420"));
        System.out.println(solution("111010101001011", "10010101001").equals("31220"));
    }
}
