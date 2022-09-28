package com.welph.leecode.part_1_500.part_481_500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 * 美式键盘 中：
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * <p>
 * 示例 1：
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * <p>
 * 示例 2：
 * 输入：words = ["omk"]
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 * <p>
 * <p>
 * 提示：
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] 由英文字母（小写和大写字母）组成
 */
public class Solution500 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findWords(new String[]{"adsdf", "sfd"})));
        System.out.println(Arrays.toString(findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }

    static int[] arr = new int[26];

    static {
        String line1 = "qwertyuiop";
        for (int i = 0; i < line1.length(); i++) {
            arr[line1.charAt(i) - 'a'] = 1;
        }
        String line2 = "asdfghjkl";
        for (int i = 0; i < line2.length(); i++) {
            arr[line2.charAt(i) - 'a'] = 2;
        }
        String line3 = "zxcvbnm";
        for (int i = 0; i < line3.length(); i++) {
            arr[line3.charAt(i) - 'a'] = 3;
        }
    }

    public static String[] findWords(String[] words) {
        List<String> ret = new ArrayList<>();
        char[] chars;
        Label:
        for (String word : words) {
            char c = word.charAt(0);
            int expect = arr[c < 91 ? c - 'A' : c - 'a'];
            chars = word.toCharArray();
            for (char aChar : chars) {
                if (arr[aChar < 91 ? aChar - 'A' : aChar - 'a'] != expect) {
                    continue Label;
                }
            }
            ret.add(word);
        }
        return ret.toArray(new String[0]);
    }
}
