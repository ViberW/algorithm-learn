package com.welph.leecode.part_1_500.part_461_480;

import java.util.Arrays;

/**
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，
 * 所以s
 * 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * <p>
 * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，
 * 尤其是当你的输入是字符串 p ，你需要输出字符串s 中 p 的不同的非空子串的数目。
 * <p>
 * 注意: p仅由小写的英文字母组成，p 的大小可能超过 10000。
 * <p>
 * 示例1:
 * 输入: "a"
 * 输出: 1
 * 解释: 字符串 S 中只有一个"a"子字符。
 * <p>
 * 示例 2:
 * 输入: "cac"
 * 输出: 2
 * 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
 * <p>
 * 示例 3:
 * 输入: "zab"
 * 输出: 6
 * 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.
 */
public class Solution467 {

    public static void main(String[] args) {
        // System.out.println(findSubstringInWraproundString("cac"));
        System.out.println(findSubstringInWraproundString("zab"));
        System.out.println(findSubstringInWraproundString("abaab"));
        System.out.println(findSubstringInWraproundString(
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
    }

    /**
     * 考虑重复的数据
     */
    public static int findSubstringInWraproundString(String p) {
        int length = p.length();
        if (length == 0) {
            return 0;
        }
        char[] chars = p.toCharArray();
        int[] arr = new int[26];
        arr[chars[0] - 'a'] = 1;
        int size = 1;
        int index;
        for (int i = 1; i < length; i++) {
            index = chars[i] - 'a';
            if (arr[index] == 0) {
                arr[index] = 1;
            }
            if (chars[i] - chars[i - 1] == 1
                    || (chars[i] == 'a' && chars[i - 1] == 'z')) {
                size++;
                if (size > arr[index]) { // 主要方式前面 abc ..... zabc 那么其实到c结尾的子串,应该是4 而不是3
                    arr[index] = size;
                }
            } else {
                size = 1;
            }
        }
        int ret = 0;
        for (int i : arr) {
            ret += i;
        }
        return ret;
    }

    /* 官方题解 */
    // 同样的思路 更加简洁
    public int findSubstringInWraproundString2(String p) {
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); ++i) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) { // 字符之差为 1 或 -25
                ++k;
            } else {
                k = 1;
            }
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], k);
        }
        return Arrays.stream(dp).sum();
    }

}
