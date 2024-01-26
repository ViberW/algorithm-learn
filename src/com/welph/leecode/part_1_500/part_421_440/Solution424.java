package com.welph.leecode.part_1_500.part_421_440;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
 * 总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过 104。
 * <p>
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * <p>
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class Solution424 {
    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }

    /**
     * 记录窗口内各个字符的数量
     * 若是i对应字符的
     */
    public static int characterReplacement(String s, int k) {
        char[] chars = s.toCharArray();
        int res = 0;
        int maxCnt = 0;
        int l = 0;
        int[] arr = new int[26];
        for (int i = 0; i < chars.length; i++) {
            arr[chars[i] - 'A']++;
            maxCnt = Math.max(maxCnt, arr[chars[i] - 'A']);
            while (i - l + 1 - maxCnt > k) { // 若是有新的,则尽量要保证至少超过maxCnt的长度
                arr[chars[l] - 'A']--;
                l++;
            }
            res = Math.max(res, i - l + 1);
        }
        return res;
    }

    /* 来看看官方题解 */
    /*
     * 结合官方题解, 说明maxn=maxCnt 代表重复字母最长的一段值, 只能递增, 后续依照当前大小进行变化
     */
    public int characterReplacement2(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxn > k) {// 这里仅仅需要变动一次即可, right - left + 1 > maxn + k
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}
