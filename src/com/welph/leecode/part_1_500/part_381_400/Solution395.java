package com.welph.leecode.part_1_500.part_381_400;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * <p>
 * 示例 1：
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * <p>
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 */
public class Solution395 {

    public static void main(String[] args) {
        System.out.println(longestSubstring("aaacaabbb", 3));
        System.out.println(longestSubstring("ababbc", 2));
        System.out.println(longestSubstring("aaabb", 3));
        System.out.println(longestSubstring("ababacb", 3));
        System.out.println(longestSubstring("a", 1));
        System.out.println(longestSubstring("bbaaacbd", 3));
    }

    /**
     * 使用滑动窗口呢? --官方题解.
     */
    public static int longestSubstring1(String s, int k) {
        int ret = 0;
        int n = s.length();
        for (int t = 1; t <= 26; t++) {
            int l = 0, r = 0;
            int[] cnt = new int[26];
            int tot = 0;
            int less = 0;
            while (r < n) {
                // 这块用来记录 less 当前子串存在满足的字符 若是less == 0 说明字符i满足>k 这块想到了 但下面...
                cnt[s.charAt(r) - 'a']++;
                if (cnt[s.charAt(r) - 'a'] == 1) {
                    tot++;
                    less++;
                }
                if (cnt[s.charAt(r) - 'a'] == k) {
                    less--;
                }

                while (tot > t) {
                    cnt[s.charAt(l) - 'a']--;
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        less++;
                    }
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        tot--;
                        less--;
                    }
                    l++;
                }
                if (less == 0) {// 若是less == 0 说明字符i满足>k
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }

    /**
     * 每个字符都要>=k, [分治]--时间有点长
     */
    public static int longestSubstring(String s, int k) {
        return longestSubstring(s, 0, s.length(), k);
    }

    static int longestSubstring(String s, int l, int r, int k) {
        if (r - l < k) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] arr = new int[26];
        for (int i = l; i < r; i++) {
            arr[chars[i] - 'a']++;
        }
        for (int i = l; i < r; i++) {
            if (arr[chars[i] - 'a'] < k) {
                return Math.max(longestSubstring(s, l, i, k), longestSubstring(s, i + 1, r, k));
            }
        }
        return r - l;
    }

    /* 官方题解- 分治算法 */
    public int longestSubstring2(String s, int k) {
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    public int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }
            // 找出每一段的中间不包含split的子串
            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }

}
