package com.welph.leecode.part_1_500.part_401_420;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如"Aa"不能当做一个回文字符串。
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * 输入: "abccccdd"
 * 输出: 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class Solution409 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
        System.out.println(longestPalindrome("aaaAaaaa"));
        System.out.println(longestPalindrome(
                "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
    }

    /**
     * 只能保存一个奇数对(可有可无), 无穷个偶数对 ,奇数取偶数
     */
    public static int longestPalindrome(String s) {
        int length = s.length();
        int[] chars = new int[52];
        int c;
        for (int i = 0; i < length; i++) {
            c = s.charAt(i);
            if (c >= 'a') {
                chars[c - 'a' + 26]++;
            } else {
                chars[c - 'A']++;
            }
        }
        int ans = 0;
        boolean oddOverZero = false;
        for (int count : chars) {
            if ((count & 1) == 1) {
                oddOverZero = true;
                ans += count - 1;
            } else {
                ans += count;
            }
        }
        if (oddOverZero) {
            ans++;
        }
        return ans;
    }

    /* 官方题解 */

    public int longestPalindrome2(String s) {
        int[] count = new int[128];
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            count[c]++;
        }

        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) { // 仅在第一次遇见奇数ans+1
                ans++;
            }
        }
        return ans;
    }
}
