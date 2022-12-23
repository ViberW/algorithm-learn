package com.welph.leecode.part_1_500.part_61_80;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class Solution72 {

    //这个题目能够解决协同文档的类似操作
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance2("horse", "ros"));
    }

    /**
     * 实在没思路， 查看了他人的文档解释
     * ----------------
     * 综合了数据结构与算法之美的例子.使用动态规划,从下面的回溯算法中抽取
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();
        int m = word1.length();
        int n = word2.length();
        int[][] min = new int[m][n];//a为i,b为j时的最小编辑距离
        //初始化
        for (int i = 0; i < m; i++) {
            if (a[i] == b[0]) {
                min[i][0] = i;
            } else if (i != 0) {
                min[i][0] = min[i - 1][0] + 1;
            } else {
                min[i][0] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (a[0] == b[i]) {
                min[0][i] = i;//在a[0]前添加多个元素
            } else if (i != 0) {
                min[0][i] = min[0][i - 1] + 1;
            } else {
                min[0][i] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] == b[j]) {
                    min[i][j] = Math.min(min[i - 1][j] + 1, min[i - 1][j - 1]);
                } else {
                    min[i][j] = Math.min(min[i - 1][j] + 1, min[i - 1][j - 1] + 1);
                }
            }
        }

        return min[m - 1][n - 1];
    }

    static int minDist = Integer.MAX_VALUE;

    /**
     * 使用回溯算法去实现
     */
    public static int minDistance2(String word1, String word2) {
        //如果ai =bj 则尝试ai+1 = bj=1
        //如果ai!=bj 则 删除ai | 替换ai | 新增ai
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();
        minDistance2(0, 0, 0, a, a.length, b, b.length);
        return minDist;
    }

    public static void minDistance2(int i, int j, int min, char[] a, int m, char[] b, int n) {
        if (i == m || j == n) {
            if (i < m) min += (m - i); //说明还有剩余,需要删除掉剩余
            if (j < n) min += (n - j); //说明匹配缺少,需要新增额外
            if (minDist > min) {
                minDist = min;
            }
            return;
        }
        if (a[i] == b[j]) {
            minDistance2(i + 1, j + 1, min, a, m, b, n);
        } else {
            minDistance2(i, j + 1, min + 1, a, m, b, n);//新增b[j] .之后考察a[i]和b[j+1]
            minDistance2(i + 1, j, min + 1, a, m, b, n);//删除a[i] .之后考察a[i+1]和b[j]
            minDistance2(i + 1, j + 1, min + 1, a, m, b, n);//a[i]替换为b[j] .之后考察a[i+1]和b[j+1]
        }
    }
}
