package com.welph.leecode.part_1_500.part_381_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 我们有 N 个与坐标轴对齐的矩形, 其中 N > 0, 判断它们是否能精确地覆盖一个矩形区域。
 * 每个矩形用左下角的点和右上角的点的坐标来表示。例如，
 * 一个单位正方形可以表示为 [1,1,2,2]。 ( 左下角的点的坐标为 (1, 1) 以及右上角的点的坐标为 (2, 2) )。
 * <p>
 * 示例 1:
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [3,2,4,4],
 * [1,3,2,4],
 * [2,3,3,4]
 * ]
 * 返回 true。5个矩形一起可以精确地覆盖一个矩形区域。
 * <p>
 * 示例 2:
 * rectangles = [
 * [1,1,2,3],
 * [1,3,2,4],
 * [3,1,4,2],
 * [3,2,4,4]
 * ]
 * <p>
 * 返回 false。两个矩形之间有间隔，无法覆盖成一个矩形。
 * <p>
 * 示例 3:
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [1,3,2,4],
 * [3,2,4,4]
 * ]
 * 返回 false。图形顶端留有间隔，无法覆盖成一个矩形。
 * <p>
 * 示例 4:
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [1,3,2,4],
 * [2,2,4,4]
 * ]
 * <p>
 * 返回 false。因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 */
public class Solution391 {

