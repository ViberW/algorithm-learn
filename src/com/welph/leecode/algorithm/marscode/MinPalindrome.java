package com.welph.leecode.algorithm.marscode;

/**
 * 小R手中有一个由小写英文字母组成的字符串。她希望将这个字符串转换为回文字符串，并且要求字典序尽可能小。
 * 在这个过程中，小R最多可以更改字符串中的两个字符。每个字符可以被更改为任意的小写字母。
 * 现在你的任务是帮助小R构造出在满足条件的前提下字典序最小的回文字符串。
 * <p>
 * 例如：对于字符串 acca，通过更改两个字符，可以得到回文字符串 aaaa，这是字典序最小的解。
 */
public class MinPalindrome {
    public static String solution(String s) {
        // write code here
        int mid = s.length() / 2;
        //前面替换最好
        int cost = 0;
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < mid; i++, j--) {
            if (chars[i] != chars[j]) {
                if (chars[i] > chars[j]) {
                    chars[i] = chars[j];
                } else {
                    chars[j] = chars[i];
                }
                cost++;
            }
        }
        if (cost == 0) {
            if (s.length() % 2 == 1) {
                mid++;
            }
            for (int i = 0; i < mid; i++) { //有多个cost,则尝试替换两对
                if (chars[i] != 'a') {
                    chars[i] = 'a';
                    chars[chars.length - 1 - i] = 'a';
                    break;
                }
            }
        } else if (cost == 1 && s.length() % 2 == 1) {
            chars[mid] = 'a'; //替换中间
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(solution("acccaacaacccc"));
        System.out.println(solution("acca").equals("aaaa"));
        System.out.println(solution("racecar").equals("aacecaa"));
        System.out.println(solution("fecdef").equals("feccef"));
    }
}
