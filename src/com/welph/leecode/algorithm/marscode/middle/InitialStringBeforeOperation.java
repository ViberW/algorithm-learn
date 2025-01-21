package com.welph.leecode.algorithm.marscode.middle;

/**
 * 给定一个字符串F,这个字符串是通过对某个初始字符串S执行若干次以下操作得到的
 * 选择一个整数K(其中0<K<|S|,|S|表示字符串 S 的长度)
 * 将S从第K个位置(从0开始计数)到未尾的子串追加到S的末尾,即: S = S + S[K:]
 *
 * 输入格式:
 * 输入为一个字符串F,仅包含小写字母,长度不超过1000。
 *
 * 输出格式:
 * 输出一个字符串,表示可能的最短初始字符串,S。
 * 如果无法通过题目描述的操作得到字符串F,则输出原字符串 F
 */
public class InitialStringBeforeOperation {

    public static String solution(String str1) {
        int n = str1.length();
        if (n == 0) {
            return "";
        }
        int[] count = new int[n]; //标记到i处的最短原始子串长度
        for (int i = 0; i < n; i++) {
            count[i] = i;
        }
        for (int i = 1; i < n; i++) {
            Label:
            for (int k = i + 1, h = i; k < n && h >= 0; k++, h--) {
                for (int c = h, d = i + 1; c <= i; c++, d++) {
                    if (str1.charAt(c) != str1.charAt(d)) {
                        continue Label;
                    }
                }
                count[k] = Math.min(count[k], count[i]);
            }
        }
        return str1.substring(0, count[n - 1] + 1);
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution("abbabbbabb").equals("ab"));
        System.out.println(solution("abbbabbbb").equals("ab"));
        System.out.println(solution("jiabanbananananiabanbananananbananananiabanbananananbananananbananananbanananan").equals("jiaban"));
        System.out.println(solution("selectecttectelectecttectcttectselectecttectelectecttectcttectectelectecttectcttectectcttectectcttectectcttect").equals("select"));
        System.out.println(solution("discussssscussssiscussssscussssdiscussssscussssiscussssscussssiscussssscussss").equals("discus"));
    }
}
