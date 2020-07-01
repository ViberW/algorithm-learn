package com.welph.leecode.algorithm.string;

/**
 * BF算法:
 * 我们在主串中，检查起始位置分别是 0、1、2…n-m 且长度为 m 的 n-m+1 个子串，看有没有跟模式串匹配的
 */
public class BFDemo {

    public static void main(String[] args) {
        String s1 = "ababc";
        String s2 = "ad";
        System.out.println(match(s1, s2));
    }

    public static boolean match(String m, String n) {
        int ml = m.length();
        int nl = n.length();
        LABEL:
        for (int i = 0; i <= ml - nl; i++) {
            for (int j = 0; j < nl; j++) {
                if (m.charAt(i + j) != n.charAt(j)) {
                    continue LABEL;
                }
            }
            return true;
        }
        return false;
    }
}
