package com.welph.leecode.part_500_1000.part_521_540;

import java.util.*;

/**
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
 * <p>
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 * <p>
 * s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * <p>
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。
 * "aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 * <p>
 * 示例 1：
 * 输入: strs = ["aba","cdc","eae"]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: strs = ["aaa","aaa","aa"]
 * 输出: -1
 * <p>
 * 提示:
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] 只包含小写英文字母
 */
public class Solution522 {

    public static void main(String[] args) {
        System.out.println(findLUSlength(new String[] { "aba", "cdc", "eae" }));// 3
        System.out.println(findLUSlength(new String[] { "aaa", "aaa", "aa" }));// -1
        System.out.println(findLUSlength(new String[] { "aabbcc", "aabbcc", "cb" }));// 2
    }

    /**
     * {@link Solution521} 有点区别. 因为会考虑小一长度的 但又不能
     */
    public static int findLUSlength(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i = o1.length() - o2.length();
                return i == 0 ? o1.compareTo(o2) : i;
            }
        });
        boolean flag;
        int ret = -1;
        for (int i = 0; i < strs.length; i++) {
            flag = true;
            if (i > 0) {
                if (isSubStr(strs[i], strs[i - 1])) {
                    flag = false;
                }
            }
            for (int j = i + 1; j < strs.length; j++) {
                if (isSubStr(strs[i], strs[j])) {
                    flag = false;
                }
            }
            if (flag) {
                ret = Math.max(ret, strs[i].length());
            }
        }
        return ret;
    }

    public static boolean isSubStr(String str1, String str2) {
        int i1 = 0;
        int i2 = 0;
        while (i1 < str1.length() && i2 < str2.length()) {
            if (str1.charAt(i1) == str2.charAt(i2)) {
                i1++;
            }
            i2++;
        }
        return i1 == str1.length();
    }

    ////////////////////////////////
    public int findLUSlength2(String[] strs) {
        // 不为任意其他字符串子序列的最长字符串即为答案
        int res = -1;
        for (int i = 0; i < strs.length; i++) {
            // 剪枝优化
            if (res >= strs[i].length())
                continue;
            boolean flag = true;
            for (int j = 0; j < strs.length; j++) {
                if (i == j)
                    continue;
                if (isSubsequence(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            res = flag ? Math.max(res, strs[i].length()) : res;
        }
        return res;
    }

    // s是否是t的子序列
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        // s,t等长，只有相等才为子序列
        if (n == m) {
            return s.equals(t);
        }
        if (n > m) {
            return false;
        }
        // 贪心匹配s[i]
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
