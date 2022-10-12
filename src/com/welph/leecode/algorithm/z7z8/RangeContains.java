package com.welph.leecode.algorithm.z7z8;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给了很多个区间，求对于每一个区间，它属于多少个区间的真子集
 * 即 [s1,e1] 与 [s2,e2]
 * 若存在 s1 <= s2 && e1 >= e2 && (e1-s1) >= (e2-s2) 包含,即视为子集
 */
public class RangeContains {

    public static void main(String[] args) {
        int[] ints = countContain(new int[][]{
                {1, 2},
                {2, 5},
                {2, 3},
                {2, 4},
                {3, 6},
                {6, 8},
                {7, 8},
        });

        System.out.println(Arrays.toString(ints));
    }

    /**
     * 对ranges排序 先按照s1排序, s1相同时按照e1排序
     * 那样仅仅需要找到那些个包含s1  同时e1在目标的右边的集合区间
     * ,使用树状数组快速查询
     */
    private static int[] countContain(int[][] ranges) {
        int n = ranges.length;
        Point[] cow = new Point[n];
        int[] range;
        int max = 0;
        for (int i = 0; i < n; i++) {
            range = ranges[i];
            cow[i] = new Point(i, range[0], range[1]);
            max = Math.max(max, range[1]);
        }
        Arrays.sort(cow, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //先按照x升序排序, 相同时按照y倒序排序
                //之后仅仅需要找到历史中,所有比y的区间
                return o1.x == o2.x ? o2.y - o1.y : o1.x - o2.x;
            }
        });
//        System.out.println(Arrays.toString(points));
        int[] sum = new int[max], ans = new int[sum.length]; //这里应该是y的最大值
        for (int i = 0; i < n; ++i) { //每次遍历, 这样就代表从x小于或等于的位置, 找到左右比y大的地方
            if (i > 0 && cow[i].x == cow[i - 1].x && cow[i].y == cow[i - 1].y) {
                ans[cow[i].index] = ans[cow[i - 1].index];
            } else {
                ans[cow[i].index] = i - query(sum, cow[i].y - 1); //当前位置所有的, 减去过去中不超过的y数量
            }
            add(sum, cow[i].y, 1);
        }
        int[] ret = new int[n];
        System.arraycopy(ans, 0, ret, 0, n);
        return ret;
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_301_320.Solution307}
     */
    static int lowbit(int x) {
        return x & -x;
    }

    static void add(int[] sum, int index, int x) {
        for (int pos = index; pos < sum.length; pos += lowbit(pos)) {
            sum[pos] += x;
        }
    }

    //查的是到index位置(值)的所有数据信息
    static int query(int[] sum, int index) {
        int ret = 0;
        for (int pos = index; pos > 0; pos -= lowbit(pos))
            ret += sum[pos];
        return ret;
    }

    static class Point {
        int index;
        int x;
        int y;

        public Point(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "index=" + index +
                    ", x=" + x +
                    ", y=" + y +
                    '}' + "\n";
        }
    }
}
