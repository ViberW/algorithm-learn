package com.welph.leecode.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Sunday算法和BM算法稍有不同的是，Sunday算法是从前往后匹配，在匹配失败时关注的是主串中参加匹配的最末位字符的下一位字符。
 * <p>
 * 如果该字符没有在模式串中出现则直接跳过，即移动位数 = 模式串长度 + 1；
 * 否则，其移动位数 = 模式串长度 - 该字符最右出现的位置(以0开始) = 模式串中该字符最右出现的位置到尾部的距离 + 1
 * <a>https://www.jianshu.com/p/2e6eb7386cd3<a/>。
 * <p>
 * ---------
 * 看上去简单高效非常美好的Sunday算法，也有一些缺点。
 * ....因为Sunday算法的核心依赖于move数组，而move数组的值则取决于模式串，那么就可能存在模式串构造出很差的move数组。
 * 例如下面一个例子
 * 主串：baaaabaaaabaaaabaaaa
 * 模式串：aaaaa
 * <p>
 * 这个模式串使得move[a]的值为1，即每次匹配失败时，只让模式串向后移动一位再进行匹配。
 * 这样就让Sunday算法的时间复杂度飙升到了O(m*n)，也就是字符串匹配的最坏情况
 */
public class Sunday {

    public static void main(String[] args) {
        String s1 = "cababc";
        String s2 = "ab";
        System.out.println(sunday(s1.toCharArray(), s2.toCharArray()));
    }

    static int sunday(char[] total, char[] part) {
        int tSize = total.length;
        int pSize = part.length;

        Map<Character, Integer> move = new HashMap<>();

        //主串参与匹配最末位字符移动到该位需要移动的位数
        for (int i = 0; i < pSize; i++) {
            move.put(part[i], pSize - i);
        }

        int s = 0;//模式串头部在字符串位置
        int j;//模式串已经匹配了的长度
        while (s <= tSize - pSize) {//到达末尾之前
            j = 0;
            while (total[s + j] == part[j]) {
                j++;
                if (j >= pSize) {
                    return s;
                }
            }
            if (s + pSize >= total.length) {
                return -1;
            }
            s += move.getOrDefault(total[s + pSize], pSize + 1);
        }
        return -1;
    }
}
