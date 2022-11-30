package com.welph.leecode.part_500_1000.part_541_560;

import java.util.Arrays;

/**
 * 给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。
 * 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，
 * 才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应该含有冗余的括号。
 * <p>
 * 示例：
 * 输入: [1000,100,10,2]
 * 输出: "1000/(100/10/2)"
 * 解释:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * 但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
 * 因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
 * <p>
 * 其他用例:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 * 说明:
 * 输入数组的长度在 [1, 10] 之间。
 * 数组中每个元素的大小都在 [2, 1000] 之间。
 * 每个测试用例只有一个最优除法解。
 */
public class Solution553 {

    public static void main(String[] args) {
//        System.out.println(optimalDivision(new int[]{1000, 100, 10, 2}));
        System.out.println(optimalDivision(new int[]{2, 3, 4}));
    }

    /**
     * 动态规划
     * dp[i] 代表在i处设置/符号
     * dp[l][i] 的最大值  dp[i][r]的最小值
     * ----------
     * 题解2: [n0,n1,n2...nx]  因为n0始终是作为分母的, n1一定是分子 剩下的仅仅需要保证处于分子中的分子即可.
     *  最小值: n0/n1/n2.../nx
     *  最大值: n0/(n1/n2/n3/n4.../nx)
     */
    public static String optimalDivision(int[] nums) {
        int n = nums.length;
        Option[][] dp = new Option[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = new Option();
            dp[i][i].max = nums[i];
            dp[i][i].min = nums[i];
            dp[i][i].maxStr = String.valueOf(nums[i]);
            dp[i][i].minStr = String.valueOf(nums[i]);
        }
        //先小组合  todo 这里的小步走 扩大步的形式动态规划 值得记录(代替了dfs)
        for (int i = 1; i < n; i++) {
            for (int j = 0; j + i < n; j++) {
                dp[j][j + i] = new Option();
                //j为起点 j+i为终点
                for (int k = j; k < j + i; k++) {
                    //计算最大值
                    if (dp[j][j + i].max < dp[j][k].max / dp[k + 1][j + i].min) {
                        dp[j][j + i].max = dp[j][k].max / dp[k + 1][j + i].min;
                        if (k + 1 != j + i) {//后面需要加括号
                            dp[j][j + i].maxStr = dp[j][k].maxStr + "/(" + dp[k + 1][j + i].minStr + ")";
                        } else {
                            dp[j][j + i].maxStr = dp[j][k].maxStr + "/" + dp[k + 1][j + i].minStr;
                        }
                    }
                    //计算最小值
                    if (dp[j][j + i].min > dp[j][k].min / dp[k + 1][j + i].max) {
                        dp[j][j + i].min = dp[j][k].min / dp[k + 1][j + i].max;
                        if (k + 1 != j + i) {//后面需要加括号
                            dp[j][j + i].minStr = dp[j][k].minStr + "/(" + dp[k + 1][j + i].maxStr + ")";
                        } else {
                            dp[j][j + i].minStr = dp[j][k].minStr + "/" + dp[k + 1][j + i].maxStr;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1].maxStr;
    }

    static class Option {
        public double max = 0;
        public double min = 1000;
        public String maxStr;
        public String minStr;
    }

}
