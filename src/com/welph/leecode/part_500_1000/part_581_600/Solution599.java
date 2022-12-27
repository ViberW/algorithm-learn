package com.welph.leecode.part_500_1000.part_581_600;

import java.util.*;

/**
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，
 * 则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 * <p>
 * 示例 1:
 * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，
 * list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * <p>
 * 示例 2:
 * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，
 * list2 = ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 * <p>
 * 提示:
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30
 * list1[i] 和 list2[i] 由空格 ' ' 和英文字母组成。
 * list1 的所有字符串都是 唯一 的。
 * list2 中的所有字符串都是 唯一 的。
 */
public class Solution599 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"})));
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        Set<String> result = new HashSet<>();
        Map<String, Integer> exists = new HashMap<>();
        int minIndex = list1.length + list2.length;
        for (int i = 0; i < list1.length; i++) {
            exists.put(list1[i], i);
        }
        String s;
        int index;
        for (int i = 0; i < list2.length; i++) {
            s = list2[i];
            if (exists.containsKey(s)) {
                index = exists.get(s) + i;
                if (index < minIndex) {
                    result.clear();
                    result.add(s);
                    minIndex = index;
                } else if (index == minIndex) {
                    result.add(s);
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}

