package com.welph.leecode.algorithm.marscode;

import java.util.*;

/**
 * 小U定义了一种特殊的字符串类型，称为 "icci" 型字符串。要满足这个类型，字符串必须具备以下条件：
 *
 * 它的长度为 4。
 * 第一个和第四个字符必须是元音字母（'a', 'e', 'i', 'o', 'u'）。
 * 第二个和第三个字符必须是辅音字母（除了元音以外的字母）。
 * 该字符串是一个回文串。
 * 例如，字符串 "awwa" 和 "uttu" 都是 "icci" 型字符串。现在给定一个字符串，小U想知道其中有多少个 "icci" 型的子序列。
 */
public class IcciSubStrings {

    public static int solution(String s) {
        Set<Character> metaChars = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        //记录每个数据的个数.
        int result = 0;
        int n = s.length();
        Map<Character, Integer> counts = new HashMap<>(32);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!metaChars.contains(c)) {
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                char c1 = s.charAt(j);
                if (!metaChars.contains(c1)) {
                    counts.put(c1, counts.getOrDefault(c1, 0) + 1);
                } else if (c1 == c) {
                    for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
                        if (entry.getValue() >= 2) {
                            result += entry.getValue() * (entry.getValue() - 1) / 2;
                        }
                    }
                }
            }
            counts.clear();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("iiaabbii") == 4);
        System.out.println(solution("aekekeo") == 1);
        System.out.println(solution("abcdefg") == 0);
    }
}
