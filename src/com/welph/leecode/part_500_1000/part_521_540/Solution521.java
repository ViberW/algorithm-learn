package com.welph.leecode.part_500_1000.part_521_540;

/**
 * 给你两个字符串 a 和 b，请返回 这两个字符串中 最长的特殊序列 的长度。如果不存在，则返回 -1 。
 * <p>
 * 「最长特殊序列」 定义如下：该序列为 某字符串独有的最长子序列（即不能是其他字符串的子序列） 。
 * <p>
 * 字符串 s 的子序列是在从 s 中删除任意数量的字符后可以获得的字符串。
 * <p>
 * 例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。
 * "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。
 * <p>
 * <p>
 * 示例 1：
 * 输入: a = "aba", b = "cdc"
 * 输出: 3
 * 解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
 * <p>
 * 示例 2：
 * 输入：a = "aaa", b = "bbb"
 * 输出：3
 * 解释: 最长特殊序列是 "aaa" 和 "bbb" 。
 * <p>
 * 示例 3：
 * 输入：a = "aaa", b = "aaa"
 * 输出：-1
 * 解释: 字符串 a 的每个子序列也是字符串 b 的每个子序列。同样，字符串 b 的每个子序列也是字符串 a 的子序列。
 * <p>
 * <p>
 * 提示：
 * 1 <= a.length, b.length <= 100
 * a 和 b 由小写英文字母组成
 */
public class Solution521 {

    public static void main(String[] args) {
        System.out.println(findLUSlength("aba", "cdc"));
    }

    /**
     * 题目给我看麻了
     */
    public static int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }

    // 这个至少是一种算法-动态规划 当然不如上面的快速判断
    public int findLUSlength2(String a, String b) {
        if (a == b)
            return -1;
        int m = a.length(), n = b.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            // 二维数组中第一行和第一列为特殊值
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] == 0 ? 0 // 此时i-1和j-1基本是相等的, 无特殊子串, 那么最后点相等,则为0
                      
                            : Math.max(Math.max(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp[m][n];
    }

}
