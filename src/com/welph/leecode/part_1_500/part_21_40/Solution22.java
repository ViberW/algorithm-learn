package com.welph.leecode.part_1_500.part_21_40;

import java.util.*;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @author: Admin
 * @date: 2019/5/16
 * @Description: {相关描述}
 */
public class Solution22 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(4));
    }

    /**
     * 保持第一个点  逐渐往右移动,每次2,不断计算中间和 外面的可能
     * 慢!!!
     *  第三种法就不错,将长度多少的括号的可能性保存了下,减少了不必要的计算
     */
    public static List<String> generateParenthesis(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        if (n == 1) {
            return Arrays.asList("()");
        }
        //判断左右括号相隔的距离  最长不超过2n
        LinkedList<String> list = new LinkedList<>();
        List<String> cur;
        LinkedList<String> item;
        String s;
        int total = 2 * n;
        for (int i = 1; i < total; i += 2) {
            //生成inner
            item = new LinkedList<>();
            item.add("()");
            cur = generateParenthesis(i / 2);
            if (!cur.isEmpty()) {
                item.removeFirst();
                for (String str : cur) {
                    item.add("(" + str + ")");
                }
            }
            //生成outter
            cur = generateParenthesis((total - i) / 2);
            if (!cur.isEmpty()) {
                while (item.getFirst().length() != total) {
                    s = item.removeFirst();
                    for (String str : cur) {
                        item.add(s + str);
                    }
                }
            }
            list.addAll(item);
        }
        return list;
    }

    /**
     * 回溯算法, 递归中判断左右括号是否还可以组成有效的组合
     */
    public static List<String> generateParenthesis01(int n) {
        List<String> res = new ArrayList<String>();
        generate(res, "", 0, 0, n);

        return res;
    }

    //count1统计“(”的个数，count2统计“)”的个数
    public static void generate(List<String> res, String ans, int count1, int count2, int n) {
        if (count1 > n || count2 > n) return;
        if (count1 == n && count2 == n) res.add(ans);
        if (count1 >= count2) {
            generate(res, ans + "(", count1 + 1, count2, n);
            generate(res, ans + ")", count1, count2 + 1, n);

        }
    }

    /**
     * 第一种方法的精简,使用map来保存相隔长度的括号可能性,减少不必要的递归
     */
    public List<String> generateParenthesis03(int n) {
        HashMap<Integer, List<String>> hashMap = new HashMap<>();
        hashMap.put(0, Collections.singletonList(""));
        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String fj : hashMap.get(j)) {
                    for (String fi_j_1 : hashMap.get(i - j - 1)) {
                        list.add("(" + fj + ")" + fi_j_1);// calculate f(i)
                    }
                }
            }
            hashMap.put(i, list);
        }
        return hashMap.get(n);
    }

}
