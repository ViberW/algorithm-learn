package com.welph.leecode.part_381_400;

/**
 * 给定一个从1 到 n 排序的整数列表。
 * 首先，从左到右，从第一个数字开始，每隔一个数字进行删除，直到列表的末尾。
 * 第二步，在剩下的数字中，从右到左，从倒数第一个数字开始，每隔一个数字进行删除，直到列表开头。
 * 我们不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 返回长度为 n 的列表中，最后剩下的数字。
 * <p>
 * 示例：
 * <p>
 * 输入:
 * n = 9,
 * 1 2 3 4 5 6 7 8 9
 * 2 4 6 8
 * 2 6
 * 6
 * <p>
 * 输出:
 * 6
 */
public class Solution390 {

    public static void main(String[] args) {
        System.out.println(lastRemaining(9));
    }

    /**
     * 从左到右: 删除了奇数. 剩下的都是偶数
     * 从右到左: 若长度为奇数, 则删除了奇数, 否则偶数
     * ---------------------
     * 无论从左到右， 还是从右到左，每次都要消除 一半的数
     * 但是，从左到右，每次都要消除第一个
     * 而从右到左，只要数组为奇数个，才会消除第一个。
     * --------------------
     */
    public static int lastRemaining(int n) {
        int remain = n;
        boolean flag = true; //是否从左到右
        int res = 1;
        int step = 1;
        while (remain > 1) {
            // 当左到右 或者剩余个数为奇数
            if (flag || remain % 2 == 1) //顶级李姐: 假定目标值就是那第一个值, 若是删除了,说明之后一个值一定是没有删除的.
                res += step;
            flag = !flag;
            step *= 2;  //每次更新两个数之间的间隔大小
            remain /= 2; //剩余的数据个数
        }
        return res;
    }

    /**
     * ------------也是666;
     * 假如输入为 n，我们使用 f(n) 表示 从左到右(forward) 的最终结果，使用 b(n)表示 从右到左(backward) 的最终结果。则：
     * <p>
     * 当 n = 1 时，存在 f(n) = 1, b(n) = 1
     * 对于任意 n，存在 f(n) + b(n) = n + 1
     * 对于 n > 2 的情况下，f(n) = 2 * b(n / 2)
     * 所以：f(n) = 2 * (n / 2 + 1 - f(n / 2))
     */
    public int lastRemaining2(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining2(n / 2));
    }

}
