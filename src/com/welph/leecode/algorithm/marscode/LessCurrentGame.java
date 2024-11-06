package com.welph.leecode.algorithm.marscode;

public class LessCurrentGame {

    public static String solution(int n, int[] a) {
        //判断i, 是否存在左边比i小的数据
        int min = a[0];
        int success = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > min) {
                success++;
            } else {
                min = a[i];
            }
        }
        if (success == 0) {
            return "0/1";
        } else if (success == n) {
            return "1/1";
        } else {
            //找到最大公约数
            int mod = gcd(n, success);
            return (success / mod) + "/" + (n / mod);
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{3, 1, 5, 4, 3}).equals("3/5"));
        System.out.println(solution(6, new int[]{6, 2, 9, 7, 4, 3}).equals("2/3"));
        System.out.println(solution(4, new int[]{8, 5, 6, 3}).equals("1/4"));
    }

}
