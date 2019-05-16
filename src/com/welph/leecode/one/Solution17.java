package com.welph.leecode.one;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * 说明: 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * @author: Admin
 * @date: 2019/5/16
 * @Description: {相关描述}
 */
public class Solution17 {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    private static String[] map = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return Collections.emptyList();
        List<String> list = new ArrayList<>();
        helper(list, digits, "");
        return list;
    }

    /**
     * 最终的长度等于digits长度
     */
    private static void helper(List<String> list, String digits, String ans) {
        if (ans.length() == digits.length()) {
            list.add(ans);
            return;
        }
        for (char c : map[digits.charAt(ans.length()) - '2'].toCharArray()) {
            helper(list, digits, ans + c);
        }
    }

    /**
     * 不断迭代,将当前的list中最前面的小一级的长度拿出来重新拼接
     */
    public static List<String> letterCombinations01(String digits) {
        if (digits.length() == 0) return Collections.emptyList();
        LinkedList<String> list = new LinkedList<>();
        list.add("");
        char[] charArray = digits.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            while (list.getFirst().length() == i) {
                String pop = list.removeFirst();
                for (char v : map[c - '2'].toCharArray()) {
                    list.addLast(pop + v);
                }
            }
        }
        return list;
    }
}

