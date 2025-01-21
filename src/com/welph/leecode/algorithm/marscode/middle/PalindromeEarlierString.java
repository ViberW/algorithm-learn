package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小C手中有一个由小写字母组成的字符串 s。
 * 她希望构造另一个字符串 t，并且这个字符串需要满足以下几个条件：
 *
 * t 由小写字母组成，且长度与 s 相同。
 * t 是回文字符串，即从左到右与从右到左读取相同。
 * t 的字典序要小于 s，并且在所有符合条件的字符串中字典序尽可能大。
 * 小C想知道是否能构造出这样的字符串 t，输出这样的t。如果无法构造满足条件的字符串，则输出 -1。
 */
public class PalindromeEarlierString {

    public static String solution(String s) {
        int l = 0;
        int r = s.length() - 1;
        char[] arr = s.toCharArray();
        boolean needChange = true;
        while (l <= r) {
            if (arr[l] != arr[r]) {
                arr[r] = arr[l];
                needChange = s.charAt(l) > s.charAt(r);
            }
            l++;
            r--;
        }
        if (needChange) {
            do {
                l--;
                r++;
                if (arr[l] > 'a') {
                    arr[r--] = --arr[l++];
                    //中间设置为z
                    while (l <= r) {
                        arr[l++] = arr[r--] = 'z';
                    }
                    return new String(arr);
                }
            } while (l > 0);
            return "-1";
        } else {
            return new String(arr);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("abc").equals("aba"));
        System.out.println(solution("cba").equals("cac"));
        System.out.println(solution("aaa").equals("-1"));
    }
}
