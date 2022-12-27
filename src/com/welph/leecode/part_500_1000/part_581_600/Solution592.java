package com.welph.leecode.part_500_1000.part_581_600;

/**
 * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。
 * 这个结果应该是不可约分的分数，即最简分数。
 * 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 * <p>
 * 示例 1:
 * 输入: expression = "-1/2+1/2"
 * 输出: "0/1"
 * <p>
 * 示例 2:
 * 输入: expression = "-1/2+1/2+1/3"
 * 输出: "1/3"
 * <p>
 * 示例 3:
 * 输入: expression = "1/3-1/2"
 * 输出: "-1/6"
 * <p>
 * 提示:
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 */
public class Solution592 {

    public static void main(String[] args) {
        System.out.println(fractionAddition("-1/2+1/2"));
        System.out.println(fractionAddition("-1/2+1/2+1/3"));
        System.out.println(fractionAddition("1/3-1/2"));
    }

    /**
     * {@link com.welph.leecode.algorithm.thinking.ExpandEuclidean_8}
     */
    public static String fractionAddition(String expression) {
        char[] chars = expression.toCharArray();
        int[] preValue = new int[2];
        preValue[1] = 1;
        int[] curValue = new int[2];
        curValue[1] = 1;
        int tempInt = 0;
        boolean add = true;
        char aChar = '+';
        for (int i = 0; i <= chars.length; i++) {
            if (chars.length == i || (aChar = chars[i]) == '-' || aChar == '+') {
                if (tempInt > 0) {
                    curValue[1] = tempInt;
                }
                calculate(add, preValue, curValue);
                tempInt = 0;
                add = aChar == '+';
            } else if (aChar == '/') {
                curValue[0] = tempInt;
                tempInt = 0;
            } else {
                tempInt = tempInt * 10 + (aChar - '0');
            }
        }
        return preValue[0] + "/" + preValue[1];
    }

    private static void calculate(boolean add, int[] preValue, int[] curValue) {
        int molecule = curValue[1] * preValue[0] + (add ? 1 : -1) * (preValue[1] * curValue[0]);
        int denominator = curValue[1] * preValue[1];
        int gcd = calculateGcd(Math.abs(molecule), denominator);
        if (gcd != 0) {
            molecule /= gcd;
            denominator /= gcd;
        }
        preValue[0] = molecule;
        preValue[1] = denominator;
        curValue[0] = 0;
        curValue[1] = 1;
    }

    public static int calculateGcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return calculateGcd(b, a % b);
    }
}
