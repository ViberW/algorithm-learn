package com.welph.leecode.part_1_500.part_381_400;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 * <p>
 * 例如，
 * 给定 n =13，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * <p>
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000
 */
public class Solution386 {

    public static void main(String[] args) {
        System.out.println(lexicalOrder2(120));
    }

    /**
     * 字典序
     */
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (i > n) {
                break;
            }
            res.add(i);
            lexical(i, res, n);
        }
        return res;
    }

    static void lexical(int n, List<Integer> res, int max) {
        n *= 10;
        int v;
        for (int i = 0; i < 10; i++) {
            v = n + i;
            if (v > max) {
                break;
            }
            res.add(v);
            lexical(v, res, max);
        }
    }

    /* 官方题解 */
    // 这种写法更好点, 少了深度递归的空间消耗
    public static List<Integer> lexicalOrder2(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ret.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {// 到这说明number不能再乘10

                while (number % 10 == 9 || number + 1 > n) { // 尝试进位,后面num++, 再number*10
                    number /= 10;
                }
                number++;
            }
        }
        return ret;
    }
}
