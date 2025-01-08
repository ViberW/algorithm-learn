package com.welph.leecode.algorithm.marscode.hard;

/**
 * 给定一个长度为n的排列，使用K排序算法可以通过以下步骤将其从小到大排序：
 *
 * 每次从数列中选择最多K个位置的数，将它们移除后，将剩余的数左对齐。
 * 接着对移除的数进行排序，并将它们放在数列的末尾。
 *
 * 需要计算最少需要多少次这样的操作，才能使数列按升序排列。
 */
public class MinCostByKSort {

    public static int solution(int n, int k, int[] a) {
        //每次选择两个临界之间的数值,如1....2 中间的数值, 按照前后顺序拿出来.
        int pos = 1;
        for (int i : a) {
            if (i == pos) {
                pos++;
            }
        }
        //意思就是, 将不在顺序的数字拿出来, 按照小到大拿取的操作
        //n - pos + 1 未排序的个数,    + k - 1 代表向上取整
        return (n - pos + 1 + k - 1) / k;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 1, new int[]{1, 2, 3, 4, 5}) == 0);
        //第一次将3,2移除=> 15423 第二次将5,4移除=> 12345
        //或者 34 移除, 15234, 再 移除5 12345
        System.out.println(solution(5, 2, new int[]{1, 3, 5, 4, 2}) == 2);
        System.out.println(solution(6, 3, new int[]{6, 5, 3, 1, 4, 2}) == 2);
    }
}
