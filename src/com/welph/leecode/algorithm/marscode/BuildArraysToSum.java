package com.welph.leecode.algorithm.marscode;

/**
 * 小R有一个长度为n的数组,数组相邻元素的差值最多为1,即|a(i)-a(i+1)|<=1,且数组
 * 中的元素都是正整数,即a(i)>1。现在已知数组的长度为n,数组的的和为m,
 * 小R想知道所有符合条件的数组中,a(p)的最大值是多少。 即第p-1位的值能够到的最大值.
 * 约束条件:
 *  n,m,p为整数,数据范围1<=p<=n<=m
 */
public class BuildArraysToSum {
    public static int solution(int n, int m, int p) {
        //升序, 中间存在平台的那种, 或者是先升后降低
        //n(n+1)<=2*m    n^2+n-2m<=0 解中的最大值 (-b ±√(b^2 - 4ac)) / 2a
        //算出最大值N

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 5, 2) == 2);
        System.out.println(solution(3, 7, 1) == 3);
        System.out.println(solution(5, 9, 3) == 3);
    }
}
