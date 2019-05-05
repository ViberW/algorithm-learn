package com.welph.leecode.one;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author: Admin
 * @date: 2019/5/5
 * @Description: {相关描述}
 */
public class Solution03 {
    public static void main(String[] args) {
        String s = "jbpnbwwd";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring01(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        //思路: 从左向右 不中断前滑动
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            //若是重复,则基于当前右边界 不断移动左边界,获取到前端i-j范围内的最新的不重复的子串,用于之后的判断
            if (!set.contains(s.charAt(j))) {
                //无重复,则右边界+1
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                //有重复,左边界移动
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static int lengthOfLongestSubstring01(String s) {
        //思路:暴力破解
        int length = s.length();
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                max = Math.max(set.size(), max);
            }
            set.clear();
        }
        return max;
    }

    public int lengthOfLongestSubstring02(String s) {
        //基于Map代替Set方案,向前滑动
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                //直接到相等于右边界值的位置
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            //保存当前值在map中的最新位置
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring03(String s) {
        int n = s.length(), ans = 0;
        //使用int数组来代替Map的保存方案,但这个仅仅是提本身的String的每个字符的值不会太大
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
