package com.welph.leecode.part_1_500.part_201_220;

import java.util.Arrays;

import com.welph.leecode.part_1_500.part_1_20.Solution05;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * <p>
 * 示例 1：
 * 输入：s = "aacecaaa"
 * 输出："aaacecaaa"
 * <p>
 * 示例 2：
 * 输入：s = "abcd"
 * 输出："dcbabcd"
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 仅由小写英文字母组成
 */
public class Solution214 {

    public static void main(String[] args) {
        System.out.println(shortestPalindrome("abcd"));
    }

    /**
     * --前面添加
     * //寻找包含最左边的最长回文串.
     * 使用manacher算法获取到包含最左的最长回文串 {@link Solution05}
     */
    public static String shortestPalindrome(String s) {
        String s1 = initStr(s);
        int[] count = new int[s1.length()];
        int pR = -1;
        int index = 0;
        int max = 1;
        for (int i = 0; i < s1.length(); i++) {
            count[i] = pR > i ? Math.min(pR - i, count[2 * index - i]) : 1;
            while (i + count[i] < s1.length() && i - count[i] > -1) {
                if (s1.charAt(i + count[i]) == s1.charAt(i - count[i])) {
                    count[i]++;
                } else {
                    break;
                }
            }
            if (i + count[i] > pR) {
                pR = i + count[i];
                index = i;
            }
            // 是否是开始的;
            if (i + 1 == count[i]) {
                max = i + count[i];
            }
        }
        int target = max / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= target; i--) {
            sb.append(s.charAt(i));
        }
        return sb.append(s).toString();
    }

    private static String initStr(String s) {
        StringBuilder sb = new StringBuilder("#");
        char[] ss = s.toCharArray();
        for (char aChar : ss) {
            sb.append(aChar).append("#");
        }
        return sb.toString();
    }

    /* 官方题解 */
    /*
     * 基于KMP算法 {@link KMPDemo}
     * 记k 为字符串s的反序, 那么s1为s的前缀, 则k1为k的后缀
     * 考虑s与k为回文串, 则s1同样为k的后缀
     * ----
     * 可以将 s作为模式串, 与k作为查询串进行匹配
     * 当遍历到k的末尾时,如果匹配到s中的i个字符,那么说明前i个字符和k的后i个字符相同,即找到需要填充得到字符位置
     */
    public String shortestPalindrome2(String s) {
        int n = s.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int best = -1;
        for (int i = n - 1; i >= 0; --i) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                ++best;
            }
        }
        // best 表示能够匹配到的最长的路径
        /*
         * s = aacecaaa
         * k = aaacecaa
         * ----------最终匹配到的-----------
         * k   aaacecaa
         * s    aacecaaa   //模式串走成这个样子
         */
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }
}
