package com.welph.leecode.algorithm.thinking;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [线段树（Segment Tree）]
 * 几乎是算法竞赛最常用的数据结构了，它主要用于维护区间信息（要求满足结合律）。
 * 与树状数组相比，它可以实现 [公式] 的区间修改，还可以同时支持多种操作（加、乘），更具通用性
 * <p>
 * -----------------
 * 线段树是一棵平衡二叉树。母结点代表整个区间的和，越往下区间越小。
 * 注意，线段树的每个节点都对应一条线段（区间），但并不保证所有的线段（区间）都是线段树的节点，这两者应当区分开。
 * @since 2021/6/15 11:05
 */
public class SegmentTree_14 {

    public static void main(String[] args) {
        //构建线段树
        int[] ints = {1, 2, 3, 4, 5};
        SegmentTree segmentTree = new SegmentTree(ints);
        segmentTree.update(0, 1, 3);
        System.out.println(segmentTree.query(0, 4));
    }

    static class SegmentTree {
        int[] tree;
        int[] mark;
        int NA = Integer.MIN_VALUE;
        int N;

        public SegmentTree(int[] iterable) {
            N = iterable.length;
            int len = 100;
            tree = new int[len];
            mark = new int[len];
            build(iterable, 1, N, 1);
        }

        /**
         * 若是其他类型, 就可以看作[组合函数]
         */
        private int op(int a, int b) {
            return a + b;
        }

        private void upd(int p, int d, int len) {
            tree[p] += d * len;
            if (mark[p] == NA) {
                mark[p] = d;
            } else {
                mark[p] += d;
            }
        }

        private void build(int[] iterable, int l, int r, int p) {
            if (l == r) {
                tree[p] = iterable[l - 1];
                return;
            }
            int mid = (l + r) / 2;
            build(iterable, l, mid, p * 2);
            build(iterable, mid + 1, r, p * 2 + 1);
            tree[p] = op(tree[p * 2], tree[p * 2 + 1]);
        }

        private void push_down(int p, int len) {
            if (mark[p] == NA) return;
            upd(p * 2, mark[p], len - len / 2);
            upd(p * 2 + 1, mark[p], len / 2);
            mark[p] = NA;
        }

        private void update(int l, int r, int d, int p, int cl, int cr) {
            if (cl >= l && cr <= r) {
                upd(p, d, cr - cl + 1);
                return;
            }
            push_down(p, cr - cl + 1);
            int mid = (cl + cr) / 2;
            if (mid >= l) update(l, r, d, p * 2, cl, mid);
            if (mid < r) update(l, r, d, p * 2 + 1, mid + 1, cr);
            tree[p] = op(tree[p * 2], tree[p * 2 + 1]);
        }

        public void update(int l, int r, int d) {
            l++;
            r++;
            update(l, r, d, 1, 1, N);
        }

        public int query(int l, int r) {
            l++;
            r++;
            return query(l, r, 1, 1, N);
        }

        /**
         * @param l  查询左边界
         * @param r  查询右边界
         * @param p  变更值
         * @param cl 对应的查询范围左边界
         * @param cr 对应的查询范围右边界
         * @return
         */
        public int query(int l, int r, int p, int cl, int cr) {
            if (cl >= l && cr <= r) return tree[p];
            push_down(p, cr - cl + 1);
            int mid = (cl + cr) / 2;
            if (mid >= r)
                return query(l, r, p * 2, cl, mid);
            else if (mid < l)
                return query(l, r, p * 2 + 1, mid + 1, cr);
            else
                return op(query(l, r, p * 2, cl, mid), query(l, r, p * 2 + 1, mid + 1, cr));
        }
    }

}
