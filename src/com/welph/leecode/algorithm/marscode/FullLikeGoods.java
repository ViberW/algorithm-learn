package com.welph.leecode.algorithm.marscode;

/**
 * 小F正在超市购物，有 n个商品摆成一排，每个商品的价格为ai，小F对它的喜爱度为bi 。
 * 所有商品的价格都是偶数。超市有一个活动：
 * 当小F以原价购买某件商品时，她可以用半价购买下一件右边相邻的商品
 * （当然也可以选择以原价购买，这样下一件商品仍有机会半价购买）。
 *
 * 然而，如果小F半价购买了一件商品，那么下一件相邻的商品只能原价购买。
 *
 * 小F手中有x金额，她希望通过购物活动，尽可能最大化她获得的喜爱度总和，并且购买的商品总价格不能超过她的初始金额x。
 */
public class FullLikeGoods {

    public static int solution(int n, int x, int[] a, int[] b) {
        //DP 背包问题
        x *= 2;
        int[][] f = new int[x + 1][3]; //0代表不买 1代表半价购买 2代表原价购买
        f[0][1] = f[0][2] = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = x; j > 0; j--) {
                //忽略
                f[j][0] = Math.max(f[j][0], Math.max(f[j][1], f[j][2]));
                if (j >= a[i]) {
                    //半价
                    if (f[j - a[i]][2] > 0) {
                        f[j][1] = f[j - a[i]][2] + b[i];
                    } else {
                        f[j][1] = 0;
                    }
                    //原价
                    if (j >= 2 * a[i]) {
                        int next = j - 2 * a[i];
                        f[j][2] = Math.max(f[next][0], Math.max(f[next][1], f[next][2])) + b[i];
                    } else {
                        f[j][2] = 0;
                    }
                } else {
                    f[j][1] = f[j][2] = 0;
                }
            }
        }
        return Math.max(f[x][0], Math.max(f[x][1], f[x][2]));
    }

    public static void main(String[] args) {
//        System.out.println(solution(4, 7, new int[]{2, 2, 6, 2}, new int[]{3, 4, 5, 1}) == 12);
//        System.out.println(solution(3, 10, new int[]{4, 4, 4}, new int[]{2, 3, 5}) == 10);
//        System.out.println(solution(5, 8, new int[]{2, 4, 4, 6, 2}, new int[]{1, 2, 3, 4, 5}) == 10);
//        System.out.println(solution(13, 7, new int[]{28, 12, 10, 16, 12, 34, 32, 8, 12, 30, 12, 28, 22},
//                new int[]{26, 30, 12, 32, 14, 22, 20, 18, 14, 8, 6, 6, 2}));
        System.out.println(solution(7, 11, new int[]{8, 14, 6, 20, 30, 28, 8},
                new int[]{10, 22, 28, 26, 28, 26, 34}));
    }
}
