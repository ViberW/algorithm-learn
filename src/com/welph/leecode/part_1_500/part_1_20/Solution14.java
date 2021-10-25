package com.welph.leecode.part_1_500.part_1_20;

/**
 * 编写一个函数来查找字符串数组中的最长公共 [前缀]。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @author: Admin
 * @date: 2019/5/15
 * @Description: {相关描述}
 */
public class Solution14 {
    public static void main(String[] args) {
        String[] strs = new String[]{"", "b"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println("".substring(0, 0));
    }

    public static String longestCommonPrefix(String[] strs) {
        //从最小长度中找起  因为是寻找前缀 所以归为简单
        if (null == strs || strs.length == 0) {
            return "";
        }
        String minArr = strs[0];
        for (String str : strs) {
            if (minArr.length() > str.length()) {
                minArr = str;
            }
        }
        int i = 0;
        lab:
        for (; i < minArr.length(); i++) {
            for (String str : strs) {
                if (minArr.charAt(i) != str.charAt(i)) {
                    break lab;
                }
            }
        }
        return minArr.substring(0, i);
    }
}
