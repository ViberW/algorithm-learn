package com.welph.leecode.part_1_500.part_361_380;

/**
 * 猜数字游戏的规则如下：
 * <p>
 * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * <p>
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 * <p>
 * 示例 1：
 * 输入：n = 10, pick = 6
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：n = 1, pick = 1
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：n = 2, pick = 1
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：n = 2, pick = 2
 * 输出：2
 * <p>
 * 提示：
 * 1 <= n <= 231 - 1
 * 1 <= pick <= n
 */
public class Solution374 {

    public static void main(String[] args) {

    }

    public static int guessNumber(int n) {
        //折中
        int l = 1;
        int r = n;
        int mid;
        int guess;
        while (l <= r) {
            mid = l + (r - l) / 2;
            guess = guess(mid);
            if (guess == 1) {
                l = mid + 1;
            } else if (guess == -1) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return 0;
    }

    /**
     * leetcode提供的方法
     */
    static int guess(int num) {
        return 0;
    }
}
