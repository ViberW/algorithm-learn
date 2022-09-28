package com.welph.leecode.part_1_500.part_481_500;

import java.util.Arrays;
import java.util.Random;

/**
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi]
 * 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。
 * 设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
 * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
 * 请注意，整数点是具有整数坐标的点。
 * <p>
 * 实现Solution类:
 * Solution(int[][] rects)用给定的矩形数组rects 初始化对象。
 * int[] pick()返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 * <p>
 * 示例 1：
 * 输入:
 * ["Solution", "pick", "pick", "pick", "pick", "pick"]
 * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
 * 输出:
 * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
 * 解释：
 * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 * solution.pick(); // 返回 [1, -2]
 * solution.pick(); // 返回 [1, -1]
 * solution.pick(); // 返回 [-1, -2]
 * solution.pick(); // 返回 [-2, -2]
 * solution.pick(); // 返回 [0, 0]
 * <p>
 * 提示：
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -10^9<= ai< xi<= 10^9
 * -10^9<= bi< yi<= 10^9
 * xi- ai<= 2000
 * yi- bi<= 2000
 * 所有的矩形不重叠。
 * pick 最多被调用104次。
 */
public class Solution497 {

    public static void main(String[] args) {
        Solution obj = new Solution(new int[][]{{82918473, -57180867, 82918476, -57180863},
                {83793579, 18088559, 83793580, 18088560},
                {66574245, 26243152, 66574246, 26243153},
                {72983930, 11921716, 72983934, 11921720}});
        System.out.println(Arrays.toString(obj.pick()));
    }

    static class Solution {
        static Random random = new Random();
        int[][] rects;
        int[] area;

        public Solution(int[][] rects) {
            this.rects = rects;
            this.area = new int[rects.length];
            // 治理考虑的面积中包含点的数量. 而非面积
            for (int i = 0; i < rects.length; i++) {
                area[i] = (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
            }

        }

        public int[] pick() {
            int total = area[0];
            int target = 0;
            int r = random.nextInt(total);
            int k = r;
            for (int i = 1; i < area.length; i++) {
                total += area[i];
                if ((r = random.nextInt(total)) < area[i]) {
                    target = i;
                    k = r;
                }
            }
            int[] rect = rects[target];
            int a = rect[0], b = rect[1], y = rect[3];
            int col = y - b + 1;
            int da = k / col;//属于第几个轴
            int db = k - col * da; //找到x轴, 就找对应的y
            return new int[]{a + da, b + db};
        }
    }

}
