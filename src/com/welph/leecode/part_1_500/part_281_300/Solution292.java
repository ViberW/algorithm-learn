package com.welph.leecode.part_1_500.part_281_300;

/**
 * 你和你的朋友，两个人一起玩 Nim 游戏：
 * <p>
 * 桌子上有一堆石头。
 * 你们轮流进行自己的回合，你作为先手。
 * 每一回合，轮到的人拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。
 * 如果可以赢，返回 true；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：false
 * 解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
 * 因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：n = 2
 * 输出：true
 * <p>
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class Solution292 {

    public static void main(String[] args) {
        System.out.println(canWinNim(1));
        System.out.println(canWinNim(2));
        System.out.println(canWinNim(3));
        System.out.println(canWinNim(4));
        System.out.println(canWinNim(5));
        System.out.println(canWinNim(6));
        System.out.println(canWinNim(7));
        System.out.println(canWinNim(8));
    }

    //巴什博弈，n%(m+1)!=0时，先手总是会赢的
    //todo 这数学弄的. 脑筋急转弯
    public static boolean canWinNim(int n) {
        return n % (3 + 1) != 0;
    }
}
