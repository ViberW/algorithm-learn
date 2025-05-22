package com.welph.leecode.part_500_1000.part_661_680;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 房间中有 n 只已经打开的灯泡，编号从 1 到 n 。墙上挂着 4 个开关 。
 *
 * 这 4 个开关各自都具有不同的功能，其中：
 *
 * 开关 1 ：反转当前所有灯的状态（即开变为关，关变为开）
 * 开关 2 ：反转编号为偶数的灯的状态（即 0, 2, 4, ...）
 * 开关 3 ：反转编号为奇数的灯的状态（即 1, 3, ...）
 * 开关 4 ：反转编号为 j = 3k + 1 的灯的状态，其中 k = 0, 1, 2, ...（即 1, 4, 7, 10, ...）
 * 你必须 恰好 按压开关 presses 次。每次按压，你都需要从 4 个开关中选出一个来执行按压操作。
 *
 * 给你两个整数 n 和 presses ，执行完所有按压之后，返回 不同可能状态 的数量。
 *
 * 示例 1：
 * 输入：n = 1, presses = 1
 * 输出：2
 * 解释：状态可以是：
 * - 按压开关 1 ，[关]
 * - 按压开关 2 ，[开]
 *
 * 示例 2：
 * 输入：n = 2, presses = 1
 * 输出：3
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关]
 * - 按压开关 2 ，[开, 关]
 * - 按压开关 3 ，[关, 开]
 *
 * 示例 3：
 * 输入：n = 3, presses = 1
 * 输出：4
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关, 关]
 * - 按压开关 2 ，[关, 开, 关]
 * - 按压开关 3 ，[开, 关, 开]
 * - 按压开关 4 ，[关, 开, 开]
 *
 *
 * 提示：
 * 1 <= n <= 1000
 * 0 <= presses <= 1000
 */
public class Solution672 {

    /**
     * {@link com.welph.leecode.part_1_500.part_301_320.Solution319}
     */
    public static void main(String[] args) {
        System.out.println(flipLights(1, 1));
        System.out.println(flipLights(3, 1));
    }

    //尽管n很大, 但每隔一段, 灯泡的状态是重复的  每6个一组

    /**
     *     6k+1会受到1、3、4开关的影响
     *     6k+2会受到1、2开关的影响
     *     6k+3会受到1、3开关的影响
     *     6k+4会受到1、2、4开关的影响
     *     6k+5会受到1、3开关的影响
     *     6k+6会受到1、2开关的影响
     *
     * 其中 6k+2和6k+6 一样, 6k+3 和 6k+5一样, 即只需要考虑前4个即可
     *
     * 假设按下4种开关次数分别为 a b c d
     * 则6k+1 => (a+c+d)%2  6k+2 => (a+b)%2  6k+3 => (a+c)%2  6k+4 => (a+b+d)%2
     * ---------------------------------------------------------------------------------------------------
     * 由于①和③都受到1、3开关的影响，所以若①③状态相同，则d必然为偶数；若①③状态不同，则d必然为奇数
     * 由于②和④都受到1、2开关的影响，并且④和d有关系，所以若d为偶数，则②④状态相同；若d为奇数，则②④状态不同
     *
     * 所以: 通过①②③的状态来确定④的状态
     * -----------------------------------------------------------------------------------------------
     * 若初始为 111
     * 经过按 1次:  000   101  010  011
     * 经过按 2次:  000 -> 111   010   101  100
     *             101 -> 010   111   000  001
     *             010 -> 101   000   111  110
     *             011 -> 100   001   110  111
     * 说明: 按2次后, 少了011,  但它本质在第1次就存在
     * 因此: presses >=3 结果为8种
     */
    public static int flipLights(int n, int presses) {
        //不按开关
        if (presses == 0) {
            return 1;
        }
        //特殊情况处理
        if (n == 1) {
            return 2;
        } else if (n == 2) {
            //特殊情况
            return presses == 1 ? 3 : 4;
        } else {
            //n >= 3
            return presses == 1 ? 4 : presses == 2 ? 7 : 8;
        }
    }

    /**
     * 官方题解: 按照上面的思路, 最终决定的是 4个灯泡. 后面都是类似的
     */
    public static int flipLights1(int n, int presses) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 1 << 4; i++) {
            int[] pressArr = new int[4];
            for (int j = 0; j < 4; j++) {
                pressArr[j] = (i >> j) & 1;  //每种方法的按的方式
            }
            int sum = Arrays.stream(pressArr).sum();
            if (sum % 2 == presses % 2 && sum <= presses) {
                //6k+1 => (a+c+d)%2
                int status = pressArr[0] ^ pressArr[2] ^ pressArr[3];
                if (n >= 2) {
                    //6k+2 => (a+b)%2
                    status |= (pressArr[0] ^ pressArr[1]) << 1;
                }
                if (n >= 3) {
                    //  6k+3 => (a+c)%2
                    status |= (pressArr[0] ^ pressArr[2]) << 2;
                }
                if (n >= 4) {
                    //6k+4 => (a+b+d)%2
                    status |= (pressArr[0] ^ pressArr[1] ^ pressArr[3]) << 3;
                }
                seen.add(status);
            }
        }
        return seen.size();
    }
}
