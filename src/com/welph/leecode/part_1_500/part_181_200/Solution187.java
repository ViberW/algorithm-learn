package com.welph.leecode.part_1_500.part_181_200;

import java.util.*;

/**
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，
 * 例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 * <p>
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * <p>
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 * <p>
 * 提示：
 * 0 <= s.length <= 105
 * s[i] 为 'A'、'C'、'G' 或 'T'
 */
public class Solution187 {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    //长度为10,且出现次数超过1
    //使用位运算 A 00 C 01 G 10 T 11
    public static List<String> findRepeatedDnaSequences(String s) {
        int length = s.length();
        if (length <= 10) {
            return Collections.emptyList();
        }
        Map<Long, Integer> exits = new HashMap<>();
        long preVal = 0;
        for (int i = 0; i < 10; i++) {
            preVal = (preVal << 2) | parse(s.charAt(i));
        }
        List<String> result = new ArrayList<>();
        exits.put(preVal, 1);
        int expectVal = (1 << 18) - 1;
        for (int i = 10; i < length; i++) {
            preVal &= expectVal;
            preVal = (preVal << 2) | parse(s.charAt(i));
            Integer count = exits.getOrDefault(preVal, 0);
            if (count == 1) {
                result.add(s.substring(i - 9, i + 1));
            }
            exits.put(preVal, count + 1);
        }
        return result;
    }

    private static int parse(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        return 0;
    }
}
