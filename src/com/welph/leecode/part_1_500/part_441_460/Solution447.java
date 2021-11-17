package com.welph.leecode.part_1_500.part_441_460;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定平面上n 对 互不相同 的点points ，其中 points[i] = [xi, yi] 。
 * 回旋镖 是由点(i, j, k) 表示的元组 ，其中i和j之间的距离和i和k之间的欧式距离相等（需要考虑元组的顺序）。
 * 返回平面上所有回旋镖的数量。
 * <p>
 * 示例 1：
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2<></>
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * <p>
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：points = [[1,1]]
 * 输出：0
 * <p>
 * 提示：
 * n ==points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * 所有点都 互不相同
 */
public class Solution447 {

    public static void main(String[] args) {
        System.out.println(numberOfBoomerangs(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println(numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
        System.out.println(numberOfBoomerangs(new int[][]{{1, 1}}));
        System.out.println(numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}}));
    }

    /**
     * 欧几里得距离: distance = math.sqrt(pow(xi-xj) + pow(yi-yj));
     * ----------- 这里仅仅需要计算{差值平方和}即可
     * 若(i,j,k)为一组合格的数据, 则(i,k,j)也是一组合格的数据
     * ------------时间37%, 空间利用太多了5%
     */
    public static int numberOfBoomerangs(int[][] points) {
        int ret = 0;
        int length = points.length;
        //计算不同点之间的距离, 哈希表存储数据
        int val;
        //到i的距离
        Map<Integer, Integer>[] map = new Map[length];
        for (int i = 0; i < length; i++) {
            map[i] = new HashMap<>();
        }
        for (int i = 0; i < length; i++) {
            //todo 减少空间的正确方法: 这里仅仅需要从头到尾计算就可以, 不用关心重复计算 减少空间使用
            // for (int j = 0; j < length; j++) {
            for (int j = i + 1; j < length; j++) {
                val = (int) (Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                Integer count = map[i].getOrDefault(val, 0);
                if (count > 0) {
                    ret += 2 * count;
                }
                map[i].put(val, count + 1);
                count = map[j].getOrDefault(val, 0);
                if (count > 0) {
                    ret += 2 * count;
                }
                map[j].put(val, count + 1);
            }
        }
        return ret;
    }
}
