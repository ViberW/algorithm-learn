package com.welph.leecode.part_1_500.part_461_480;

import java.util.HashMap;
import java.util.Map;

import com.welph.leecode.part_1_500.part_281_300.Solution292;
import com.welph.leecode.part_1_500.part_361_380.Solution375;

/**
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，
 * 先使得累计整数和达到或超过 100 的玩家，即为胜者。
 * <p>
 * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * <p>
 * 给定一个整数maxChoosableInteger（整数池中可选择的最大数）和另一个整数desiredTotal（累计和），
 * 判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
 * 你可以假设maxChoosableInteger不会大于 20，desiredTotal不会大于 300。
 * <p>
 * 示例：
 * 输入：
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * 输出：
 * false
 * <p>
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 */
public class Solution464 {

    public static void main(String[] args) {
        System.out.println(canIWin(10, 11));
    }

    /**
     * {@link Solution292}
     * {@link Solution375}
     */
    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if (maxChoosableInteger * (1 + maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }
        Map<Integer, Boolean> visited = new HashMap<>();
        return canIWin(maxChoosableInteger, desiredTotal, 0, visited);
    }

    /**
     * 
     * @param choose
     * @param total
     * @param state 这里其实是一个二进制, 通过位数来表示那些数据被选择了
     * @param visited
     * @return
     */
    static boolean canIWin(int choose, int total, int state, Map<Integer, Boolean> visited) {
        if (visited.containsKey(state)) {
            return visited.get(state);
        }
        for (int i = 0; i < choose; i++) {
            int cur = (1 << i);
            if ((cur & state) == 0) {//说明没有处理过
                //若本次选择成功 或者之后一次必输, 那么代表当前次必赢
                if (total <= (i + 1) || !canIWin(choose, total - (i + 1),
                        state | cur, visited)) {
                    visited.put(state, true);
                    return true;
                }
            }
        }
        visited.put(state, false);
        return false;
    }

    // 同样的思路 但是却处理很好的方法
    public static boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0 || maxChoosableInteger <= 0) {
            return true;
        }
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        int mask = (1 << maxChoosableInteger) - 1; //提前标记所有位为1
        int[] dp = new int[mask + 1];
        return process3(maxChoosableInteger, desiredTotal, mask, dp);
    }

    private static boolean process3(int maxChoosableInteger, int desiredTotal, int mask, int[] dp) {
        if (desiredTotal <= 0) {
            return false;
        }
        if (dp[mask] != 0) {
            return dp[mask] == 1 ? true : false;
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((1 << (i - 1)) & mask) != 0) {
                int cur = mask & (~(1 << (i - 1)));
                if (!process3(maxChoosableInteger,desiredTotal - i, cur, dp)) {
                    dp[mask] = 1;
                    return true;
                }
            }
        }
        dp[mask] = -1;
        return false;
    }
}
