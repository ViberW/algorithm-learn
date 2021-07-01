package com.welph.leecode.part_301_320;

/**
 * 初始时有n个灯泡处于关闭状态。
 * 对某个灯泡切换开关意味着：如果灯泡状态为关闭，那该灯泡就会被开启；而灯泡状态为开启，那该灯泡就会被关闭。
 * 第 1 轮，每个灯泡切换一次开关。即，打开所有的灯泡。
 * 第 2 轮，每两个灯泡切换一次开关。 即，每两个灯泡关闭一个。
 * 第 3 轮，每三个灯泡切换一次开关。
 * 第i 轮，每i个灯泡切换一次开关。 而第n轮，你只切换最后一个灯泡的开关。
 * 找出n轮后有多少个亮着的灯泡。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：1
 * 解释：
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 * 你应该返回 1，因为只有一个灯泡还亮着。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * 0 <= n <= 109
 */
public class Solution319 {

    public static void main(String[] args) {
        System.out.println(bulbSwitch(5));
    }

    //一旦经历两次,则会到关闭状态.
    //若是素数, 则一定是关闭的, 因为1和i关闭了当前的.
    //意思就是找到那些值 的约数是奇数的.(包括本身)
    //如1, 4, 9,  16
    //解析: 若是想要一个值的因数为奇数, 则一定是X^2 =Value
    //这样才能保证,一定是奇数
    //题目答案就是 对N开根号, 的最大匹配值
    public static int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
