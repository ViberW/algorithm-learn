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
        // p处的最大值, 说明是个凸形状
        //可以多次遍历
        /*
            1. 最初每个位置放入1
            2. 第一次p处+1,  消耗剩余的1, 第二次p-1,p,p+1处+1. 则消耗剩余的3. ....不断拓展. 不满足就当前最大值
         */
        m -= n;//第一次所有值为1
        int result = 1;
        int l = p - 1;
        int r = p;
        while (m >= r - l) {
            result++;
            m -= r - l;
            if (r - l == n) { //快速处理
                result += m / n;
                break;
            } else {
                if (l > 0) {
                    l--;
                }
                if (r < n) {
                    r++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 5, 2) == 2);
        System.out.println(solution(3, 7, 1) == 3);
        System.out.println(solution(5, 9, 3) == 3);
        System.out.println(solution(2, 10, 1));
    }
}
