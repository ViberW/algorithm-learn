package com.welph.leecode.part_500_1000.part_541_560;

/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * 示例 1：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * <p>
 * 示例 2：
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 * <p>
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅由小写英文组成
 * 1 <= k <= 10^4
 */
public class Solution541 {

    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefghl", 3));
        System.out.println(reverseStr("abcd", 2));
    }

    public static String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int dou = 0;
        int douK = 2 * k;
        for (int i = 0; i < chars.length; i++) {
            if (i % douK == douK - 1) {
                swap(chars, dou * douK, dou * douK + k - 1);
                dou++;
            }
        }
        int delta = chars.length % douK;
        if (delta > 0) {
            if (delta < k) {
                swap(chars, dou * douK, chars.length - 1);
            } else {
                swap(chars, dou * douK, dou * douK + k - 1);
            }
        }
        return new String(chars);
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp;
        for (; i < j; i++, j--) {
            tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
    }

    public static String reverseStr1(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) { // 这里直接加2k
            swap(arr, i, Math.min(i + k, n) - 1); // 右的最大值
        }
        return new String(arr);
    }

}
