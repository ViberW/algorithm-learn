package com.welph.leecode.algorithm.thinking;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [二叉搜索树]
 * {@link com.welph.leecode.algorithm.tree.BinarySearchTree}
 * @since 2021/8/4 10:41
 */
public class BinarySearchTree_45 {

    public static void main(String[] args) {

    }

    public static class Tree {
        int[] L;//L: 左子树根节点编号
        int[] R;//R：右子树根节点编号
        int[] N;//N：该节点储存的数出现的次数
        int[] val;//val：该节点储存的数
        int[] size;//size：以该节点为根节点的子树的节点数目（即子树大小）
        int cnt = 1;

        public void insert(int v) {
            insert(v, 1);
        }

        /**
         * 类似于 链式向量星 方式构建了二叉搜索树
         */
        protected void insert(int v, int pos) {
            size[pos]++; // 树大小+1
            if (N[pos] == 0 && L[pos] == 0 && R[pos] == 0) { // 空节点
                val[pos] = v;
                N[pos] = 1;
            } else if (v < val[pos]) {// 向左搜索
                if (L[pos] == 0) // 如果应该向左搜，但不存在左节点，则创建一个新节点
                    L[pos] = ++cnt;
                insert(v, L[pos]);
            } else if (v > val[pos]) { // 向右搜索
                if (R[pos] == 0)
                    R[pos] = ++cnt;
                insert(v, R[pos]);
            } else { // 已经存在值相同的节点
                N[pos]++;
            }
        }

        public void remove(int v) {
            remove(v, 1);
        }

        /**
         * 惰性删除  --这里的方法 是基于确定了key存在...
         * 判断节点是否为空: N[pos]==0 && L[pos]==0 && R[pos]==0
         */
        protected void remove(int v, int pos) {
            size[pos]--; // 树大小-1
            if (v < val[pos])
                remove(v, L[pos]);
            else if (v > val[pos])
                remove(v, R[pos]);
            else
                N[pos]--;
        }

        public int countLt(int v) {
            return countLt(v, 1);
        }

        // 求比某数小的数的个数
        private int countLt(int v, int pos) {
            if (v < val[pos])
                return L[pos] != 0 ? countLt(v, L[pos]) : 0;
            else if (v > val[pos])
                return size[L[pos]] + N[pos] + (R[pos] != 0 ? countLt(v, R[pos]) : 0);
            else
                return size[L[pos]];
        }

        public int countGt(int v) {
            return countGt(v, 1);
        }

        // 求比某数大的数的个数
        private int countGt(int v, int pos) {
            if (v > val[pos])
                return R[pos] != 0 ? countGt(v, R[pos]) : 0;
            else if (v < val[pos])
                return size[R[pos]] + N[pos] + (L[pos] != 0 ? countGt(v, L[pos]) : 0);
            else
                return size[R[pos]];
        }

        //值v的排名
        public int rank(int v) {
            return countLt(v) + 1;
        }

        public int kth(int rank) {
            return kth(rank, 1);
        }

        /**
         * 求指定排名的值 --倒序的
         */
        private int kth(int rank, int pos) {
            if (size[L[pos]] + 1 > rank) // 答案在左，在左子树中找排名为k的数
                return kth(rank, L[pos]);
            else if (size[L[pos]] + N[pos] < rank)  // 答案在右，在右子树中找排名为k - size[L[pos]] - N[pos]的数
                return kth(rank - size[L[pos]] - N[pos], R[pos]);
            else
                return val[pos];
        }

        // 求前驱: 直接找到排名比当前数小1的那个数
        public int pre(int v) {
            int r = countLt(v);
            return kth(r);
        }

        // 求后继: 小于等于当前数的数的数量+1
        public int suc(int v) {
            int r = size[1] - countGt(v) + 1;
            return kth(r);
        }
    }
}