    public static void main(String[] args) {
        // System.out.println(isRectangleCover(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2},
        // {1, 3, 2, 4}, {3, 2, 4, 4}}));
        System.out.println(isRectangleCover(
                new int[][] { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 }, { 1, 3, 2, 4 }, { 2, 3, 3, 4 } }));
        // [[0,0,1,1],[0,1,3,2],[1,0,2,2]]
        System.out.println(isRectangleCover(new int[][] { { 0, 0, 1, 1 }, { 0, 1, 3, 2 }, { 1, 0, 2, 2 } }));
        // [[0,0,4,1],[7,0,8,3],[5,1,6,3],[6,0,7,2],[4,0,5,1],[4,2,5,3],[2,1,4,3],[0,2,2,3],[0,1,2,2],[6,2,8,3],[5,0,6,1],[4,1,5,2]]
        System.out.println(isRectangleCover(new int[][] { { 0, 0, 4, 1 }, { 7, 0, 8, 3 }, { 5, 1, 6, 3 },
                { 6, 0, 7, 2 }, { 4, 0, 5, 1 }, { 4, 2, 5, 3 }, { 2, 1, 4, 3 }, { 0, 2, 2, 3 }, { 0, 1, 2, 2 },
                { 6, 2, 8, 3 }, { 5, 0, 6, 1 }, { 4, 1, 5, 2 } }));
    }

    /**
     * 通过面积.除了上下左右四个点, 其他点都是成对出现的.
     * -------------空间和时间. 4N的时间
     */
    public static boolean isRectangleCover1(int[][] rectangles) {
        Set<Point> set = new HashSet<>();
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        int area = 0;
        int[] intsX = { 0, 2 };
        int[] intsY = { 1, 3 };
        Point point;
        for (int[] rectangle : rectangles) {
            minX = Math.min(minX, rectangle[0]);
            minY = Math.min(minY, rectangle[1]);
            maxX = Math.max(maxX, rectangle[2]);
            maxY = Math.max(maxY, rectangle[3]);
            area += (rectangle[3] - rectangle[1]) * (rectangle[2] - rectangle[0]);

            for (int x : intsX) {
                for (int y : intsY) {
                    point = new Point(rectangle[x], rectangle[y]);
                    if (set.contains(point)) {
                        set.remove(point);
                    } else {
                        set.add(point);
                    }
                }
            }
        }
        if (set.size() != 4
                || !set.contains(new Point(minX, minY))
                || !set.contains(new Point(minX, maxY))
                || !set.contains(new Point(maxX, minY))
                || !set.contains(new Point(maxX, maxY))) {
            return false;
        }
        return area == (maxY - minY) * (maxX - minX);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x + y;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return p.x == x && p.y == y;
        }
    }

    //////////////////////////////////////////////////////

    /**
     * 需要精准合并, 不能有重合
     * ------------
     * [扫描线] --- 按照X坐标进行排序且每个扫描下来都是一个合理的矩形, 通过第一次扫描就能够确定对应的高了.
     * + 线段树
     * todo GOOD!!! 使用扫描线+线段树完成了, 虽然时间长了些
     */
    public static boolean isRectangleCover(int[][] rectangles) {
        int maxN = rectangles.length * 2;
        Line[] lines = new Line[maxN];
        int cnt = 0;
        int[] yy = new int[maxN];
        for (int[] rectangle : rectangles) {
            lines[cnt] = new Line(rectangle[0], rectangle[3], rectangle[1], 1);
            yy[cnt++] = rectangle[1];
            lines[cnt] = new Line(rectangle[2], rectangle[3], rectangle[1], -1);
            yy[cnt++] = rectangle[3];
        }
        Arrays.sort(lines, Comparator.comparingInt(value -> value.x));
        Arrays.sort(yy);
        int union = 1;
        for (int i = 1; i < cnt; i++) {
            if (yy[i] != yy[i - 1]) {
                yy[union++] = yy[i]; // 检查有多少个纵坐标
            }
        }
        int r = union - 1;
        // 找到r的最接近的2的平方数值
        int[] cover = new int[near2square(union) * 2];
        for (int i = 0; i < cnt; i++) {
            Line line = lines[i];
            if (i > 0 && line.x != lines[i - 1].x) { // 若不是同一竖线
                if (!checkCover(cover, 0, r, 0, r, 1)) {
                    return false;
                }
            }
            // 通过yy
            int yl = binary(yy, 0, r, line.yd);// 低点
            int yr = binary(yy, 0, r, line.yu);// 顶点
            // 根据出度入库标记 纵坐标的范围值
            update(yl, yr, line.inout, 0, r, cover, 1);
        }
        return true;
    }

    // 最接近的平方数
    private static int near2square(int r) {
        int n = r - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    // 校验每一段是否正常只保留1个值, 若是大于1 说明有重叠的地方, 若是小于1 说明有空白的地方
    private static boolean checkCover(int[] cover, int yl, int yr, int l, int r, int rt) {
        if (l + 1 == r) {
            return cover[rt] == 1;
        }
        push_down(cover, rt);
        int m = (l + r) >> 1;
        boolean b = true;
        if (yl <= m) {
            b = checkCover(cover, yl, yr, l, m, rt * 2);
        }
        if (b && yr > m) {
            b = checkCover(cover, yl, yr, m, r, rt * 2 + 1);
        }
        return b;
    }

    static int binary(int[] yy, int l, int r, int value) {
        int mid;
        int midVal;
        while (l <= r) {
            mid = (l + r) >>> 1;
            midVal = yy[mid];
            if (midVal < value)
                l = mid + 1;
            else if (value < midVal)
                r = mid - 1;
            else
                return mid;
        }
        return l;
    }

    static void update(int yl, int yr, int io, int l, int r, int[] cover, int rt) {
        if (yl > r || yr < l)
            return;
        if (yl <= l && yr >= r) { // 说明是 yl<=l<r<=yr 整个线段的范围都是需要添加的.
            cover[rt] += io;
            return;
        }
        if (l + 1 == r) // 左开右闭 (]
            return;
        // 将原本的cover给放下追加
        push_down(cover, rt);
        int m = (l + r) >> 1; // 中间
        if (yl <= m) {
            update(yl, yr, io, l, m, cover, rt * 2);
        }
        if (yr > m) {
            update(yl, yr, io, m, r, cover, rt * 2 + 1);
        }
    }

    static void push_down(int[] cover, int rt) {
        if (cover[rt] == 0)
            return;
        upd(cover, rt * 2, cover[rt]);
        upd(cover, rt * 2 + 1, cover[rt]);
        cover[rt] = 0;
    }

    static void upd(int[] cover, int rt, int op) {
        if (rt < cover.length) {
            cover[rt] += op;
        }
    }

    static class Line {
        int x; // x的坐标
        int yu; // y的上坐标
        int yd; // y的下坐标
        int inout;

        public Line(int x, int yu, int yd, int inout) {
            this.x = x;
            this.yu = yu;
            this.yd = yd;
            this.inout = inout;
        }
    }

    // 另一种写法 扫描线
    public boolean isRectangleCover3(int[][] rectangles) {
        int n = rectangles.length;
        int[][] rs = new int[n * 2][4];
        for (int i = 0, idx = 0; i < n; i++) {
            int[] re = rectangles[i];
            rs[idx++] = new int[] { re[0], re[1], re[3], 1 };
            rs[idx++] = new int[] { re[2], re[1], re[3], -1 };
        }
        Arrays.sort(rs, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0]; // x轴顺序
            return a[1] - b[1]; // y轴顺序
        });
        n *= 2; // rs的长度
        // 分别存储相同的横坐标下「左边的线段」和「右边的线段」 (y1, y2)
        List<int[]> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int l = 0; l < n;) {
            int r = l;
            l1.clear();
            l2.clear();
            // 找到横坐标相同部分, 从当前往后找到等同l的x轴线段
            while (r < n && rs[r][0] == rs[l][0])
                r++;
            // 从l扫描到r, 因为是相同的x轴
            for (int i = l; i < r; i++) {
                // 当前线段的下和上两个点y坐标
                int[] cur = new int[] { rs[i][1], rs[i][2] };
                List<int[]> list = rs[i][3] == 1 ? l1 : l2; // 入度选l1
                if (list.isEmpty()) {
                    list.add(cur);
                } else {
                    int[] prev = list.get(list.size() - 1);
                    // 因为按照了下点坐标升序
                    if (cur[0] < prev[1])
                        return false; // 存在重叠
                    else if (cur[0] == prev[1])
                        prev[1] = cur[1]; // 首尾相连
                    else // 这里是直接添加的, 不能用cur[0] > prev[1]来判断, 因为中间段可能会有间隔
                        /*
                         * |--------|--------|
                         * |-----------------|
                         * |--------|--------|
                         * 这种情况, 在扫描到中间的时候就会出现相同x轴, 两段.
                         */
                        list.add(cur);
                }
            }
            if (l > 0 && r < n) {
                // 若不是完美矩形的边缘竖边，检查是否成对出现
                if (l1.size() != l2.size()) // 入度出度相同
                    return false;
                for (int i = 0; i < l1.size(); i++) {
                    if (l1.get(i)[0] == l2.get(i)[0] && l1.get(i)[1] == l2.get(i)[1])
                        continue;
                    return false;
                }
            } else {
                // 若是完美矩形的边缘竖边，检查是否形成完整一段 ,它 这里也能兜得住 中间空白的地方, size()=1
                if (l1.size() + l2.size() != 1)
                    return false;
            }
            l = r;// 从下一段开始
        }
        return true;
    }
}
