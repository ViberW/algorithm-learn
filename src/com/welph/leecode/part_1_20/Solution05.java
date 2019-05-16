package com.welph.leecode.part_1_20;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * @author: Admin
 * @date: 2019/5/7
 * @Description: {相关描述}
 */
public class Solution05 {

    public static void main(String[] args) {
        String s = "babbad";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome01(s));
        System.out.println(longestPalindrome03(s));
        System.out.println(longestPalindrome06(s));
    }

    /**
     * 中心扩展
     */
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i < len; i++) {
            int count = maxRight - maxLeft;
            //若是两边相等,获取最大+1的延伸长度
            int limit = Math.min(len - 1 - i, i);
            int j = 0;
            boolean mix = true;
            boolean fix = true;
            int k = 0;
            for (; j <= limit; j++) {
                //此处不断每次判断,知道得到最终相同的判断数据
                mix = mix && (chars[i - j] == chars[i + j]);
                fix = fix && (j < len - 1 - i && chars[i - j] == chars[i + j + 1]);
                if (!(mix || fix)) {
                    break;
                } else if (!(mix && fix) && mix) {
                    k = 1;
                }
            }
            if (k != 0) {
                j -= 1;
                //说明对称
                if ((2 * j + 1) > (count)) {
                    maxLeft = i - j;
                    maxRight = i + j + 1;
                }
            } else {
                //说明前后相等
                if ((2 * j) > (count)) {
                    maxLeft = i - j + 1;
                    maxRight = i + j + 1;
                }
            }
        }
        return s.substring(maxLeft, maxRight);
    }

    /**
     * 最长公共子串
     */
    public static String longestPalindrome01(String s) {
        if (s.length() < 2) {
            return s;
        }
        String maxStr = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            point:
            for (int j = i + 1; j < s.length(); j++) {
                String str = s.substring(i, j + 1);  //正向字符串
                String reverse = new StringBuffer(str).reverse().toString();          //反向字符串
                if (reverse.equals(str) && str.length() > maxStr.length()) {
                    //判断前后索引是否一致,防止因为本身字符串正反相同.但不对称
                    for (int k = 0, n = str.length() - 1; k < n; k++, n--) {
                        if (str.charAt(k) != str.charAt(n)) {
                            break point;
                        }
                    }
                    maxStr = str;        //比较是否是回文串，且比当前最大的子串长
                }
            }
        }

        return maxStr;
    }

    /**
     * 同第一个方法类似
     */
    public static String longestPalindrome02(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] range = new int[2];
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            i = find(c, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    private static int find(char[] c, int low, int[] range) {
        int max = c.length - 1;
        int high = low;
        while (high < max && c[high + 1] == c[low]) {
            high++;
        }
        int result = high;
        while (low > 0 && high < max && c[low - 1] == c[high + 1]) {
            low--;
            high++;
        }
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return result;
    }

    /**
     * 动态规划: P(i,j)=(P(i+1,j−1) and Si==Sj)
     */
    public static String longestPalindrome03(String s) {
        //新找到1,2的回文->3的回文->4的回文..->n的回文
        // 讨论特殊情况，s的长度小于等于2，都视为特殊情况
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        // 讨论一般情况
        String result = s.substring(0, 1);
        // 创建动态规划表格matrix
        // matrix[left][right]表示下标从left到right的子串（以下简写为s[left, right]）是否是回文的
        // 若是，则值为其长度；否则，值为0
        // matrix所有元素初始化为0 此处我没做处理,仅仅是在之后直接做数值判断了
        int[][] matrix = new int[length][length];

        // 用left表示当前子串起始下标，right表示当前子串结束下标。left <= right。
        // 基本情形：
        // 1. left == right，此时子串只有一个字符，一定是回文的，且长度为1
        // 2. left == right - 1，此时子串有两个字符，当且仅当两个字符相同，子串是回文的，且长度为2
        for (int i = 0; i < length; i++) {
            matrix[i][i] = 1;
        }
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                matrix[i][i + 1] = 2;
                if (result.length() < 2) { // 最大长度为2的结果
                    result = s.substring(i, 2);
                }
            }
        }
        // 迭代：从长度为3的子串开始
        // 当且仅当s[left] == s[right]，且s[left + 1, right - 1]是回文的，s[left, right]是回文的
        // 换言之，若s[left] == s[right]，且smatrix[left + 1][right - 1] > 0，则s[left, right] = s[left + 1, right - 1] + 2
        // 注意要按列而不是按行遍历！
        // 原因是，在表格中，s[left + 1][right - 1]是在s[left][right]的右下角
        // 只有按列遍历，才能保证在计算s[left][right]前，s[left + 1][right - 1]已经被计算过
        for (int right = 2; right < length; right++) {
            for (int left = 0; left <= right - 2; left++) {
                if (s.charAt(left) == s.charAt(right) && (matrix[left + 1][right - 1] > 0)) {
                    int cur_length = matrix[left + 1][right - 1] + 2; //回文子串长度增加2
                    matrix[left][right] = cur_length;
                    // 最大长度为cur_length的结果
                    if (result.length() < cur_length) {
                        result = s.substring(left, right + 1);
                    }
                } else {
                    //如果不是回文，则matrix[left][right]应设置为0
                    matrix[left][right] = 0;
                }
            }
        }
        return result;
    }

    /**
     * 动态规划,简化版
     */
    public String longestPalindrome04(String s) {
        int len = s.length();
        if (len <= 1) return s;
        int st = 0, end = 0;
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            for (int j = 0; j < i; j++) {
                if (j + 1 == i) {
                    dp[j][i] = chars[j] == chars[i];
                } else {
                    dp[j][i] = dp[j + 1][i - 1] && chars[j] == chars[i];
                }
                if (dp[j][i] && i - j > end - st) {
                    st = j;
                    end = i;
                }
            }
        }
        return s.substring(st, end + 1);
    }

    static int st, end;

    /**
     * 中心扩展算法的简化版,
     */
    public static String longestPalindrome05(String s) {
        st = 0;
        end = 0;
        int len = s.length();
        if (len <= 1) return s;
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            helper(chars, i, i);
            helper(chars, i, i + 1);
        }
        return s.substring(st, end + 1);
    }

    private static void helper(char[] chars, int l, int r) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            --l;
            ++r;
        }
        if (end - st < r - l - 2) {
            st = l + 1;
            end = r - 1;
        }
    }

    /**
     * manacher(马拉车)算法
     * 定位到最长回文串中的右端点最大值(不一定是最长的最长回文串仅仅是需要知道最大的右端点对应的位置)
     * 方便用于极端区分
     *
     * @param s
     * @return
     */
    public static String longestPalindrome06(String s) {
        String s1 = initStr(s);
        int[] count = new int[s1.length()];
        int pR = -1;
        int index = 0;
        int max = Integer.MIN_VALUE;
        int center = 0;
        for (int i = 0; i < s1.length(); i++) {
            count[i] = pR > i ? Math.min(pR - i, count[2 * index - i]) : 1;
            while (i + count[i] < s1.length() && i - count[i] > -1) {
                if (s1.charAt(i + count[i]) == s1.charAt(i - count[i])) {
                    count[i]++;
                } else {
                    break;
                }
            }
            if (i + count[i] > pR) {
                pR = i + count[i];
                index = i;
            }
            if (count[i] > max) {
                max = count[i];
                center = i;
            }
        }
        max = max - 1;
        //因为对于填充的字符串来说, 除开第一个和最后一个索引,其他的一定存在#...#包裹住,所以除2即可
        return s.substring((center - max) / 2, (center + max) / 2);
    }

    public static String initStr(String str) {
        char[] chars = str.toCharArray();
        StringBuffer sbuffer = new StringBuffer("#");
        for (char c : chars) {
            sbuffer.append(c).append("#");
        }
        return sbuffer.toString();
    }

    /**
     * 滑动窗口,类似最长公共子串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome07(String s) {
        int len = s.length();
        if (len <= 1)
            return s;
        String longest = s.substring(0, 1);
        for (int i = 2; i < len + 1; i++) {
            for (int j = 0; j < len - i + 1; j++) {
                if (isPalindromic(s.substring(j, j + i))) {
                    longest = s.substring(j, j + i);
                    break;
                }
            }
        }
        return longest;
    }

    private static boolean isPalindromic(String substr) {
        int len = substr.length();
        for (int i = 0; i < len / 2; i++) {
            //每次都去比较
            if (substr.charAt(i) != substr.charAt(len - i - 1))
                return false;
        }
        return true;
    }
}
