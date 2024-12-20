package com.welph.leecode.algorithm.marscode;

/**
 * 小C拿到了一个数组，他可以进行最多一次操作：
 * 将一个元素修改为任意给定的x。
 *
 * 小C想知道，经过这次修改后，能够得到的连续子数组的最大和是多少。
 */
public class SubArrayMaxSum {

    //{@link Solution53}
    public static int solution(int n, int x, int[] a) {
        //类似力扣的53题, 但这里需要考虑x改替换哪个值.
        int pre0 = a[0], result0 = a[0]; //当前未修改
        int pre1 = x, result1 = x; //当前已修改
        for (int i = 1; i < n; i++) {
            int val = a[i];
            //以前修改
            pre1 = Math.max(pre1 + val, val);
            //现在修改
            pre1 = Math.max(Math.max(pre0 + x, x), pre1);

            result1 = Math.max(pre1, result1);

            //不修改
            pre0 = Math.max(pre0 + val, val);
            result0 = Math.max(pre0, result0);
        }
        return result1;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 10, new int[]{5, -1, -5, -3, 2}) == 15);
        System.out.println(solution(2, -3, new int[]{-5, -2}) == -2);
        System.out.println(solution(6, 10, new int[]{4, -2, -11, -1, 4, -1}) == 15);

        System.out.println(solution(16, 1, new int[]{17, 17, 4, 13, 11, 3, 6, 13, 7, 13, 13, 13, 6, 16, 6, 11}));
    }
}
