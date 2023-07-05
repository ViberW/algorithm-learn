package com.welph.leecode.part_500_1000.part_541_560;

/**
 * 给出一些不同颜色的盒子 boxes ，盒子的颜色由不同的正数表示。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。
 * 每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k * k 个积分。
 * 返回 你能获得的最大积分和 。
 * <p>
 * 示例 1：
 * 输入：boxes = [1,3,2,2,2,3,4,3,1]
 * 输出：23
 * 解释：
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 * <p>
 * 示例 2：
 * 输入：boxes = [1,1,1]
 * 输出：9
 * <p>
 * 示例 3：
 * 输入：boxes = [1]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 */
public class Solution546 {

    public static void main(String[] args) {
        System.out.println(removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_301_320.Solution312} 戳气球
     * 这里是需要连续的k个想通过数值
     * dp[i][j][k]  这k是代表和j处相等的连续的最近的一个k [i....j jjjj] 后面
     * 1. dp[i][j-1][0] + (k+1)*(k+1)   计算j以及后面k个j
     * 2. max(dp[i][m][k+1] + dp[m+1][j-1][0]) 说明: m为i到j范围内存在box[m] = box[j]
     *      即i到m 以及后面存在k+1个相等于m的值,   加上消除m+1到j-1的盒子(这里就不涉及后面k个等于box[j]的数据)
     * ------------------------------------------------------好动态规划
     */
    public static int removeBoxes(int[] boxes) {
        int len = boxes.length;
        int[][][] dp = new int[len][len][len + 1]; //最后一个是数量
        return dfs(boxes, 0, len - 1, 0, dp);
    }

    private static int dfs(int[] boxes, int l, int r, int k, int[][][] dp) {
        if (l > r) {
            return 0;
        }
        if (dp[l][r][k] > 0) {
            return dp[l][r][k];
        }
        int or = r; //必须代替一下 否则会重复计算很多次
        int ok = k;
        while (l < or && boxes[or] == boxes[or - 1]) { //[l  or or r] 缩小范围, 找到等于r的相连的值
            ok++;
            or--;
        }
        dp[l][r][k] = dfs(boxes, l, or - 1, 0, dp) + (ok + 1) * (ok + 1);
        for (int i = l; i < or; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k],
                        //前一段和r相同(继续)   中间一段直接重新计算
                        dfs(boxes, l, i, ok + 1, dp) + dfs(boxes, i + 1, or - 1, 0, dp));
            }
        }
        return dp[l][r][k];
    }
}
