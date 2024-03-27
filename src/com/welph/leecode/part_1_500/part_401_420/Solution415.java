package com.welph.leecode.part_1_500.part_401_420;

/**
 * 给定两个字符串形式的非负整数num1 和num2，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 示例 1：
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * <p>
 * 示例 2：
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * <p>
 * 示例 3：
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 * <p>
 * 提示：
 * 1 <= num1.length, num2.length <= 10^4
 * num1 和num2 都只包含数字0-9
 * num1 和num2 都不包含任何前导零
 */
public class Solution415 {

    public static void main(String[] args) {
        System.out.println(addStrings("456", "77"));
    }

    public static String addStrings(String num1, String num2) {
        char[] chars = num1.toCharArray();
        char[] chars1 = num2.toCharArray();
        int i = chars.length - 1;
        int j = chars1.length - 1;
        int carry = 0;
        int add;
        char[] c = new char[Math.max(chars.length, chars1.length)];
        int k = c.length - 1;
        for (; i >= 0 && j >= 0; i--, j--, k--) {
            add = (chars[i] - '0') + (chars1[j] - '0') + carry;
            c[k] = (char) ('0' + add % 10);
            carry = add / 10;
        }
        if (i >= 0) {
            for (; i >= 0; i--, k--) {
                add = (chars[i] - '0') + carry;
                c[k] = (char) ('0' + add % 10);
                carry = add / 10;
            }
        }
        if (j >= 0) {
            for (; j >= 0; j--, k--) {
                add = (chars1[j] - '0') + carry;
                c[k] = (char) ('0' + add % 10);
                carry = add / 10;
            }
        }
        if (carry > 0) {
            char[] nc = new char[c.length + 1];
            nc[0] = (char) ('0' + carry);
            System.arraycopy(c, 0, nc, 1, c.length);
            c = nc;
        }
        return new String(c);
    }

    /* 官方题解 */

    // 写法好很多
    public String addStrings2(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }
}
