package com.welph.leecode.algorithm.marscode.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 小F得到了一个特殊的字符串，这个字符串只包含字符A、S、D、F，其长度总是4的倍数。
 *
 * 他的任务是通过尽可能少的替换，使得A、S、D、F这四个字符在字符串中出现的频次相等。
 *
 * 求出实现这一条件的最小子串长度。
 */
public class ReplaceSubStrForEqualCnt {

    public static int solution(String input) {
        Map<Character, Integer> counts = new HashMap<>();
        counts.put('A', 0);
        counts.put('S', 0);
        counts.put('D', 0);
        counts.put('F', 0);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            counts.put(c, counts.get(c) + 1);
        }
        int avg = input.length() / 4;
        if (counts.get('A') <= avg
                && counts.get('D') <= avg
                && counts.get('S') <= avg
                && counts.get('F') <= avg) {
            return 0;
        }
        //构建滑动窗口,  查看超出数量的
        int result = input.length();
        for (int i = 0, l = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            counts.put(c, counts.get(c) - 1);
            while (l <= i && counts.get('A') <= avg
                    && counts.get('D') <= avg
                    && counts.get('S') <= avg
                    && counts.get('F') <= avg) {
                result = Math.min(result, i - l + 1);
                c = input.charAt(l);
                counts.put(c, counts.get(c) + 1);
                l++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        System.out.println(solution("ADDF") == 1);
        System.out.println(solution("ASAFASAFADDD") == 3);
        System.out.println(solution("AAAASSSSDDDDFFFF"));
    }
}
