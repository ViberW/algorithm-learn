package com.welph.leecode.part_361_380;

/**
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * <p>
 * 示例 2：
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 * <p>
 * 进阶：如果行数远大于列数，该如何设计解决方案？
 */
public class Solution363 {

    public static void main(String[] args) {
        System.out.println(maxSumSubmatrix(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2));
        System.out.println(maxSumSubmatrix(new int[][]{{2, 2, -1}}, 3));
        System.out.println(maxSumSubmatrix(new int[][]{{2, 2, -1}}, 0));
    }

    /**
     * 动态规划,
     */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] + matrix[i][j] - dp[i][j];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int s = i + 1; s <= m; s++) {
                    for (int p = j + 1; p <= n; p++) {  //todo 下面是使用TreeMap 有序集合,获取到最大值.
                        int r = dp[s][p] - dp[s][j] + dp[i][j] - dp[i][p];
                        if (r <= k) {
                            max = Math.max(max, r);
                        }
                    }
                }
            }
        }
        return max;
    }

    /*for(int down=up;down<m;down++){
        //创建一个有序集合维护面积
        TreeSet<Integer> set=new TreeSet<>();
        //遍历子矩阵的右边界
        for(int r=0;r<n;r++){
            //当前右边界到大矩阵左边界的有上下限的面积
            right=sum[down+1][r+1]-sum[up][r+1];

            set.add(0);
            Integer left= set.ceiling(right-k);
            if(left != null){
                int temp=right-left;
                max=Math.max(max,temp);
            }
            set.add(right);
        }
    }*/
}
