package com.welph.leecode.part_1_500.part_81_100;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 *
 */
public class Solution96 {

    public static void main(String[] args) {
        System.out.println(numTrees(5));
    }

    /**
     * 思考: i与i-1的关系:
     * count(i) = count(i-1) * 2 + count(i-1的右子树高度)
     * <p>
     * 为什么? 假设i=3 i-1=2
     * i =2  存在 1   2
     * .          \  /
     * .          2  1
     * 那么i=3时 本质就是遍历所有i=2的树中, 除了本身作为根节点,其他的都是找右节点插入直到原右节点为空.
     *
     * Tips这个题目本质是对逻辑的归纳 时间100%,内存消耗 7.7%
     */
    public static int numTrees(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //需要统计右子树的长度 + 本身的长度
        int[] bp = new int[n + 1];
        bp[0] = 1;
        int count = 1;
        int rightCount = 0;
        int leftOver;
        int i1;
        for (int i = 2; i < n; i++) {
            count = 2 * count + rightCount;
            /**
             * 再次思考:右节点高度为多少?
             *   i=2时 i-1分别为 0
             *                 0*(0+1)  0
             *   i=3时 i-1分别为 1, 0  size
             *   i=4时 看上面的图: i-1分别为: 0,1,0,1,2  size + count  012 01
             *                             01 012 01 012 0123
             *
             *   i=5时 i-1分别为:  0,2,2,0,1,0,1,0,1,2,0,1,2,3  最高位仅有 1个
             *   0 1 2 3  ...n
             *   1 3 6 10    (n+1)(n+2)/2 的个数乘积/
             *
             *   每次计算i-1的右高度,则为: i-2 的所有的 0的个数 1个数
             *                 前面每次所有的
             *   rightCount(i) = 每次对i-1的
             */
            rightCount = 0;
            leftOver = 0;
            for (int j = 0; j <= i; j++) {
                i1 = bp[j];
                bp[j] = 0;
                rightCount += ((j + 1) * (j + 2) / 2) * i1;
                for (int k = 0; k <= j; k++) {
                    bp[k] += i1;
                }
                bp[j] += leftOver;
                leftOver = i1;
            }

        }
        return 2 * count + rightCount;
    }

    //todo  看了其他人的, 有种动态规划  条理性较好. 等有时间再写吧
}
